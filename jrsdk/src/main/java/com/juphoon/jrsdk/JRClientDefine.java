package com.juphoon.jrsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class JRClientDefine {
    /**
     * 设置业务token
     */
    @IntDef({MTC_CLIENT_SET_TOKEN_TYPE_MULTI_VIDEO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MtcClientSetTokenType {

    }
    /**
     * 多方视频
     */
    public static final int MTC_CLIENT_SET_TOKEN_TYPE_MULTI_VIDEO = 0;
}
