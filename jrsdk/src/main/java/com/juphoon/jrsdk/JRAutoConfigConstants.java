package com.juphoon.jrsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JRAutoConfigConstants {

    /**
     *  @brief 自动配置的状态
     */
    @IntDef({AUTO_CONFIG_STATE_NONE, AUTO_CONFIG_STATE_RUNNING, AUTO_CONFIG_STATE_FAILED, AUTO_CONFIG_STATE_OK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoConfigState {

    }
    /**
     * 未知
     */
    public static final int AUTO_CONFIG_STATE_NONE = 0;
    /**
     * 自动配置中
     */
    public static final int AUTO_CONFIG_STATE_RUNNING = 1;
    /**
     * 自动配置失败
     */
    public static final int AUTO_CONFIG_STATE_FAILED = 2;
    /**
     * 自动配置成功
     */
    public static final int AUTO_CONFIG_STATE_OK = 3;


    /**
     *  @brief 自动配置失败错误码
     */
    @IntDef({AUTO_CONFIG_ERROR_NONE, AUTO_CONFIG_ERROR_TIMEOUT, AUTO_CONFIG_ERROR_NETWORK, AUTO_CONFIG_ERROR_FORBIDDEN, AUTO_CONFIG_ERROR_INTERNAL_ERROR,
            AUTO_CONFIG_ERROR_INCORRET_XML, AUTO_CONFIG_ERROR_DISABLED_TEMP, AUTO_CONFIG_ERROR_DISABLED_PERM, AUTO_CONFIG_ERROR_DECLINE,
            AUTO_CONFIG_ERROR_INVALID_OTP, AUTO_CONFIG_ERROR_INVALID_TOKEN, AUTO_CONFIG_ERROR_INVALID_NUMBER, AUTO_CONFIG_ERROR_RETRY_AFTER,
            AUTO_CONFIG_ERROR_BOSS_ERROR, AUTO_CONFIG_ERROR_NO_WHITE_USER, AUTO_CONFIG_ERROR_BOSS_TIMEOUT, AUTO_CONFIG_ERROR_PROMPT_TIMEOUT, AUTO_CONFIG_ERROR_HAS_BODY, AUTO_CONFIG_ERROR_OTHER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoConfigError {

    }
    /**
     * 无错误
     */
    public static final int AUTO_CONFIG_ERROR_NONE = 0;
    /**
     * 超时
     */
    public static final int AUTO_CONFIG_ERROR_TIMEOUT = 1;
    /**
     * 网络错误
     */
    public static final int AUTO_CONFIG_ERROR_NETWORK = 2;
    /**
     * 请求被拒绝
     */
    public static final int AUTO_CONFIG_ERROR_FORBIDDEN = 3;
    /**
     * 内部错误
     */
    public static final int AUTO_CONFIG_ERROR_INTERNAL_ERROR = 4;
    /**
     * xml格式错误
     */
    public static final int AUTO_CONFIG_ERROR_INCORRET_XML = 5;
    /**
     * 暂时禁止使用RCS业务
     */
    public static final int AUTO_CONFIG_ERROR_DISABLED_TEMP = 6;
    /**
     * 永久禁止使用RCS业务
     */
    public static final int AUTO_CONFIG_ERROR_DISABLED_PERM = 7;
    /**
     * 用户拒绝
     */
    public static final int AUTO_CONFIG_ERROR_DECLINE = 8;
    /**
     * 无效的验证码
     */
    public static final int AUTO_CONFIG_ERROR_INVALID_OTP = 9;
    /**
     * token无效
     */
    public static final int AUTO_CONFIG_ERROR_INVALID_TOKEN = 10;
    /**
     * 号码无效
     */
    public static final int AUTO_CONFIG_ERROR_INVALID_NUMBER = 11;
    /**
     * 稍后重试
     */
    public static final int AUTO_CONFIG_ERROR_RETRY_AFTER = 12;
    /**
     * boss返回错误
     */
    public static final int AUTO_CONFIG_ERROR_BOSS_ERROR = 13;
    /**
     * 非白名单用户
     */
    public static final int AUTO_CONFIG_ERROR_NO_WHITE_USER = 14;
    /**
     * boss超时
     */
    public static final int AUTO_CONFIG_ERROR_BOSS_TIMEOUT = 15;
    /**
     * 无token
     */
    public static final int AUTO_CONFIG_ERROR_PROMPT_TIMEOUT = 16;
    /**
     * 200OK有body
     */
    public static final int AUTO_CONFIG_ERROR_HAS_BODY = 17;
    /**
     * 其他错误
     */
    public static final int AUTO_CONFIG_ERROR_OTHER = 18;
}
