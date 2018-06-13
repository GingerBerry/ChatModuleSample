package com.juphoon.jrsdk;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;

import com.juphoon.cmcc.app.zmf.ZmfAudio;
import com.juphoon.cmcc.app.zmf.ZmfVideo;

class ZmfEngine {
    static ZmfEngine getInstance() {
        return ZmfEngineHolder.INSTANCE;
    }

    public void initialize(Context context) {
        ZmfAudio.initialize(context);
        ZmfVideo.initialize(context);
    }

    public void uninitialize() {
        ZmfAudio.terminate();
        ZmfVideo.terminate();
    }

    public JRMediaDeviceResult setAudio(JRMediaDeviceParam.SetAudio param) {
        if (param.start) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    synchronized (ZmfEngine.this) {
                        String outputDevice = getAudioOutput();
                        int ret = ZmfAudio.outputStart(outputDevice, 16000, 0);
                        if (ret != 0) {
                            ret = ZmfAudio.outputStart(outputDevice, 44100, 0);
                        }

                        if (ret == 0) {
                            ret = ZmfAudio.inputStart(getAudioInput(), 16000, 0,
                                    ZmfAudio.AEC_ON,
                                    ZmfAudio.AGC_ON);
                            if (ret != 0) {
                                ret = ZmfAudio.inputStart(getAudioInput(), 44100, 0,
                                        ZmfAudio.AEC_ON,
                                        ZmfAudio.AGC_ON);
                            }
                        }
                        if (ret != 0) {
                            ZmfAudio.inputStopAll();
                            ZmfAudio.outputStopAll();
                        }
                    }
                    return null;
                }
            }.execute();

        } else {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    synchronized (ZmfEngine.this) {
                        ZmfAudio.inputStopAll();
                        ZmfAudio.outputStopAll();
                    }
                    return null;
                }
            }.execute();
        }
        JRMediaDeviceResult result = new JRMediaDeviceResult();
        result.isSucceed = true;
        return result;
    }

    public JRMediaDeviceResult setCamera(JRMediaDeviceParam.SetCamera param) {
        if (param.type == JRMediaDeviceDefine.MTC_SET_CAMERA_TYPE_SWITCH) {
            ZmfVideo.captureStopAll();
            ZmfVideo.captureStart(param.camera, param.width, param.height, param.framerate);
        } else if (param.type == JRMediaDeviceDefine.MTC_SET_CAMERA_TYPE_START) {
            ZmfVideo.captureStart(param.camera, param.width, param.height, param.framerate);
        } else if (param.type == JRMediaDeviceDefine.MTC_SET_CAMERA_TYPE_STOP) {

            ZmfVideo.captureStopAll();
        }
        JRMediaDeviceResult result = new JRMediaDeviceResult();
        result.isSucceed = true;
        return result;
    }

    public JRMediaDeviceResult dealRender(JRMediaDeviceParam.DealRender param) {
        if (param.type == JRMediaDeviceDefine.MTC_SET_VIDEO_RENDER_TYPE_START) {
            ZmfVideo.renderStart(param.view);
            ZmfVideo.renderAdd(param.view, param.videoSource, 0, param.renderType);
            ZmfVideo.renderRotate(param.view, param.autoRotate ? ZmfVideo.ROTATION_AUTO_SCREEN : ZmfVideo.ROTATION_FIXED_SCREEN);
        } else if (param.type == JRMediaDeviceDefine.MTC_SET_VIDEO_RENDER_TYPE_STOP) {
            ZmfVideo.renderStop(param.view);
            ZmfVideo.renderRemoveAll(param.view);
        } else if (param.type == JRMediaDeviceDefine.MTC_SET_VIDEO_RENDER_TYPE_REPLACE) {
            ZmfVideo.renderReplace(param.view, param.oldVideoSource, param.videoSource);
        }
        JRMediaDeviceResult result = new JRMediaDeviceResult();
        result.isSucceed = true;
        return result;
    }

    private static final class ZmfEngineHolder {
        private static final ZmfEngine INSTANCE = new ZmfEngine();
    }

    private static String getAudioOutput() {
        return ZmfAudio.OUTPUT_VOICE_CALL;
    }

    private static String getAudioInput() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return ZmfAudio.INPUT_MIC;
        }
        return ZmfAudio.INPUT_VOICE_COMMUNICATION;
    }
}
