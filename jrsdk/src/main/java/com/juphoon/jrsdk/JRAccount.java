package com.juphoon.jrsdk;

import java.util.List;
import java.util.Map;

public abstract class JRAccount {

    private static JRAccount sAccount;

    public static JRAccount getInstance() {
        if (sAccount == null) {
            sAccount = new JRAccountImpl();
        }
        return sAccount;
    }

    /**
     * 创建一个账号
     *
     * @param accountName 账号名称
     * @return 成功返回 true，失败返回 false
     */
    public abstract boolean createAccount(String accountName);

    /**
     * 删除帐号
     *
     * @param accountName 帐号名
     */
    public abstract boolean deleteAccount(String accountName);

    /**
     * 设置帐号配置
     *
     * @param accountName 帐号名
     * @param extraParams 帐号配置参数
     * @return 成功返回 true，失败返回 false
     */
    public abstract boolean setAccountConfig(String accountName, Map<JRAccountDefine.ConfigKey, String> extraParams);

    public abstract boolean setAccountConfig(String accountName, JRAccountDefine.ConfigKey constants, String value);

    /**
     * 获取帐号配置参数
     *
     * @param accountName 帐号名
     * @param paramKey    帐号配置
     * @return 配置参数
     */
    public abstract String getAccountConfig(String accountName, JRAccountDefine.ConfigKey paramKey);


    /**
     * 获取帐号列表
     *
     * @return 帐号集合
     */
    public abstract List<String> getAccountsList();
}
