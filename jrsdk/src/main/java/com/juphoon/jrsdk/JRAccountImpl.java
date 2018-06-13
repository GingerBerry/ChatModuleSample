package com.juphoon.jrsdk;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JRAccountImpl extends JRAccount {
    @Override
    public boolean createAccount(String accountName) {
        if (TextUtils.isEmpty(accountName)) {
            JRLog.printf("账号为空");
            return false;
        }
        if (JRClient.getInstance().getState() == JRClientConstants.CLIENT_STATE_IDLE || JRClient.getInstance().getState() == JRClientConstants.CLIENT_STATE_UNKNOW) {
            JRAccountParam.Create param = new JRAccountParam.Create();
            param.accountName = accountName;
            JRAccountResult.Create result = MtcEngine.getInstance().createAccount(param);
            return result.isSucceed;
        } else {
            JRLog.printf("创建账号失败");
            return false;
        }
    }

    @Override
    public boolean deleteAccount(String accountName) {
        if (TextUtils.isEmpty(accountName)) {
            JRLog.printf("账号为空");
            return false;
        }
        if (JRClient.getInstance().getCurrentUser().equalsIgnoreCase(accountName) && JRClient.getInstance().getState() != JRClientConstants.CLIENT_STATE_IDLE) {
            JRLog.printf("不能删除登录状态的账号");
            return false;
        } else {
            JRAccountParam.Delete param = new JRAccountParam.Delete();
            param.accountName = accountName;
            JRAccountResult.Delete result = MtcEngine.getInstance().deleteAccount(param);
            return result.isSucceed;
        }
    }

    @Override
    public boolean setAccountConfig(String accountName, Map<JRAccountDefine.ConfigKey, String> extraParams) {
        if (TextUtils.isEmpty(accountName)) {
            JRLog.printf("账号为空");
            return false;
        }
        if (!JRClient.getInstance().getCurrentUser().equalsIgnoreCase(accountName) && JRClient.getInstance().getState() == JRClientConstants.CLIENT_STATE_LOGINED) {
            JRLog.printf("不能设置非打开状态的账号");
            return false;
        } else {
            JRAccountParam.SetConfig param = new JRAccountParam.SetConfig();
            param.accountName = accountName;
            param.extraParams = extraParams;
            return MtcEngine.getInstance().setAccountConfig(param).isSucceed;
        }
    }

    @Override
    public boolean setAccountConfig(String accountName, JRAccountDefine.ConfigKey constants, String value) {
        if (TextUtils.isEmpty(accountName)) {
            JRLog.printf("账号为空");
            return false;
        }
        if (!JRClient.getInstance().getCurrentUser().equalsIgnoreCase(accountName) && JRClient.getInstance().getState() == JRClientConstants.CLIENT_STATE_LOGINED) {
            JRLog.printf("不能设置非打开状态的账号");
            return false;
        } else {
            JRAccountParam.SetConfig param = new JRAccountParam.SetConfig();
            Map<JRAccountDefine.ConfigKey, String> extraParams = new HashMap<>();
            extraParams.put(constants, value);
            param.accountName = accountName;
            param.extraParams = extraParams;
            return MtcEngine.getInstance().setAccountConfig(param).isSucceed;
        }
    }

    @Override
    public String getAccountConfig(String accountName, JRAccountDefine.ConfigKey paramKey) {
        if (TextUtils.isEmpty(accountName)) {
            JRLog.printf("账号为空");
            return null;
        }
        if (!JRClient.getInstance().getCurrentUser().equalsIgnoreCase(accountName) && JRClient.getInstance().getState() == JRClientConstants.CLIENT_STATE_LOGINED) {
            JRLog.printf("不能获取非打开状态账号的配置");
            return null;
        } else {
            JRAccountParam.GetConfig param = new JRAccountParam.GetConfig();
            param.accountName = accountName;
            param.key = paramKey;
            return MtcEngine.getInstance().getAccountConfig(param).value;
        }
    }

    @Override
    public List<String> getAccountsList() {
        JRAccountResult.GetList result = MtcEngine.getInstance().getAccountList();
        return result.accountList;
    }
}
