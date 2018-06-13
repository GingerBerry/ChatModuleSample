package com.juphoon.jrsdk;

public interface JRCallCallback {

    /**
     * @param item JRCallItem 对象
     * @brief 增加一路通话
     */
    void onCallItemAdd(JRCallItem item);

    /**
     * @param item   JRCallItem 对象
     * @param reason 通话结束原因
     * @brief 移除一路通话
     * @see JRCallConstants.CallTermReason
     */
    void onCallItemRemove(JRCallItem item, @JRCallConstants.CallTermReason int reason);

    /**
     * @param item JRCallItem 对象，当 item 为 nil 时表示全部更新
     * @param type 更新的事件类型
     * @brief 通话状态更新通知
     * @warning 当上层收到此回调时，可以根据 JRCallItem 对象获得该通话的所有信息及状态，从而更新该通话相关UI
     * @see JRCallConstants.CallUpdateType
     */
    void onCallItemUpdate(JRCallItem item, @JRCallConstants.CallUpdateType int type);
}
