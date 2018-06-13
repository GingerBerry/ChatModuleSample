package com.juphoon.jrsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class JRNet {

    /**
     * 网络类型
     */
    @IntDef({UNAVAILABLE, WIFI, MOBILE, ETHERNET})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetType {
    }

    /**
     * 无网
     */
    static final int UNAVAILABLE = 0;
    /**
     * Wifi
     */
    static final int WIFI = 1;
    /**
     * 数据网
     */
    static final int MOBILE = 2;
    /**
     * 网线
     */
    static final int ETHERNET = 3;

    private Context mContext;
    private
    @NetType
    int mNetType;
    private List<JRNetCallback> mCallbacks = new ArrayList<>();

    static JRNet getInstance() {
        return NetworkManagerHolder.INSTANCE;
    }

    private BroadcastReceiver mNetworkChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateNetType();
        }
    };

    void initialize(Context appContext) {
        if (appContext == null) {
            throw new RuntimeException("Context should not be null!");
        }
        mContext = appContext.getApplicationContext();
        mContext.registerReceiver(mNetworkChangedReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        mNetType = getNetTypeInternal();
    }

    void uninitialize() {
        if (mContext != null) {
            mContext.getApplicationContext().unregisterReceiver(mNetworkChangedReceiver);
        }
    }

    @NetType
    int getNetType() {
        return mNetType;
    }

    /**
     * 设置回调
     *
     * @param callback 回调对象
     */
    void addCallback(JRNetCallback callback) {
        mCallbacks.add(callback);
    }

    /**
     * 删除回调
     *
     * @param callback 回调对象
     */
    void removeCallback(JRNetCallback callback) {
        mCallbacks.remove(callback);
    }

    void updateNetType() {
        int old = mNetType;
        mNetType = getNetTypeInternal();
        if (old != mNetType) {
            JRLog.printf("状态 %d->%d", old, mNetType);
            for (JRNetCallback h : mCallbacks) {
                h.onNetChange(mNetType, old);
            }
        }
    }

    @NetType
    private int getNetTypeInternal() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return UNAVAILABLE;
        }
        int netType = info.getType();

        if (netType == ConnectivityManager.TYPE_WIFI) {
            return WIFI;
        } else if (netType == ConnectivityManager.TYPE_MOBILE) {
            return MOBILE;
        } else if (netType == ConnectivityManager.TYPE_ETHERNET) {
            return ETHERNET;
        } else {
            return UNAVAILABLE;
        }
    }

    private static final class NetworkManagerHolder {
        private static final JRNet INSTANCE = new JRNet();
    }
}
