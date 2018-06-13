package com.juphoon.jrsdk;

import android.content.Context;

public abstract class JRClient {

    private static JRClient sClient;

    public static JRClient getInstance() {
        if (sClient == null) {
            sClient = new JRClientImpl();
        }
        return sClient;
    }

    /**
     * 获取当前open的账号
     * <p>
     * return 返回账号名
     */
    public abstract String getCurrentUser();

    /**
     * 获取当前登录的号码
     * <p>
     * return 返回账号名
     */
    public abstract String getCurrentNumber();

    /**
     * 获取当前登录的号码
     * <p>
     * return 返回账号名
     */
    public abstract @JRClientConstants.ClientState int getState();

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public abstract void startInitSDK(Context context);

    /**
     * 注册
     *
     * @param accountName 帐号名
     * @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean login(String accountName);

    /**
     * 注销
     *
     * @return 返回 true 表示正常执行调用流程，false 表示调用异常，异常错误通过 JRClientCallback 通知
     */
    public abstract boolean logout();

    /**
     * 获取上下文
     *
     * @return Context
     */
    public abstract Context getContext();

    /**
     * 添加回调
     *
     * @param callback JRClientCallback 接口对象
     */
    public abstract void addCallback(JRClientCallback callback);

    /**
     * 删除回调
     */
    public abstract void removeCallback(JRClientCallback callback);
}
