package com.juphoon.jrsdk;

public interface JRNetCallback {
    /**
     * 网络变化
     *
     * @param newNetType 当前网络类型
     * @param oldNetType 之前网络类型
     */
    void onNetChange(int newNetType, int oldNetType);
}
