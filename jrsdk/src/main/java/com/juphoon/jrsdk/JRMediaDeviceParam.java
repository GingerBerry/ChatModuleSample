package com.juphoon.jrsdk;

import android.view.SurfaceView;

class JRMediaDeviceParam {

    public static class SetAudio {
        public boolean start;
    }

    public static class SetCamera {
        public @JRMediaDeviceDefine.MtcSetCameraType int type;
        public String camera;
        public int width;
        public int height;
        public int framerate;
    }

    public static class DealRender {
        public @JRMediaDeviceDefine.MtcSetVideoRenderType int type;
        public SurfaceView view;
        public String videoSource;
        public String oldVideoSource;
        public boolean autoRotate;
        public int rotateAngleToScreen;
        public @JRMediaDeviceDefine.MtcRenderType int renderType;
    }
}
