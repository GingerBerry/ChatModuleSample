package com.juphoon.jrsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class JRCallDefine {
    /**
     * 通话方向
     */
    @IntDef({MTC_CALL_DIRECTION_IN, MTC_CALL_DIRECTION_OUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MtcCallDirection {
    }
    public static final int MTC_CALL_DIRECTION_IN = 0;
    public static final int MTC_CALL_DIRECTION_OUT = 1;

    /**
     * 通话结束原因
     */
    @IntDef({MTC_CALL_TERM_REASON_NONE, MTC_CALL_TERM_REASON_NORMAL, MTC_CALL_TERM_REASON_BUSY, MTC_CALL_TERM_REASON_DECLINE, MTC_CALL_TERM_REASON_NOT_AVAILABLE,
            MTC_CALL_TERM_REASON_INTERRUPT, MTC_CALL_TERM_REASON_FORBIDDEN, MTC_CALL_TERM_REASON_NOT_FOUND, MTC_CALL_TERM_REASON_NOT_ACPTED, MTC_CALL_TERM_REASON_REQ_TERMED,
            MTC_CALL_TERM_REASON_INTERNAL_ERR, MTC_CALL_TERM_REASON_SRV_UNAVAIL, MTC_CALL_TERM_REASON_NOT_EXIST, MTC_CALL_TERM_REASON_TEMP_UNAVAIL, MTC_CALL_TERM_REASON_OTHER_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MtcCallTermReason {
    }
    public static final int MTC_CALL_TERM_REASON_NONE = 0;
    public static final int MTC_CALL_TERM_REASON_NORMAL = 1;
    public static final int MTC_CALL_TERM_REASON_BUSY = 2;
    public static final int MTC_CALL_TERM_REASON_DECLINE = 3;
    public static final int MTC_CALL_TERM_REASON_NOT_AVAILABLE = 4;
    public static final int MTC_CALL_TERM_REASON_INTERRUPT = 5;
    public static final int MTC_CALL_TERM_REASON_FORBIDDEN = 6;
    public static final int MTC_CALL_TERM_REASON_NOT_FOUND = 7;
    public static final int MTC_CALL_TERM_REASON_NOT_ACPTED = 8;
    public static final int MTC_CALL_TERM_REASON_REQ_TERMED = 9;
    public static final int MTC_CALL_TERM_REASON_INTERNAL_ERR = 10;
    public static final int MTC_CALL_TERM_REASON_SRV_UNAVAIL = 11;
    public static final int MTC_CALL_TERM_REASON_NOT_EXIST = 12;
    public static final int MTC_CALL_TERM_REASON_TEMP_UNAVAIL = 13;
    public static final int MTC_CALL_TERM_REASON_OTHER_ERROR = 14;

    /**
     * 通话类型
     */
    @IntDef({MTC_CALL_TYPE_ONE_ON_ONE_AUDIO, MTC_CALL_TYPE_ONE_ON_ONE_VIDEO, MTC_CALL_TYPE_MULTI_AUDIO, MTC_CALL_TYPE_MULTI_VIDEO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MtcCallType {
    }

    public static final int MTC_CALL_TYPE_ONE_ON_ONE_AUDIO = 0;
    public static final int MTC_CALL_TYPE_ONE_ON_ONE_VIDEO = 1;
    public static final int MTC_CALL_TYPE_MULTI_AUDIO = 2;
    public static final int MTC_CALL_TYPE_MULTI_VIDEO = 3;

    /**
     * 通话更新类型
     */
    @IntDef({MTC_CALL_UPDATE_TYPE_REQ_VIDEO, MTC_CALL_UPDATE_TYPE_TO_VIDEO_OK, MTC_CALL_UPDATE_TYPE_TO_VIDEO_FAILED, MTC_CALL_UPDATE_TYPE_TO_AUDIO_OK, MTC_CALL_UPDATE_TYPE_TO_AUDIO_FAILED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MtcCallUpdateType {
    }

    public static final int MTC_CALL_UPDATE_TYPE_REQ_VIDEO = 0;
    public static final int MTC_CALL_UPDATE_TYPE_TO_VIDEO_OK = 1;
    public static final int MTC_CALL_UPDATE_TYPE_TO_VIDEO_FAILED = 2;
    public static final int MTC_CALL_UPDATE_TYPE_TO_AUDIO_OK = 3;
    public static final int MTC_CALL_UPDATE_TYPE_TO_AUDIO_FAILED = 4;
}
