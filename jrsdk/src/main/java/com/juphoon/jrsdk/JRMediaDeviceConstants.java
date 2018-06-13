package com.juphoon.jrsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JRMediaDeviceConstants {
    /**
     *  渲染模式
     */
    @IntDef({RENDER_TYPE_AUTO, RENDER_TYPE_FULL_SCREEN, RENDER_TYPE_FULL_CONTENT, RENDER_TYPE_FULL_AUTO, RENDER_TYPE_MATCH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RenderType {

    }
    public static final int RENDER_TYPE_AUTO = 0;
    public static final int RENDER_TYPE_FULL_SCREEN = 1;
    public static final int RENDER_TYPE_FULL_CONTENT = 2;
    public static final int RENDER_TYPE_FULL_AUTO = 3;
    public static final int RENDER_TYPE_MATCH = 4;
}
