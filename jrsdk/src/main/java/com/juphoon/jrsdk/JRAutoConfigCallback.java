package com.juphoon.jrsdk;

public interface JRAutoConfigCallback {
    /**
     * 自动配置全流程结果
     * @param result 配置结果
     * @param code   错误码
     */
    void onAutoConfigResult(boolean result, @JRAutoConfigConstants.AutoConfigError int code);

    /**
     *   配置过期，应重新发起自动配置
     */
    void onAutoConfigExpire();

    /**
     *  token过期，传入新token
     */
    String onAutoConfigAuthInd();
}
