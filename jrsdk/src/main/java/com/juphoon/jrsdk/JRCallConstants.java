package com.juphoon.jrsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JRCallConstants {
    /**
     * @brief 通话类型
     */
    @IntDef({CALL_TYPE_ONE_ON_ONE_AUDIO, CALL_TYPE_ONE_ON_ONE_VIDEO, CALL_TYPE_MULTI_AUDIO, CALL_TYPE_MULTI_VIDEO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallType {
    }
    /**
     *  一对一语音
     */
    public static final int CALL_TYPE_ONE_ON_ONE_AUDIO = 0;
    /**
     *  一对一视频
     */
    public static final int CALL_TYPE_ONE_ON_ONE_VIDEO = 1;
    /**
     *  多方语音
     */
    public static final int CALL_TYPE_MULTI_AUDIO = 2;
    /**
     *  多方视频
     */
    public static final int CALL_TYPE_MULTI_VIDEO = 3;

    /**
     * @brief 通话状态
     */
    @IntDef({CALL_STATE_INIT, CALL_STATE_PENDING, CALL_STATE_ALERTING, CALL_STATE_TALKING, CALL_STATE_OK, CALL_STATE_CANCEL,
            CALL_STATE_CANCELED, CALL_STATE_MISSED, CALL_STATE_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallState {
    }
    /**
     *  初始
     */
    public static final int CALL_STATE_INIT = 0;
    /**
     *  连接中
     */
    public static final int CALL_STATE_PENDING = 1;
    /**
     *  响铃中
     */
    public static final int CALL_STATE_ALERTING = 2;
    /**
     *  通话中
     */
    public static final int CALL_STATE_TALKING = 3;
    /**
     *  通话正常结束
     */
    public static final int CALL_STATE_OK = 4;
    /**
     *  未接通挂断
     */
    public static final int CALL_STATE_CANCEL = 5;
    /**
     * 未接通被挂断
     */
    public static final int CALL_STATE_CANCELED = 6;
    /**
     *  未接
     */
    public static final int CALL_STATE_MISSED = 7;
    /**
     *  异常
     */
    public static final int CALL_STATE_ERROR = 8;

    /**
     * @brief 通话方向
     */
    @IntDef({CALL_DIRECTION_IN, CALL_DIRECTION_OUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallDirection {
    }
    public static final int CALL_DIRECTION_IN = 0;
    public static final int CALL_DIRECTION_OUT = 1;

    /**
     * @brief 通话状态更新类型
     */
    @IntDef({CALL_UPDATE_TYPE_INCOMING, CALL_UPDATE_TYPE_OUTGOING, CALL_UPDATE_TYPE_ALERTING, CALL_UPDATE_TYPE_TALKING, CALL_UPDATE_TYPE_TERMED,
            CALL_UPDATE_TYPE_HOLD, CALL_UPDATE_TYPE_UNHOLD, CALL_UPDATE_TYPE_HELD, CALL_UPDATE_TYPE_UNHELD, CALL_UPDATE_TYPE_MUTE,
            CALL_UPDATE_TYPE_UNMUTE, CALL_UPDATE_TYPE_NET_STATUS_CHANGED, CALL_UPDATE_TYPE_CONF_MEMBER_UPDATE, CALL_UPDATE_TYPE_REQ_VIDEO,
            CALL_UPDATE_TYPE_TO_VIDEO_OK, CALL_UPDATE_TYPE_TO_VIDEO_FAILED, CALL_UPDATE_TYPE_TO_AUDIO_OK, CALL_UPDATE_TYPE_TO_AUDIO_FAILED, CALL_UPDATE_TYPE_TO_CONF})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallUpdateType {
    }
    public static final int CALL_UPDATE_TYPE_INCOMING = 0;
    public static final int CALL_UPDATE_TYPE_OUTGOING = 1;
    public static final int CALL_UPDATE_TYPE_ALERTING = 2;
    public static final int CALL_UPDATE_TYPE_TALKING = 3;
    public static final int CALL_UPDATE_TYPE_TERMED = 4;
    public static final int CALL_UPDATE_TYPE_HOLD = 5;
    public static final int CALL_UPDATE_TYPE_UNHOLD = 6;
    public static final int CALL_UPDATE_TYPE_HELD = 7;
    public static final int CALL_UPDATE_TYPE_UNHELD = 8;
    public static final int CALL_UPDATE_TYPE_MUTE = 9;
    public static final int CALL_UPDATE_TYPE_UNMUTE = 10;
    public static final int CALL_UPDATE_TYPE_NET_STATUS_CHANGED = 11;
    public static final int CALL_UPDATE_TYPE_CONF_MEMBER_UPDATE = 12;
    public static final int CALL_UPDATE_TYPE_REQ_VIDEO = 13;
    public static final int CALL_UPDATE_TYPE_TO_VIDEO_OK = 14;
    public static final int CALL_UPDATE_TYPE_TO_VIDEO_FAILED = 15;
    public static final int CALL_UPDATE_TYPE_TO_AUDIO_OK = 16;
    public static final int CALL_UPDATE_TYPE_TO_AUDIO_FAILED = 17;
    public static final int CALL_UPDATE_TYPE_TO_CONF = 18;

    /**
     * @brief 通话结束原因
     */
    @IntDef({CALL_TERM_REASON_NONE, CALL_TERM_REASON_NORMAL, CALL_TERM_REASON_BUSY, CALL_TERM_REASON_DECLINE, CALL_TERM_REASON_NOT_AVAILABLE,
            CALL_TERM_REASON_INTERRUPT, CALL_TERM_REASON_FORBIDDEN, CALL_TERM_REASON_NOT_FOUND, CALL_TERM_REASON_NOT_ACPTED, CALL_TERM_REASON_REQ_TERMED,
            CALL_TERM_REASON_INTERNAL_ERR, CALL_TERM_REASON_SRV_UNAVAIL, CALL_TERM_REASON_NOT_EXIST, CALL_TERM_REASON_TEMP_UNAVAIL, CALL_TERM_REASON_OTHER_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallTermReason {
    }
    public static final int CALL_TERM_REASON_NONE = 0;
    public static final int CALL_TERM_REASON_NORMAL = 1;
    public static final int CALL_TERM_REASON_BUSY = 2;
    public static final int CALL_TERM_REASON_DECLINE = 3;
    public static final int CALL_TERM_REASON_NOT_AVAILABLE = 4;
    public static final int CALL_TERM_REASON_INTERRUPT = 5;
    public static final int CALL_TERM_REASON_FORBIDDEN = 6;
    public static final int CALL_TERM_REASON_NOT_FOUND = 7;
    public static final int CALL_TERM_REASON_NOT_ACPTED = 8;
    public static final int CALL_TERM_REASON_REQ_TERMED = 9;
    public static final int CALL_TERM_REASON_INTERNAL_ERR = 10;
    public static final int CALL_TERM_REASON_SRV_UNAVAIL = 11;
    public static final int CALL_TERM_REASON_NOT_EXIST = 12;
    public static final int CALL_TERM_REASON_TEMP_UNAVAIL = 13;
    public static final int CALL_TERM_REASON_OTHER_ERROR = 14;

    /**
     * @brief DTMF类型
     */
    @IntDef({CALL_DTMF_TYPE_0, CALL_DTMF_TYPE_1, CALL_DTMF_TYPE_2, CALL_DTMF_TYPE_3, CALL_DTMF_TYPE_4, CALL_DTMF_TYPE_5, CALL_DTMF_TYPE_6,
            CALL_DTMF_TYPE_7, CALL_DTMF_TYPE_8, CALL_DTMF_TYPE_9, CALL_DTMF_TYPE_STAR, CALL_DTMF_TYPE_PIUND, CALL_DTMF_TYPE_A,
            CALL_DTMF_TYPE_B, CALL_DTMF_TYPE_C, CALL_DTMF_TYPE_D})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallDtmftype {
    }
    public static final int CALL_DTMF_TYPE_0 = 0;
    public static final int CALL_DTMF_TYPE_1 = 1;
    public static final int CALL_DTMF_TYPE_2 = 2;
    public static final int CALL_DTMF_TYPE_3 = 3;
    public static final int CALL_DTMF_TYPE_4 = 4;
    public static final int CALL_DTMF_TYPE_5 = 5;
    public static final int CALL_DTMF_TYPE_6 = 6;
    public static final int CALL_DTMF_TYPE_7 = 7;
    public static final int CALL_DTMF_TYPE_8 = 8;
    public static final int CALL_DTMF_TYPE_9 = 9;
    public static final int CALL_DTMF_TYPE_STAR = 10;
    public static final int CALL_DTMF_TYPE_PIUND = 11;
    public static final int CALL_DTMF_TYPE_A = 12;
    public static final int CALL_DTMF_TYPE_B = 13;
    public static final int CALL_DTMF_TYPE_C = 14;
    public static final int CALL_DTMF_TYPE_D = 15;

    /**
     * @brief 通话质量
     */
    @IntDef({CALL_NET_STATUS_DISCONNECTED, CALL_NET_STATUS_VERY_BAD, CALL_NET_STATUS_BAD, CALL_NET_STATUS_NORMAL, CALL_NET_STATUS_GOOD, CALL_NET_STATUS_VERY_GOOD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallNetStatus {
    }
    /// 未连接
    public static final int CALL_NET_STATUS_DISCONNECTED = -3;
    /// 很差
    public static final int CALL_NET_STATUS_VERY_BAD = -2;
    /// 差
    public static final int CALL_NET_STATUS_BAD = -1;
    /// 正常
    public static final int CALL_NET_STATUS_NORMAL = 0;
    /// 好
    public static final int CALL_NET_STATUS_GOOD = 1;
    /// 非常好
    public static final int CALL_NET_STATUS_VERY_GOOD = 2;

    /**
     * @brief 多方通话成员状态
     */
    @IntDef({CALL_MEMBER_STATUS_PENDING, CALL_MEMBER_STATUS_DIALINGIN, CALL_MEMBER_STATUS_DIALINGOUT, CALL_MEMBER_STATUS_ALERTING, CALL_MEMBER_STATUS_CONNED, CALL_MEMBER_STATUS_ONHOLD,
            CALL_MEMBER_STATUS_DISCING, CALL_MEMBER_STATUS_DISCED, CALL_MEMBER_STATUS_MUTED, CALL_MEMBER_STATUS_HANGUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallMemberStatus {
    }
    public static final int CALL_MEMBER_STATUS_PENDING = 0;
    public static final int CALL_MEMBER_STATUS_DIALINGIN = 1;
    public static final int CALL_MEMBER_STATUS_DIALINGOUT = 2;
    public static final int CALL_MEMBER_STATUS_ALERTING = 3;
    public static final int CALL_MEMBER_STATUS_CONNED = 4;
    public static final int CALL_MEMBER_STATUS_ONHOLD = 5;
    public static final int CALL_MEMBER_STATUS_DISCING = 6;
    public static final int CALL_MEMBER_STATUS_DISCED = 7;
    public static final int CALL_MEMBER_STATUS_MUTED = 8;
    public static final int CALL_MEMBER_STATUS_HANGUP = 9;
}
