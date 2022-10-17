package com.example.mall.utils;

public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_KAPTCHA = "kaptcha";
    private static final String PREFIX_TICKET = "ticket";
    private static final String PREFIX_USER = "user";
    private static final String PREFIX_CODE = "code";

    // 登录验证码
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    // 登录的凭证
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }

    // 用户
    public static String getUserKey(String userId) {
        return PREFIX_USER + SPLIT + userId;
    }

    public static String getCodeKey(String owner) {
        return PREFIX_CODE + SPLIT + owner;
    }

}
