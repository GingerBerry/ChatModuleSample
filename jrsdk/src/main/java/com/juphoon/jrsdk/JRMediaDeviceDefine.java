package com.juphoon.jrsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class JRMediaDeviceDefine {
    /**
     *  渲染
     */
    @IntDef({MTC_SET_VIDEO_RENDER_TYPE_START, MTC_SET_VIDEO_RENDER_TYPE_STOP, MTC_SET_VIDEO_RENDER_TYPE_REPLACE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MtcSetVideoRenderType {

    }
    public static final int MTC_SET_VIDEO_RENDER_TYPE_START = 0;
    public static final int MTC_SET_VIDEO_RENDER_TYPE_STOP = 1;
    public static final int MTC_SET_VIDEO_RENDER_TYPE_REPLACE = 2;

    /**
     *  设置摄像头
     */
    @IntDef({MTC_SET_CAMERA_TYPE_START, MTC_SET_CAMERA_TYPE_STOP, MTC_SET_CAMERA_TYPE_SWITCH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MtcSetCameraType {

    }
    public static final int MTC_SET_CAMERA_TYPE_START = 0;
    public static final int MTC_SET_CAMERA_TYPE_STOP = 1;
    public static final int MTC_SET_CAMERA_TYPE_SWITCH = 2;

    /**
     *  渲染模式
     */
    @IntDef({MTC_RENDER_TYPE_AUTO, MTC_RENDER_TYPE_FULL_SCREEN, MTC_RENDER_TYPE_FULL_CONTENT, MTC_RENDER_TYPE_FULL_AUTO, MTC_RENDER_TYPE_MATCH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MtcRenderType {

    }
    public static final int MTC_RENDER_TYPE_AUTO = 0;
    public static final int MTC_RENDER_TYPE_FULL_SCREEN = 1;
    public static final int MTC_RENDER_TYPE_FULL_CONTENT = 2;
    public static final int MTC_RENDER_TYPE_FULL_AUTO = 3;
    public static final int MTC_RENDER_TYPE_MATCH = 4;
}
