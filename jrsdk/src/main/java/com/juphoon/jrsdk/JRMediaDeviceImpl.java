package com.juphoon.jrsdk;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

import com.juphoon.cmcc.app.zmf.ZmfVideo;

import java.util.ArrayList;
import java.util.List;

class JRMediaDeviceImpl extends JRMediaDevice {

    private List<JRMediaDeviceCallback> mCallbacks = new ArrayList<>();
    private List<JRMediaDeviceVideoCanvas> mLocalCanvas = new ArrayList<>();
    private List<JRMediaDeviceVideoCanvas> mRemoteCanvas = new ArrayList<>();
    private String mCamera;
    private boolean mAudioStart;
    private AudioManager mAudioManager;
    private boolean mSpeakerOn;

    JRMediaDeviceImpl() {
        mAudioManager = (AudioManager) JRClient.getInstance().getContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public JRMediaDeviceVideoCanvas startCameraVideo(@JRMediaDeviceConstants.RenderType int type) {
        JRMediaDeviceVideoCanvas canvas = JRMediaDeviceVideoCanvas.create(ZmfVideo.CaptureFront(), type);
        startCamera();
        canvas.startRender();
        mLocalCanvas.add(canvas);
        return canvas;
    }

    @Override
    public JRMediaDeviceVideoCanvas startVideo(String videoSource, @JRMediaDeviceConstants.RenderType int type) {
        JRMediaDeviceVideoCanvas canvas = JRMediaDeviceVideoCanvas.create(videoSource, type);
        JRLog.printf("开始渲染 %s", videoSource);
        canvas.startRender();
        mRemoteCanvas.add(canvas);
        return canvas;
    }

    @Override
    public void stopVideo(JRMediaDeviceVideoCanvas canvas) {
        if (canvas == null) {
            JRLog.printf("canvas 不存在");
            return;
        }
        canvas.pause();
        if (mLocalCanvas.contains(canvas)) {
            mLocalCanvas.remove(canvas);
            if (mLocalCanvas.size() == 0) {
                JRLog.printf("无本地视频对象");
                stopCamera();
            }
        } else {
            mRemoteCanvas.remove(canvas);
        }
    }

    @Override
    public boolean switchCamera() {
        if (mCamera.isEmpty()) {
            JRLog.printf("摄像头未打开");
            return false;
        }
        JRMediaDeviceParam.SetCamera param = new JRMediaDeviceParam.SetCamera();
        param.type = JRMediaDeviceDefine.MTC_SET_CAMERA_TYPE_SWITCH;
        if (mCamera.equalsIgnoreCase(ZmfVideo.CaptureFront())) {
            mCamera = ZmfVideo.CaptureBack();
        } else {
            mCamera = ZmfVideo.CaptureFront();
        }
        param.camera = mCamera;
        if (!JRClient.getInstance().getCurrentUser().isEmpty()) {
            // TODO: 2018/6/1
            String resolution = JRAccount.getInstance().getAccountConfig(JRClient.getInstance().getCurrentUser(), JRAccountDefine.ConfigKey.VideoWAndH);
        } else {
            param.width = 640;
            param.height = 480;
            param.framerate = 30;
        }
        JRMediaDeviceResult result = ZmfEngine.getInstance().setCamera(param);
        if (result.isSucceed) {
            for (JRMediaDeviceVideoCanvas canvas : mLocalCanvas) {
                canvas.switchCamera(mCamera);
            }
        } else {
            for (JRMediaDeviceCallback callback : mCallbacks) {
                callback.onCameraTypeChange();
            }
        }
        return result.isSucceed;
    }

    @Override
    public boolean enableSpeaker(boolean enable) {
        if (mSpeakerOn != enable) {
            mSpeakerOn = enable;
            mAudioManager.setSpeakerphoneOn(mSpeakerOn);
            for (JRMediaDeviceCallback callback : mCallbacks) {
                callback.onAudioOutputTypeChange();
            }
        }
        return true;
    }

    @Override
    public boolean isSpeakerOn() {
        return mSpeakerOn;
    }

    @Override
    public void addCallback(JRMediaDeviceCallback callback) {
        if (!mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    @Override
    public void removeCallback(JRMediaDeviceCallback callback) {
        mCallbacks.remove(callback);
    }

    @Override
    boolean startAudio() {
        if (mAudioStart) {
            JRLog.printf("音频设备已经打开");
            return true;
        }
        JRMediaDeviceParam.SetAudio param = new JRMediaDeviceParam.SetAudio();
        param.start = true;
        JRMediaDeviceResult result = ZmfEngine.getInstance().setAudio(param);
        mAudioStart = result.isSucceed;
        mAudioManager.setMode(getAudioMode());
        return result.isSucceed;
    }

    @Override
    boolean stopAudio() {
        if (!mAudioStart) {
            JRLog.printf("音频设备已经关闭");
            return true;
        }
        JRMediaDeviceParam.SetAudio param = new JRMediaDeviceParam.SetAudio();
        param.start = false;
        JRMediaDeviceResult result = ZmfEngine.getInstance().setAudio(param);
        mAudioStart = false;
        mAudioManager.setMode(AudioManager.MODE_NORMAL);
        return result.isSucceed;
    }

    @Override
    boolean startCamera() {
        if (!mCamera.isEmpty()) {
            JRLog.printf("摄像头已经打开");
            return true;
        }
        JRMediaDeviceParam.SetCamera param = new JRMediaDeviceParam.SetCamera();
        param.type = JRMediaDeviceDefine.MTC_SET_CAMERA_TYPE_START;
        param.camera = ZmfVideo.CaptureFront();
        if (!JRClient.getInstance().getCurrentUser().isEmpty()) {
            // TODO: 2018/6/1
            String resolution = JRAccount.getInstance().getAccountConfig(JRClient.getInstance().getCurrentUser(), JRAccountDefine.ConfigKey.VideoWAndH);
        } else {
            param.width = 640;
            param.height = 480;
            param.framerate = 30;
        }
        JRMediaDeviceResult result = new JRMediaDeviceResult();
        if (result.isSucceed) {
            mCamera = param.camera;
            for (JRMediaDeviceCallback callback : mCallbacks) {
                callback.onCameraTypeChange();
            }
        }

        return result.isSucceed;
    }

    @Override
    boolean stopCamera() {
        if (mCamera.isEmpty()) {
            JRLog.printf("摄像头已经关闭");
            return true;
        }
        JRMediaDeviceParam.SetCamera param = new JRMediaDeviceParam.SetCamera();
        param.type = JRMediaDeviceDefine.MTC_SET_CAMERA_TYPE_STOP;
        JRMediaDeviceResult result = ZmfEngine.getInstance().setCamera(param);
        if (result.isSucceed) {
            mCamera = null;
            for (JRMediaDeviceCallback callback : mCallbacks) {
                callback.onCameraTypeChange();
            }
        }
        return result.isSucceed;
    }

    @Override
    String camera() {
        return mCamera;
    }

    private int getAudioMode() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return AudioManager.MODE_NORMAL;
        }
        return AudioManager.MODE_IN_COMMUNICATION;
    }
}
