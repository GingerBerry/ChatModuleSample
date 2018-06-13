package com.juphoon.jrsdk;

import java.util.List;

public abstract class JRCall {
    private static JRCall mInstance;

    public static JRCall getInstance() {
        if (mInstance == null) {
            mInstance = new JRCallImpl();
        }
        return mInstance;
    }

    /**
     *  @brief 一对一呼叫。
     *  @param peerNumber 对端号码
     *  @param video 是否为视频呼叫
     *  @return 成功返回 true，失败返回 false
     */
    public abstract boolean call(String peerNumber, boolean video);

    /**
     *  @brief 接听
     *  @param video 针对一对一视频呼入可以选择以视频接听还是音频接听
     *  @param token 多方视频所需token，如果非多方视频通话可为空
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean answer(boolean video, String token);

    /**
     *  @brief 结束通话
     *  @param reason 结束通话原因
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     *  @see JRCallConstants.CallTermReason
     */
    public abstract boolean end(@JRCallConstants.CallTermReason int reason);

    /**
     *  @brief 静音，解除静音
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean mute();

    /**
     *  @brief 保持通话，解除保持
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean hold();

    /**
     *  @brief 切换摄像头
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean switchCamera();

    /**
     *  @brief 发送DTMF
     *  @param dtmf dtmf类型
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     *  @see JRCallConstants.CallDtmftype
     */
    public abstract boolean sendDtmf(@JRCallConstants.CallDtmftype int dtmf);

    /**
     *  @brief 发起音视频互转的请求
     *  @param video 转为视频还是音频
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean updateCall(boolean video);

    /**
     *  @brief 响应音频转视频的请求
     *  @param video 转为视频还是音频
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean answerUpdate(boolean video);

    /**
     *  @brief 创建多方通话
     *  @param peerNumbers 对端号码队列
     *  @param video 是否为视频通话
     *  @param token 多方视频所需token，如果非多方视频通话可为空
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean createMultiCall(List<String> peerNumbers, boolean video, String token);

    /**
     *  @brief 多方通话邀请成员
     *  @param member 对端号码
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean addMultiCallMember(String member);

    /**
     *  @brief 多方通话移除成员
     *  @param member 通话成员对象
     *  @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean removeMultiCallMember(JRCallMember member);

    /**
     * @brief 静音或解除静音多方通话成员
     * @param member 要操作的成员
     * @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean muteMultiCallMember(JRCallMember member);

    /**
     * @brief 静音或解除静音所有成员
     * @param mute 静音还是解除静音
     * @return 返回 true 表示正常执行调用流程，false 表示调用异常
     */
    public abstract boolean muteAllCallMember(boolean mute);

    /**
     * @brief 获得当前通话统计信息，以Json字符串形式返回，其中包含 "Audio" 和 "Video" 两个节点
     * @param video 视频信息还是音频信息
     * @param member 多方视频可以获取单独成员的统计信息
     * @return 当前通话统计信息
     */
    public abstract String getStatistics(boolean video, JRCallMember member);

    /**
     * @brief 获得当前视频通话流量消耗信息
     * @return 当前视频通话流量消耗信息
     */
    public abstract long getVideoCallFlow();

    /**
     * 添加回调
     *
     * @param callback JCMessageChannelCallback 接口对象
     */
    public abstract void addCallback(JRCallCallback callback);

    /**
     * 删除回调
     *
     * @param callback JCMessageChannelCallback 接口对象
     */
    public abstract void removeCallback(JRCallCallback callback);

    /**
     * 获取当前的通话
     * @return 当前通话对象
     */
    public abstract JRCallItem getCurrentCallItem();
}
