package com.juphoon.jrsdk;

/**
 *
 */
public abstract class JRMediaDevice {

    private static JRMediaDevice sMediaDevice;

    public static JRMediaDevice getInstance() {
        if (sMediaDevice == null) {
            sMediaDevice = new JRMediaDeviceImpl();
        }
        return sMediaDevice;
    }

    /**
     * 开启本地预览
     * @param type 本地预览渲染模式
     * @return JRMediaDeviceVideoCanvas对象
     */
    public abstract JRMediaDeviceVideoCanvas startCameraVideo(@JRMediaDeviceConstants.RenderType int type);

    /**
     * 开启远端图像渲染
     * @param videoSource 渲染源
     * @param type 渲染模式
     * @return JRMediaDeviceVideoCanvas对象
     */
    public abstract JRMediaDeviceVideoCanvas startVideo(String videoSource, @JRMediaDeviceConstants.RenderType int type);

    /**
     * 停止渲染
     * @param canvas 要停止渲染的canvas对象
     */
    public abstract void stopVideo(JRMediaDeviceVideoCanvas canvas);

    /**
     * 切换摄像头
     * @return 接口调用成功失败
     */
    public abstract boolean switchCamera();

    /**
     * 设置扬声器
     * @param enable 打开关闭
     * @return 接口调用成功失败
     */
    public abstract boolean enableSpeaker(boolean enable);

    /**
     * 扬声器是否打开
     * @return 扬声器是否打开
     */
    public abstract boolean isSpeakerOn();

    /**
     * 添加回调
     * @param callback 回调代理
     */
    public abstract void addCallback(JRMediaDeviceCallback callback);

    /**
     * 移除回调
     * @param callback 回调代理
     */
    public abstract void removeCallback(JRMediaDeviceCallback callback);

    /**
     * 开启音频设备
     * @return 接口调用成功失败
     */
    abstract boolean startAudio();

    /**
     * 关闭音频设备
     * @return 接口调用成功失败
     */
    abstract boolean stopAudio();

    /**
     * 开启摄像头
     * @return 接口调用成功失败
     */
    abstract boolean startCamera();

    /**
     * 关闭摄像头
     * @return 接口调用成功失败
     */
    abstract boolean stopCamera();

    /**
     * 当前使用的摄像头，为空即是未打开
     * @return 摄像头
     */
    abstract String camera();
}
