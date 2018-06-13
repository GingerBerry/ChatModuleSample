package com.juphoon.jrsdk;

public interface JRClientCallback {
    void onClientInitResult(boolean result, @JRClientConstants.SDKInitReason int reason);

    void onClientLoginResult(boolean result, @JRClientConstants.RegError int reason);

    void onClientLogoutResult(@JRClientConstants.RegError int reason);

    void onClientStateChange(@JRClientConstants.ClientState int state);
}
