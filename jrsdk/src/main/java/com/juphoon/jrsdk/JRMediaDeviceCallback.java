package com.juphoon.jrsdk;

public interface JRMediaDeviceCallback {
    void onCameraTypeChange();

    void onAudioOutputTypeChange();

    void onRenderStart(JRMediaDeviceVideoCanvas canvas);
}
