package com.juphoon.jrsdk;

import java.util.ArrayList;

public abstract class JRGroup {
    private static JRGroup sInstance;

    /**
     * 获取单例
     *
     * @return
     */
    public static JRGroup getInstance() {
        if (sInstance == null) {
            sInstance = new JRGroupImpl();
        }
        return sInstance;
    }

    /**
     * 创建群组
     *
     * @param groupName
     * @param numbers
     * @return 接口调用是否成功
     */
    public abstract boolean create(String groupName, ArrayList<String> numbers);

    /**
     * 离开群组
     *
     * @param item 群对象
     * @return 接口调用是否成功
     */
    public abstract boolean leave(JRGroupItem item);

    /**
     * 修改群名称
     *
     * @param item 群对象
     * @param name 群名称
     * @return 接口调用是否成功
     */
    public abstract boolean modifyGroupName(JRGroupItem item, String name);

    /**
     * 修改昵称
     *
     * @param item     群对象
     * @param nickName 在群中的昵称
     * @return 接口调用是否成功
     */
    public abstract boolean modifyNickName(JRGroupItem item, String nickName);

    /**
     * 解散群组
     *
     * @param item 群对象
     * @return 接口调用是否成功
     */
    public abstract boolean dissolve(JRGroupItem item);

    /**
     * 拒绝群邀请
     *
     * @param item 群对象
     * @return 接口调用是否成功
     */
    public abstract boolean rejectInvite(JRGroupItem item);

    /**
     * 接受群邀请
     *
     * @param item 群对象
     * @return 接口调用是否成功
     */
    public abstract boolean acceptInvite(JRGroupItem item);

    /**
     * 添加群成员
     *
     * @param numbers 邀请成员列表
     * @param item    群对象
     * @return 接口调用是否成功
     */
    public abstract boolean invite(JRGroupItem item, ArrayList<String> numbers);

    /**
     * 移除群成员
     *
     * @param item    群对象
     * @param numbers 移除成员列表
     * @return 接口调用是否成功
     */
    public abstract boolean kick(JRGroupItem item, ArrayList<String> numbers);

    /**
     * 转让群主
     *
     * @param item   群对象
     * @param number 号码
     * @return 接口调用是否成功
     */
    public abstract boolean modifyChairman(JRGroupItem item, String number);

    /**
     * 订阅群列表
     *
     * @return 接口调用是否成功
     */
    public abstract boolean subscribeGroupList();

    /**
     * 订阅群详情
     *
     * @param sessionIdentity 群sessionIdentity
     * @return 接口调用是否成功
     */
    public abstract boolean subscribeGroupInfo(String sessionIdentity);


    /**
     * 添加回调
     *
     * @param callback 回调对象
     */
    public abstract void addCallback(JRGroupCallback callback);

    /**
     * 删除回调
     *
     * @param callback 回调对象
     */
    public abstract void removeCallback(JRGroupCallback callback);
}
