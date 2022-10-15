package com.example.mall.utils;

public interface MallConstant {

    /**
     * 激活成功
     */
    Integer ACTIVATION_SUCCESS = 0;
    /**
     * 重复激活
     */
    Integer ACTIVATION_REPEAT = 1;
    /**
     * 激活失败
     */
    Integer ACTIVATION_FAILURE = 2;
    /**
     * 默认状态的登录凭证超时时间
     */
    Integer DEFAULT_EXPIRED_SECONDS = 3600 * 12;
    /**
     * 记住状态下的登录凭证超时时间
     */
    Integer REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
    /**
     * 权限：管理员-0
     */
    String AUTHORITY_ADMIN = "admin";
    /**
     * 权限：卖家-1
     */
    String AUTHORITY_SELLER = "seller";
    /**
     * 权限：买家-2
     */
    String AUTHORITY_BUYER = "buyer";

}
