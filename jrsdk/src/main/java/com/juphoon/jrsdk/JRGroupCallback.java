package com.juphoon.jrsdk;

import java.util.ArrayList;

public interface JRGroupCallback {

    /**
     * 收到群邀请或者被拉进群
     *
     * @param item 群对象
     */
    void onGroupAdd(JRGroupItem item);

    /**
     * 退出或者被踢出群聊，或者群无效
     *
     * @param item 群对象
     */
    void onGroupRemove(JRGroupItem item);

    /**
     * 群更新
     *
     * @param item    群对象
     * @param isFully 更新的群成员是否为全量
     */
    void onGroupUpdate(JRGroupItem item, boolean isFully);

    /**
     * 群操作结果
     *
     * @param operationType   操作type
     * @param succeed         是否成功
     * @param reason          原因
     * @param sessionIdentity 群sessionIdentity
     */
    void onGroupOperationResult(int operationType, boolean succeed, int reason, String sessionIdentity);

    /**
     * 群列表订阅结果
     *
     * @param succeed   是否成功
     * @param groupList 群对象列表
     */
    void onGroupListSubResult(boolean succeed, ArrayList<JRGroupItem> groupList);
}
