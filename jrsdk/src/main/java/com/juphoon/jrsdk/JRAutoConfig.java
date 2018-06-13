package com.juphoon.jrsdk;

public abstract class JRAutoConfig {

    private static JRAutoConfig sAutoConfig;

    public static JRAutoConfig getInstance() {
        if (sAutoConfig == null) {
            sAutoConfig = new JRAutoConfigImpl();
        }
        return sAutoConfig;
    }

    /**
     * 获取当前自动配置状态
     * @return 自动配置状态
     */
    public abstract @JRAutoConfigConstants.AutoConfigState int getState();

    /**
     * 发起自动配置
     * @return 成功返回 true，失败返回 false
     */
    public abstract boolean startAutoConfig(String account, String password, String token);

    /**
     * 停止自动配置
     * @return 接口调用成功失败
     */
    public abstract boolean stopAutoConfig();

    /**
     * 注册回调
     * @param callback 回调代理
     */
    public abstract void addCallback(JRAutoConfigCallback callback);

    /**
     * 移除回调
     * @param callback 回调代理
     */
    public abstract void removeCallback(JRAutoConfigCallback callback);
}
