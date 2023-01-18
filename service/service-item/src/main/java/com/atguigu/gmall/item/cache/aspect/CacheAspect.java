package com.atguigu.gmall.item.cache.aspect;

import com.atguigu.gmall.common.constant.SystemRedisConstant;
import com.atguigu.gmall.item.cache.CacheOpsService;
import com.atguigu.gmall.item.cache.annotation.GmallCache;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import com.sun.corba.se.spi.ior.iiop.IIOPFactories;
import javafx.scene.layout.ConstraintsBase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLClientInfoException;

/**
 * 功能描述：
 *
 * @componect:成为一个组件
 * @aspect：当前类是一个切面 写环绕通知：
 * 1：需要有返回类型 object
 * 2：异常必须往上跑
 * 3：切面和被代理的对象需要连接点：proceedingJoinPoint
 * 4：被代理的对象返回值必须必须往上返
 * 总结：
 * 通过断点：
 * 环绕通知：
 * controller层：调用的是代理
 * 代理通知连接点后代理的是被代理的对象
 * 被代理的对象通过拿到数据后返回给代理
 * 代理可以拿到数据：可以修改参数以及 结果
 * 在返回给controller
 * @Author: mengzhengjin
 * @Date: 2023/1/16 20:23
 */
@Component
@Aspect
public class CacheAspect {

    @Autowired
    CacheOpsService cacheOpsService;

    // 1:拿到spel解析器
    SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    // 2:模板上下文：
    TemplateParserContext parserContext = new TemplateParserContext();

    @Test
    public void heelTest(){
        Hello hello = new Hello();
        Class<? extends Hello> aClass = hello.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
    /**
     * 4:动态的获取cacheKey
     *
     * @param joinPoint
     * @return
     */
    private String determineCacheKey(ProceedingJoinPoint joinPoint) {
        // 1:转换拿到method signature
        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        // 2:拿到切面的方法
        Method currentMethod = method.getMethod();
        // 3:通过方法 .declaredAnnotation:拿到方法的注解
        String cacheAnnotation = currentMethod.getDeclaredAnnotation(GmallCache.class).cacheKey();

        // 4:创建方法拿到注解后面的值：
        String cacheKey=evaluationExpression(cacheAnnotation,joinPoint,String.class);


        return cacheKey;
    }

    private<T> T  evaluationExpression(String cacheAnnotation, ProceedingJoinPoint joinPoint, Class<T> clz) {

        // spel解析器：
        Expression expression = spelExpressionParser.parseExpression(cacheAnnotation, parserContext);
        // 4:sku:info:{#[0]}
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 5:取出参数：绑定到上下文中
        context.setVariable("params",joinPoint.getArgs());
        T cacheKey = expression.getValue(context, clz);
        return cacheKey;
    }

    /**
     * v3:优化缓存：布隆过滤器 以及 分布式的形成注解
     *
     * @param joinPoint
     * @return
     */
    @Around("@annotation(com.atguigu.gmall.item.cache.annotation.GmallCache)")
    public Object aroundCache(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1:获取缓存key：v1:动态获取cacheKey
//        String cacheKey = SystemRedisConstant.SKU_INFO + joinPoint.getArgs()[0];
        String cacheKey = determineCacheKey(joinPoint);

        // 2:查询当前缓存是否数据：
        SkuDetailInfoTo skuData = cacheOpsService.getSkuData(cacheKey, SkuDetailInfoTo.class);
        // 3:判断缓存数据：
        if (skuData == null) {
            //3.1：防止穿透：问bloom是否有当前id数据：
            boolean contains = cacheOpsService.bloomContains((Long) joinPoint.getArgs()[0]);
            // 3.2:如果没有当前id 返回null
            if (!contains) {
                return null;
            }
            // 3.3:布隆说有：准备回源:回源前加锁：防止数据不一致
            boolean lock = false;
            try {
                lock = cacheOpsService.tryLock(joinPoint.getArgs());
                // 3.4:加锁成功：回源
                if (lock) {
                    // 4:核心：切点：aop思想
                    Object result = joinPoint.proceed(joinPoint.getArgs());
                    //4.1：回源：
                    cacheOpsService.saveData(cacheKey, result);
                    return result;
                } else {
                    Thread.sleep(1000);
                    return cacheOpsService.getSkuData(cacheKey, SkuDetailInfoTo.class);
                }
            } finally {
                if (lock) {
                    cacheOpsService.unlock(joinPoint.getArgs());
                }
            }
        }
        return skuData;
    }


    /**
     * 2:环绕通知的标准写法
     *
     * @param joinPoint
     * @return
     */
//    @Around("@annotation(com.atguigu.gmall.item.cache.annotation.GmallCache)")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 1:获取方法签名：
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 2:获取方法传入的参数：
        Object[] args = joinPoint.getArgs();

        // 3:调用：利用反射执行：
        // 3.1:方法1：用proceed ：传入参数执行
//        Object method = joinPoint.proceed(args);
        // 3.2: 方法2：利用方法method signature 拿到当前方法
        System.out.println("当前方法是" + joinPoint.getThis());
        System.out.println("当前方法是" + joinPoint.getTarget());
//      signature.getMethod().invoke(signature.getMethod(),args);
        Method currentMethod = signature.getMethod();
        // 前置通知
        Object result = null;
        try {
            // aop切面利用反射：调用当前目标方法执行
            result = currentMethod.invoke(joinPoint.getTarget(), args);
            // result:返回的结果
            // 返回通知
        } catch (Exception e) {
            // 异常通知：异常必须被抛出去
//            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            // 后置通知
        }
        return result;
    }

    /**
     * 1:测试前置通知
     */
//    @Before("@annotation(com.atguigu.gmall.item.cache.annotation.GmallCache)")
    public void before() {
        System.out.println("前置通知已经执行");
    }
}
