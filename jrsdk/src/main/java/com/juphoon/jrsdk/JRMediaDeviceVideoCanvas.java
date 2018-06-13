package com.juphoon.jrsdk;

import android.content.Context;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.juphoon.cmcc.app.zmf.Zmf;
import com.juphoon.cmcc.app.zmf.ZmfVideo;

public class JRMediaDeviceVideoCanvas {
    private static Context mContext;

    public String videoSource;
    public SurfaceView view;
    public @JRMediaDeviceConstants.RenderType int renderType;

    static JRMediaDeviceVideoCanvas create(String videoSource, @JRMediaDeviceConstants.RenderType int type) {
        JRMediaDeviceVideoCanvas canvas = new JRMediaDeviceVideoCanvas();
        SurfaceView surfaceView = ZmfVideo.surfaceView(mContext);
        ZmfVideo.renderStart(surfaceView);
        FrameLayout.LayoutParams flp;
        flp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        surfaceView.setLayoutParams(flp);
        canvas.view = surfaceView;
        canvas.videoSource = videoSource;
        canvas.renderType = type;
        return canvas;
    }

    boolean switchCamera(String camera) {
        if (!camera()) {
            JRLog.printf("远端图像不支持切换摄像头");
            return false;
        }
        String oldCamera = videoSource;
        videoSource = camera;
        JRMediaDeviceParam.DealRender param = new JRMediaDeviceParam.DealRender();
        param.type = JRMediaDeviceDefine.MTC_SET_VIDEO_RENDER_TYPE_REPLACE;
        param.videoSource = videoSource;
        param.oldVideoSource = oldCamera;
        param.view = view;
        JRMediaDeviceResult result = ZmfEngine.getInstance().dealRender(param);
        return result.isSucceed;
    }

    boolean startRender() {
        JRMediaDeviceParam.DealRender param = new JRMediaDeviceParam.DealRender();
        param.view = view;
        param.type = JRMediaDeviceDefine.MTC_SET_VIDEO_RENDER_TYPE_START;
        param.videoSource = videoSource;
        param.renderType = renderType;
        JRMediaDeviceResult result = ZmfEngine.getInstance().dealRender(param);
        return result.isSucceed;
    }

    boolean pause() {
        JRMediaDeviceParam.DealRender param = new JRMediaDeviceParam.DealRender();
        param.view = view;
        param.type = JRMediaDeviceDefine.MTC_SET_VIDEO_RENDER_TYPE_STOP;
        JRMediaDeviceResult result = ZmfEngine.getInstance().dealRender(param);
        return result.isSucceed;
    }

    boolean camera() {
        if (videoSource.equalsIgnoreCase(ZmfVideo.CaptureBack()) || videoSource.equalsIgnoreCase(ZmfVideo.CaptureFront())) {
            return true;
        } else {
            return false;
        }
    }

}
