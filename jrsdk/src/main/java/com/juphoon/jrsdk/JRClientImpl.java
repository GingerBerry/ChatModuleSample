package com.juphoon.jrsdk;

import android.content.Context;
import android.text.TextUtils;

import com.juphoon.cmcc.app.lemon.MtcCli;

import java.util.ArrayList;

class JRClientImpl extends JRClient implements MtcNotifyListener {

    private ArrayList<JRClientCallback> mCallbacks = new ArrayList<>();
    Context mContext;

    JRClientImpl()  {
        MtcEngine.getInstance().addCallback(this);
    }

    @Override
    public String getCurrentUser() {
        JRClientResult.GetUser result = MtcEngine.getInstance().getCurrentUser();
        return result.user;
    }

    @Override
    public String getCurrentNumber() {
        JRClientResult.GetNumber result = MtcEngine.getInstance().getCurrentNumber();
        return result.number;
    }

    @Override
    public @JRClientConstants.ClientState int getState() {
        JRClientResult.GetState result = MtcEngine.getInstance().getState();
        return converFormMtcState(result.state);
    }

    @Override
    public void startInitSDK(Context context) {
        mContext = context;
    }

    @Override
    public boolean login(String accountName) {
        if (TextUtils.isEmpty(accountName)) {
            JRLog.printf("账号名为空");
        }
        JRClientParam.Login param = new JRClientParam.Login();
        param.accountName = accountName;
        JRClientResult.Login result = MtcEngine.getInstance().login(param);
        if (!result.isSucceed) {

        }
        return result.isSucceed;
    }

    @Override
    public boolean logout() {
        JRClientResult.Logout result = MtcEngine.getInstance().logout();
        return result.isSucceed;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void addCallback(JRClientCallback callback) {
        if (!mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    @Override
    public void removeCallback(JRClientCallback callback) {
        mCallbacks.remove(callback);
    }


    @Override
    public void onNotify(JRBaseNotify notify) {
        if (notify.type != JRBaseNotify.NotifyType.Client) {
            return;
        }
        if (notify instanceof JRClientNotify.Register) {
            JRClientNotify.Register clientNotify = (JRClientNotify.Register)notify;
            int newState = converFormMtcState(clientNotify.state);
            for (JRClientCallback callback : mCallbacks) {
                callback.onClientStateChange(newState);
            }
            if (newState == JRClientConstants.CLIENT_STATE_LOGINED) {
                for (JRClientCallback callback : mCallbacks) {
                    callback.onClientLoginResult(true, converFormMtcReason(clientNotify.reason));
                }
            } else if (newState == JRClientConstants.CLIENT_STATE_IDLE) {
                for (JRClientCallback callback : mCallbacks) {
                    callback.onClientLogoutResult(converFormMtcReason(clientNotify.reason));
                }
            }
        }
    }

    private static int converFormMtcState(int state) {
        switch (state) {
            case MtcCli.MTC_REG_STATE_IDLE:
                return JRClientConstants.CLIENT_STATE_IDLE;
            case MtcCli.MTC_REG_STATE_REGING:
                return JRClientConstants.CLIENT_STATE_LOGINING;
            case MtcCli.MTC_REG_STATE_REGED:
                return JRClientConstants.CLIENT_STATE_LOGINED;
            case MtcCli.MTC_REG_STATE_UNREGING:
                return JRClientConstants.CLIENT_STATE_LOGOUTING;
            default:
                return JRClientConstants.CLIENT_STATE_UNKNOW;
        }
    }

    private static int converFormMtcReason(int errorCode) {
        switch (errorCode) {
            case MtcCli.MTC_CLI_ERR_NO:
                return JRClientConstants.REG_ERROR_NONE;
            case MtcCli.MTC_CLI_REG_ERR_SEND_MSG:
                return JRClientConstants.REG_ERROR_SEND_MESSAGE_ERROR;
            case MtcCli.MTC_CLI_REG_ERR_AUTH_FAILED:
                return JRClientConstants.REG_ERROR_AUTHENTICATION_FAILED;
            case MtcCli.MTC_CLI_REG_ERR_INVALID_USER:
                return JRClientConstants.REG_ERROR_INVALID_USER;
            case MtcCli.MTC_CLI_REG_ERR_TIMEOUT:
                return JRClientConstants.REG_ERROR_TIMEOUT;
            case MtcCli.MTC_CLI_REG_ERR_SERV_BUSY:
                return JRClientConstants.REG_ERROR_SERVER_BUSY;
            case MtcCli.MTC_CLI_REG_ERR_SERV_NOT_REACH:
                return JRClientConstants.REG_ERROR_SERVER_NOT_REACHED;
            case MtcCli.MTC_CLI_REG_ERR_SRV_FORBIDDEN:
                return JRClientConstants.REG_ERROR_SERVER_FORBIDDEN;
            case MtcCli.MTC_CLI_REG_ERR_SRV_UNAVAIL:
                return JRClientConstants.REG_ERROR_SERVER_UNAVAILABLE;
            case MtcCli.MTC_CLI_REG_ERR_DNS_QRY:
                return JRClientConstants.REG_ERROR_DNS_QUERY_FAILED;
            case MtcCli.MTC_CLI_REG_ERR_NETWORK:
                return JRClientConstants.REG_ERROR_NETWORK_ERROR;
            case MtcCli.MTC_CLI_REG_ERR_DEACTED:
                return JRClientConstants.REG_ERROR_DEACTIVED;
            case MtcCli.MTC_CLI_REG_ERR_PROBATION:
                return JRClientConstants.REG_ERROR_PROBATION;
            case MtcCli.MTC_CLI_REG_ERR_INTERNAL:
                return JRClientConstants.REG_ERROR_INTERNAL_ERROR;
            case MtcCli.MTC_CLI_REG_ERR_NO_RESOURCE:
                return JRClientConstants.REG_ERROR_NO_RESOURCE;
            case MtcCli.MTC_CLI_REG_ERR_REJECTED:
                return JRClientConstants.REG_ERROR_REJECTED;
            case MtcCli.MTC_CLI_REG_ERR_OTHER:
                return JRClientConstants.REG_ERROR_OTHER_ERROR;
            case MtcCli.MTC_CLI_REG_ERR_SIP_SESS:
                return JRClientConstants.REG_ERROR_SIP_SESSION_ERROR;
            case MtcCli.MTC_CLI_REG_ERR_UNREG:
                return JRClientConstants.REG_ERROR_UNREGISTER_ERROR;
            case MtcCli.MTC_CLI_REG_ERR_INVALID_ADDR:
                return JRClientConstants.REG_ERROR_INVALID_IP_ADDR;
            case MtcCli.MTC_CLI_REG_ERR_WAIT_PWD:
                return JRClientConstants.REG_ERROR_WAIT_PROMPT_PASSWORD_TIMEOUT;
            case MtcCli.MTC_CLI_REG_ERR_NOT_FOUND:
                return JRClientConstants.REG_ERROR_NOT_FOUND_USER;
            case MtcCli.MTC_CLI_REG_ERR_AUTH_REJECT:
                return JRClientConstants.REG_ERROR_AUTHENTICATION_REJECTED;
            case MtcCli.MTC_CLI_REG_ERR_ID_NOT_MATCH:
                return JRClientConstants.REG_ERROR_ID_NOT_MATCH;
            case MtcCli.MTC_CLI_REG_ERR_USER_NOT_EXIST:
                return JRClientConstants.REG_ERROR_USER_NOT_EXIST;
            default:
                return JRClientConstants.REG_ERROR_OTHER_ERROR;
        }
    }
}
