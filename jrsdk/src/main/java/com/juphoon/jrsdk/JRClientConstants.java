package com.juphoon.jrsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JRClientConstants {

    /**
     * 登录状态
     */
    @IntDef({CLIENT_STATE_UNKNOW, CLIENT_STATE_IDLE, CLIENT_STATE_LOGINING, CLIENT_STATE_LOGINED, CLIENT_STATE_LOGOUTING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ClientState {

    }
    public static final int CLIENT_STATE_UNKNOW = 0;
    public static final int CLIENT_STATE_IDLE = 1;
    public static final int CLIENT_STATE_LOGINING = 2;
    public static final int CLIENT_STATE_LOGINED = 3;
    public static final int CLIENT_STATE_LOGOUTING = 4;

    /**
     * SDK初始化失败原因
     */
    @IntDef({SDK_INIT_REASON_NONE, SDK_INIT_REASON_SET_LOG_PATHFAILED, SDK_INIT_REASON_NO_LICENSE, SDK_INIT_REASON_SET_SDK_FAILED, SDK_INIT_REASON_DOWNLOAD_LICENSE_FAILED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SDKInitReason {

    }
    public static final int SDK_INIT_REASON_NONE = 0;
    public static final int SDK_INIT_REASON_SET_LOG_PATHFAILED = 1;
    public static final int SDK_INIT_REASON_NO_LICENSE = 2;
    public static final int SDK_INIT_REASON_SET_SDK_FAILED = 3;
    public static final int SDK_INIT_REASON_DOWNLOAD_LICENSE_FAILED = 4;


    /**
     * 注销或注册失败原因
     */
    @IntDef({REG_ERROR_NONE, REG_ERROR_SEND_MESSAGE_ERROR, REG_ERROR_AUTHENTICATION_FAILED, REG_ERROR_INVALID_USER, REG_ERROR_TIMEOUT,
            REG_ERROR_SERVER_BUSY, REG_ERROR_SERVER_NOT_REACHED, REG_ERROR_SERVER_FORBIDDEN, REG_ERROR_SERVER_UNAVAILABLE, REG_ERROR_DNS_QUERY_FAILED,
            REG_ERROR_NETWORK_ERROR, REG_ERROR_DEACTIVED, REG_ERROR_PROBATION, REG_ERROR_INTERNAL_ERROR, REG_ERROR_NO_RESOURCE, REG_ERROR_REJECTED,
            REG_ERROR_SIP_SESSION_ERROR, REG_ERROR_UNREGISTER_ERROR, REG_ERROR_INVALID_IP_ADDR, REG_ERROR_WAIT_PROMPT_PASSWORD_TIMEOUT, REG_ERROR_NOT_FOUND_USER,
            REG_ERROR_AUTHENTICATION_REJECTED, REG_ERROR_ID_NOT_MATCH, REG_ERROR_USER_NOT_EXIST, REG_ERROR_COMPANY_NOT_MATCH, REG_ERROR_CALL_FUNCTION_FAILED, REG_ERROR_OTHER_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RegError {

    }
    public static final int REG_ERROR_NONE = 0;
    public static final int REG_ERROR_SEND_MESSAGE_ERROR = 1;
    public static final int REG_ERROR_AUTHENTICATION_FAILED = 2;
    public static final int REG_ERROR_INVALID_USER = 3;
    public static final int REG_ERROR_TIMEOUT = 4;
    public static final int REG_ERROR_SERVER_BUSY = 5;
    public static final int REG_ERROR_SERVER_NOT_REACHED = 6;
    public static final int REG_ERROR_SERVER_FORBIDDEN = 7;
    public static final int REG_ERROR_SERVER_UNAVAILABLE = 8;
    public static final int REG_ERROR_DNS_QUERY_FAILED = 9;
    public static final int REG_ERROR_NETWORK_ERROR = 10;
    public static final int REG_ERROR_DEACTIVED = 11;
    public static final int REG_ERROR_PROBATION = 12;
    public static final int REG_ERROR_INTERNAL_ERROR = 13;
    public static final int REG_ERROR_NO_RESOURCE = 14;
    public static final int REG_ERROR_REJECTED = 15;
    public static final int REG_ERROR_SIP_SESSION_ERROR = 16;
    public static final int REG_ERROR_UNREGISTER_ERROR = 17;
    public static final int REG_ERROR_INVALID_IP_ADDR = 18;
    public static final int REG_ERROR_WAIT_PROMPT_PASSWORD_TIMEOUT = 19;
    public static final int REG_ERROR_NOT_FOUND_USER = 20;
    public static final int REG_ERROR_AUTHENTICATION_REJECTED = 21;
    public static final int REG_ERROR_ID_NOT_MATCH = 22;
    public static final int REG_ERROR_USER_NOT_EXIST = 23;
    public static final int REG_ERROR_COMPANY_NOT_MATCH = 24;
    public static final int REG_ERROR_CALL_FUNCTION_FAILED = 25;
    public static final int REG_ERROR_OTHER_ERROR = 26;
}
