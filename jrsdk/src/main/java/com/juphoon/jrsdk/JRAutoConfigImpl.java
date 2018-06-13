package com.juphoon.jrsdk;

import android.text.TextUtils;

import com.juphoon.cmcc.app.lemon.MtcCp;
import com.juphoon.cmcc.app.lemon.MtcCpConstants;

import java.util.ArrayList;

class JRAutoConfigImpl extends JRAutoConfig implements MtcNotifyListener {
    private int mState;

    private ArrayList<JRAutoConfigCallback> mCallbacks = new ArrayList<>();

    JRAutoConfigImpl() {
        MtcEngine.getInstance().addCallback(this);
    }

    @Override
    public @JRAutoConfigConstants.AutoConfigState int getState() {
        return mState;
    }

    @Override
    public boolean startAutoConfig(String account, String password, String token) {
        if (mState == JRAutoConfigConstants.AUTO_CONFIG_STATE_RUNNING) {
            JRLog.printf("正在进行自动配置");
            return false;
        }
        int netType = JRNet.getInstance().getNetType();
        if (netType == JRNet.UNAVAILABLE) {
            JRLog.printf("自动配置失败 无网络");
            return false;
        }
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password) || TextUtils.isEmpty(token)) {
            JRLog.printf("参数不完整");
        }
        JRAutoConfigParam.Start param = new JRAutoConfigParam.Start();
        param.account = account;
        param.password = password;
        param.token = token;
        JRAutoConfigResult.Start result = MtcEngine.getInstance().startAutoConfig(param);
        if (result.isSucceed) {
            mState = JRAutoConfigConstants.AUTO_CONFIG_STATE_RUNNING;
        }
        return result.isSucceed;
    }

    @Override
    public boolean stopAutoConfig() {
        if (mState == JRAutoConfigConstants.AUTO_CONFIG_STATE_RUNNING) {
            mState = JRAutoConfigConstants.AUTO_CONFIG_STATE_FAILED;
            notifyResult(false, MtcCpConstants.MTC_CP_STAT_ERR_NO);
        }
        JRLog.printf("结束自动配置");
        return true;
    }

    @Override
    public void addCallback(JRAutoConfigCallback callback) {
        if (!mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    @Override
    public void removeCallback(JRAutoConfigCallback callback) {
        mCallbacks.remove(callback);
    }

    @Override
    public void onNotify(JRBaseNotify notify) {
        if (notify.type != JRBaseNotify.NotifyType.AutoConfig) {
            return;
        }
        if (notify instanceof JRAutoConfigNotify.Result) {
            JRAutoConfigNotify.Result resultNotify = (JRAutoConfigNotify.Result)notify;
            if (resultNotify.isSucceed) {
                mState = JRAutoConfigConstants.AUTO_CONFIG_STATE_OK;
            } else {
                mState = JRAutoConfigConstants.AUTO_CONFIG_STATE_FAILED;
            }
            notifyResult(resultNotify.isSucceed, resultNotify.errorCode);
        } else if (notify instanceof JRAutoConfigNotify.AuthInd) {
            notifyAuthInd();
        } else if (notify instanceof JRAutoConfigNotify.Expire) {
            notifyExpire();
        }
    }

    private void notifyResult(boolean result, int errorCode) {
        for (JRAutoConfigCallback callback : mCallbacks) {
            callback.onAutoConfigResult(result, converErrorCode(errorCode));
        }
    }

    private void notifyExpire() {
        for (JRAutoConfigCallback callback : mCallbacks) {
            callback.onAutoConfigExpire();
        }
    }

    private void notifyAuthInd() {
        for (JRAutoConfigCallback callback : mCallbacks) {
            JRAutoConfigParam.SetToken param = new JRAutoConfigParam.SetToken();
            param.token = callback.onAutoConfigAuthInd();
            MtcEngine.getInstance().setCpToken(param);
        }
    }

    private static int converErrorCode(int errorCode) {
        switch (errorCode) {
            case MtcCp.MTC_CP_STAT_ERR_NO:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_NONE;
            case MtcCp.MTC_CP_STAT_ERR_TIMEOUT:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_TIMEOUT;
            case MtcCp.MTC_CP_STAT_ERR_NETWORK:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_NETWORK;
            case MtcCp.MTC_CP_STAT_ERR_FORBIDDEN:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_FORBIDDEN;
            case MtcCp.MTC_CP_STAT_ERR_INTERNAL_ERR:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_INTERNAL_ERROR;
            case MtcCp.MTC_CP_STAT_ERR_INCORRET_XML:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_INCORRET_XML;
            case MtcCp.MTC_CP_STAT_ERR_DISABLED_TEMP:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_DISABLED_TEMP;
            case MtcCp.MTC_CP_STAT_ERR_DISABLED_PERM:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_DISABLED_PERM;
            case MtcCp.MTC_CP_STAT_ERR_DECLINE:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_DECLINE;
            case MtcCp.MTC_CP_STAT_ERR_INVALID_OTP:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_INVALID_OTP;
            case MtcCp.MTC_CP_STAT_ERR_INVALID_TOKEN:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_INVALID_TOKEN;
            case MtcCp.MTC_CP_STAT_ERR_INVALID_NUMBER:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_INVALID_NUMBER;
            case MtcCp.MTC_CP_STAT_ERR_RETRY_AFTER:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_RETRY_AFTER;
            case MtcCp.MTC_CP_STAT_ERR_BOSS_ERROR:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_BOSS_ERROR;
            case MtcCp.MTC_CP_STAT_ERR_NO_WHITE_USER:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_NO_WHITE_USER;
            case MtcCp.MTC_CP_STAT_ERR_BOSS_TIMEOUT:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_BOSS_TIMEOUT;
            case MtcCp.MTC_CP_STAT_ERR_PROMPT_TIMEOUT:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_PROMPT_TIMEOUT;
            case MtcCp.MTC_CP_STAT_ERR_HAS_BODY:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_HAS_BODY;
            case MtcCp.MTC_CP_STAT_ERR_OTHER:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_OTHER;
            default:
                return JRAutoConfigConstants.AUTO_CONFIG_ERROR_NONE;
        }
    }
}
