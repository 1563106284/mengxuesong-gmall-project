package com.atguigu.gmall.product;


import lombok.Data;
import lombok.SneakyThrows;


import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述：
 * 线程测试：
 *
 * @Author: mengzhengjin
 * @Date: 2022/12/1 14:55
 */

public class ThreadTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println("当前线程的名字是" + Thread.currentThread().getName() + i);
            }
        }
    }

    public static void main(String[] args) {
//        new Thread().start();
//        System.out.println("主线程");

        // 2:2个线程：
        new Thread01().start();
        new Thread02().start();

    }
}

class Thread01 extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println("当前线程是" + Thread.currentThread().getName() + i);
            }
        }
    }
}

class Thread02 extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println("当前线程是" + Thread.currentThread().getName() + i);
            }
        }
    }
}


class AnonymousSubClass {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + i);
                    }
                }
            }
        }.start();
    }
}

// 方式2： 实现runnable接口
class RunnableThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println("当前线程" + Thread.currentThread().getName() + i);
            }
        }
    }

}


class RunnableTest {


    public static void main(String[] args) {
        RunnableThread runnableThread = new RunnableThread();
        Thread thread01 = new Thread(runnableThread);
        Thread thread02 = new Thread(runnableThread);
    }
}

// 实现callable接口
class CallableThread implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println("当前线程" + i);
                sum += i;
            }

        }

        return sum;
    }
}

class CallThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThread callableThread = new CallableThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableThread);
        new Thread(futureTask).start();
        Integer sum = futureTask.get();
        System.out.println("总和为" + sum);

    }
}

class RunnableThread01 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + i);
            }
        }
    }
}


class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        executorService.execute(new RunnableThread01());
        executorService.shutdown();
    }
}

// 模拟线程问题： 线程 操作 资源类：
@Data
class Ticket implements Runnable {
    private int ticketNum = 100;
    private String name;

    @Override
    public void run() {
        while (true) {
            if (ticketNum > 0) {
                // 增大线程问题：
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "剩下票" + ticketNum);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticketNum--;
            } else {
                break;
            }
        }
    }
}

class ProblemTread {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread thread01 = new Thread(ticket);
        Thread thread02 = new Thread(ticket);
        Thread thread03 = new Thread(ticket);
        thread01.setName("窗口1");
        thread02.setName("窗口2");
        thread03.setName("窗口3");

        thread01.start();
        thread02.start();
        thread03.start();
    }
}
// 解决线程问题：模拟：操作同一个对象

class Ticket01 implements Runnable {
    private Integer ticket = 100;
    Object object = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "当前剩余" + --ticket);

                } else {
                    break;
                }

            }
        }
    }
}

class problemRunnableThread {
    public static void main(String[] args) {
        Ticket01 ticket01 = new Ticket01();
        Thread thread01 = new Thread(ticket01);
        Thread thread02 = new Thread(ticket01);
        Thread thread03 = new Thread(ticket01);
        thread01.setName("窗口1");
        thread02.setName("窗口2");
        thread03.setName("窗口3");
        thread01.start();
        thread02.start();
        thread03.start();

    }
}

// 解决问题2：锁方法
class Ticket02 implements Runnable {
    private Integer ticketNum = 100;

    @Override
    public void run() {
        // while方法：
        while (ticketNum > 0) {
            show();
        }
    }

    public synchronized void show() {
        if (ticketNum > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "当前票号：" + --ticketNum);
        }
    }
}


class ProblemThread03 {
    public static void main(String[] args) {
        Ticket02 ticket02 = new Ticket02();
        Thread thread01 = new Thread(ticket02);
        Thread thread02 = new Thread(ticket02);
        Thread thread03 = new Thread(ticket02);
        thread01.setName("窗口1");
        thread01.setName("窗口2");
        thread01.setName("窗口3");
        thread01.start();
        thread02.start();
        thread03.start();
    }
}

// 设置最大 ：最小线程：
class Ticket03 implements Runnable {
    private Integer ticketNum = 100;

    @Override
    public void run() {
        while (ticketNum > 0) {
            show();
        }
    }

    public synchronized void show() {
        if (ticketNum > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "还剩下票数" + --ticketNum);
        }
    }
}

class ProblemThread04 {
    public static void main(String[] args) {
        Tick01 ticket03 = new Tick01();
        Thread thread01 = new Thread(ticket03);
        Thread thread02 = new Thread(ticket03);
        Thread thread03 = new Thread(ticket03);
        thread01.setName("窗口1");
        thread02.setName("窗口2");
        thread03.setName("窗口3");
        thread03.setPriority(Thread.MAX_PRIORITY);
        thread02.setPriority(Thread.MIN_PRIORITY);
        thread01.start();
        thread02.start();
        thread03.start();
    }
}


// 使用lock ：
class Tick01 implements Runnable {
    private Integer tickNum = 100;

    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        while (tickNum > 0) {
            reentrantLock.lock();
            if (tickNum > 0) {
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getThreadGroup() + "还剩下票" + --tickNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }

    }
}

// 模拟死锁：
class LockProblem implements Runnable {
    private Integer flag = 1;
    static String object1 = new String();
    static String object2 = new String();


    @Override
    public void run() {
        System.out.println("当前flag=" + flag);
        if (flag == 1) {
            synchronized (object1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("1");
                }
            }
        }


        if (flag == 0) {
            synchronized (object2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("0");
                }
            }
        }
    }


    public static void main(String[] args) {
        LockProblem lockProblem1 = new LockProblem();
        LockProblem lockProblem2 = new LockProblem();
        Thread thread1 = new Thread(lockProblem1);
        Thread thread2 = new Thread(lockProblem2);
        thread1.start();
        thread2.start();
    }
}


/*死锁--原理：两个线程由于相互等待对方已经锁住的资源而进入循环等到，最后导致死锁*/
class TestDeadLock implements Runnable {
    public int flag = 1;
    static Object o1 = new Object() ;
    static Object o2 = new Object();
    public void run() {
        System.out.println("flag = " + flag);
        if(flag == 1) {
            synchronized(o1) {
                try {
                    Thread.sleep(500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(o2) {
                    System.out.println("1");
                }
            }
        }

        if(flag == 0) {
            synchronized(o2) {
                try {
                    Thread.sleep(500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(o1) {
                    System.out.println("0");
                }
            }
        }
    }


    public static void main(String[] args) {
        TestDeadLock td1 = new TestDeadLock();
        TestDeadLock td2 = new TestDeadLock();
        td1.flag = 1;
        td2.flag = 0;
        Thread t1 = new Thread(td1);
        Thread t2 = new Thread(td2);
        t1.start();
        t2.start();

    }

}

/**
 * ，模拟死锁：
 */
@Data
class LockProblem01 implements Runnable {
    // 定义两个变量 + 1个flag
    private Integer flag = 1;
    static String object1 = new String();
    static String object2 = new String();

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (object1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            synchronized (object2){
                System.out.println("1");
            }
        }


        if (flag==0){
            synchronized (object2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (object1){
                System.out.println("0");
            }
        }
    }

    public static void main(String[] args) {
        LockProblem01 lockProblem01 = new LockProblem01();
        LockProblem01 lockProblem02 = new LockProblem01();
      lockProblem01.flag=1;
      lockProblem02.flag=0;

        new Thread(lockProblem01).start();
        new Thread(lockProblem02).start();

    }
}

/**
 *  模拟死锁：
 */
class LockProblem03 implements  Runnable{
        // 定义3 个变量：
    private Integer flag=1;
    static String object1= new String();
    static String object2= new String();
    @SneakyThrows
    @Override
    public void run() {
        // 上锁后两个线程上锁后相互等待
        if (flag==1){
            synchronized (object1){
                Thread.sleep(1000);
            }
            synchronized (object2){
                System.out.println("1");
            }

        }

        if (flag==0){
            synchronized (object2){
                Thread.sleep(1000);
                }
                synchronized(object1){
                    System.out.println("0");
            }
        }
    }


    public static void main(String[] args) {
        LockProblem03 lockProblem01 = new LockProblem03();
        LockProblem03 lockProblem02 = new LockProblem03();
        lockProblem01.flag=1;
        lockProblem02.flag=0;
        new Thread(lockProblem01).start();
        new Thread(lockProblem02).start();


    }
}


/**
 *   死锁：
 */
class LockProblem05 implements Runnable{
//      定义3 个变量：
    private Integer flag =1;
    static String object1= new String();
    static String object2= new String();

    @Override
    public void run() {
        if (flag ==1){
            System.out.println("flag="+flag);

            synchronized (object1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2){
                    System.out.println("1");
                }
            }


        }

        if (flag==0){
            System.out.println("flag="+flag);
            synchronized (object2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1){
                    System.out.println("0");
                }
            }


        }
    }


    public static void main(String[] args) {
        LockProblem05 lockProblem1 = new LockProblem05();
        LockProblem05 lockProblem2 = new LockProblem05();
        lockProblem1.flag=1;
        lockProblem2.flag=0;

        new Thread(lockProblem1).start();
        new Thread(lockProblem2).start();
    }
}

// lambda:
/**
 *  定义一个接口
 */
@FunctionalInterface
interface Calculator{
    int calculator(int a,int b);
}


class LambdaTest{
    public static void invokeCal(int a,int b,Calculator calculator){

        System.out.println("结果是"+calculator.calculator(a, b));
    }

    public static void main(String[] args) {
        invokeCal(5,6,(int a,int b)->{
           return a*b;
        });

        invokeCal(1, 2, (int a,int b)-> {return a+b;});

        // 定义一个变量：
        String[] arr={"1","2","3","4"};

        // 排序：

    }

}




















