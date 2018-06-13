package com.juphoon.jrsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JRGroupConstants {

    /**
     * 成员状态
     */
    @IntDef({GROUP_PARTP_STATUS_EXIST, GROUP_PARTP_STATUS_NOT_EXIST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupPartpStatus {

    }
    public static final int GROUP_PARTP_STATUS_EXIST = 0;
    public static final int GROUP_PARTP_STATUS_NOT_EXIST = 1;

    /**
     * 群状态
     */
    @IntDef({GROUP_STATUS_INVITED, GROUP_STATUS_INITIATED, GROUP_STATUS_STARTED, GROUP_STATUS_LEAVED, GROUP_STATUS_KICKED, GROUP_STATUS_DISSOLVED, GROUP_STATUS_INVALID})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupStatus {

    }
    public static final int GROUP_STATUS_INVITED = 0;
    public static final int GROUP_STATUS_INITIATED = 1;
    public static final int GROUP_STATUS_STARTED = 2;
    public static final int GROUP_STATUS_LEAVED = 3;
    public static final int GROUP_STATUS_KICKED = 4;
    public static final int GROUP_STATUS_DISSOLVED = 5;
    public static final int GROUP_STATUS_INVALID = 6;

    /**
     * 群类型
     */
    @IntDef({GROUP_TYPE_ALL, GROUP_TYPE_GENERAL, GROUP_TYPE_ENTERPRISE, GROUP_TYPE_PARTY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupType {

    }
    public static final int GROUP_TYPE_ALL = 0;
    public static final int GROUP_TYPE_GENERAL = 1;
    public static final int GROUP_TYPE_ENTERPRISE = 2;
    public static final int GROUP_TYPE_PARTY = 3;

    /**
     * 原因
     */
    @IntDef({GROUP_REASON_NONE, GROUP_REASON_CALL_FUNCTION_ERROR, GROUP_REASON_REJOIN_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupReason {

    }
    public static final int GROUP_REASON_NONE = 0;
    public static final int GROUP_REASON_CALL_FUNCTION_ERROR = 1;
    public static final int GROUP_REASON_REJOIN_ERROR = 2;

    /**
     * 操作结果
     */
    @IntDef({GROUP_OPERATION_TYPE_UNKNOW, GROUP_OPERATION_TYPE_CREATE, GROUP_OPERATION_TYPE_LEAVE, GROUP_OPERATION_TYPE_MODIFY_GROUP_NAME,
            GROUP_OPERATION_TYPE_MODIFY_NICK_NAME, GROUP_OPERATION_TYPE_DISSOLVE, GROUP_OPERATION_TYPE_REJECT_INVITE, GROUP_OPERATION_TYPE_ACCEPT_INVITE,
            GROUP_OPERATION_TYPE_INVITE, GROUP_OPERATION_TYPE_KICK, GROUP_OPERATION_TYPE_MODIFY_CHAIRMAN, GROUP_OPERATION_TYPE_SUBSCRIBE_GROUP_LIST,
            GROUP_OPERATION_TYPE_SUBSCRIBE_GROUP_INFO, GROUP_OPERATION_TYPE_ACCEPT_SESS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupOperationType {

    }
    public static final int GROUP_OPERATION_TYPE_UNKNOW = 0;
    public static final int GROUP_OPERATION_TYPE_CREATE = 1;
    public static final int GROUP_OPERATION_TYPE_LEAVE = 2;
    public static final int GROUP_OPERATION_TYPE_MODIFY_GROUP_NAME = 3;
    public static final int GROUP_OPERATION_TYPE_MODIFY_NICK_NAME = 4;
    public static final int GROUP_OPERATION_TYPE_DISSOLVE = 5;
    public static final int GROUP_OPERATION_TYPE_REJECT_INVITE = 6;
    public static final int GROUP_OPERATION_TYPE_ACCEPT_INVITE = 7;
    public static final int GROUP_OPERATION_TYPE_INVITE = 8;
    public static final int GROUP_OPERATION_TYPE_KICK = 9;
    public static final int GROUP_OPERATION_TYPE_MODIFY_CHAIRMAN = 10;
    public static final int GROUP_OPERATION_TYPE_SUBSCRIBE_GROUP_LIST = 11;
    public static final int GROUP_OPERATION_TYPE_SUBSCRIBE_GROUP_INFO = 12;
    public static final int GROUP_OPERATION_TYPE_ACCEPT_SESS = 13;
}
