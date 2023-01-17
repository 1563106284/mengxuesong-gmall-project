package com.atguigu.gmall.common.constant;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/15 19:27
 */
public class SystemRedisConstant {

    public static final String NULL_VALUE = "X";
    public static final long NULL_VALUE_TTL =60*30L ;
    public static final Long SKUDETAIL_VALIE_TTL =60*60*24*7L ;
    public static final String BLOOM_SKUID = "bloom:skuId";
    public static final String SKU_INFO = "sku:info:";
    public static final String NEW_BLOOM_SKUID = "newBloom:skuId";
    public static String lOCK_SKU_DETAIL="lock:sku:detail";
}
