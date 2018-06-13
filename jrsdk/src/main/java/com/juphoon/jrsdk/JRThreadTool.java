package com.juphoon.jrsdk;

import android.os.Handler;
import android.os.Looper;

class JRThreadTool {
    public static JRThreadTool getInstance() {
        return MainThreadHolder.INSTANCE;
    }

    private Handler mHandler;


    private JRThreadTool() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public void postDelayed(Runnable runnable, long delayMillis) {
        mHandler.postDelayed(runnable, delayMillis);
    }

    public void removeRunnable(Runnable runnable) {
        mHandler.removeCallbacks(runnable);
    }

    private static final class MainThreadHolder {
        private static final JRThreadTool INSTANCE = new JRThreadTool();
    }
}
