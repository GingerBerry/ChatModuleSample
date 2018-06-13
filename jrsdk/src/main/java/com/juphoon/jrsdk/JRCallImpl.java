package com.juphoon.jrsdk;

import com.juphoon.cmcc.app.lemon.MtcCommonConstants;
import com.juphoon.cmcc.app.lemon.MtcPartpConstants;

import java.util.ArrayList;
import java.util.List;

class JRCallImpl extends JRCall implements MtcNotifyListener, JRMediaDeviceCallback {

    private ArrayList<JRCallCallback> mCallbacks = new ArrayList<>();
    private JRCallItem mCurrentCallItem;

    JRCallImpl() {
        MtcEngine.getInstance().addCallback(this);
        JRMediaDevice.getInstance().addCallback(this);
    }

    @Override
    public boolean call(String peerNumber, boolean video) {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem != null) {
            JRLog.printf("已存在通话");
            return false;
        }
        JRCallItem callItem = new JRCallItem();
        callItem.type = video ? JRCallConstants.CALL_TYPE_ONE_ON_ONE_VIDEO : JRCallConstants.CALL_TYPE_ONE_ON_ONE_AUDIO;
        callItem.direction = JRCallConstants.CALL_DIRECTION_OUT;
        callItem.selfNumber = JRClient.getInstance().getCurrentNumber();
        callItem.beginTime = System.currentTimeMillis();
        callItem.callId = MtcCommonConstants.INVALIDID;
        JRCallMember member = new JRCallMember();
        member.number = JRNumberTool.numberWithChineseCountryCode(peerNumber);
        callItem.callMembers.add(member);
        mCurrentCallItem = callItem;
        // 先将callItem抛出，改善网络极差时的交互
        notifyCallItemAdd(callItem);

        JRCallParam.Start param = new JRCallParam.Start();
        param.peerNumber.add(JRNumberTool.numberWithChineseCountryCode(peerNumber));
        param.isVideo = video;
        param.isConf = false;
        JRCallResult.Start result = MtcEngine.getInstance().call(param);
        if (result.isSucceed) {
            JRMediaDevice.getInstance().startAudio();
            callItem.callId = result.callId;
            callItem.callMembers.get(0).videoSource = result.renderId;
        }
        return true;
    }

    @Override
    public boolean answer(boolean video, String token) {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem != null || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("不存在通话");
            return false;
        }
        if (mCurrentCallItem.type == JRCallConstants.CALL_TYPE_MULTI_VIDEO) {
            // 多方视频需要设置token
            JRClientParam.SetToken param = new JRClientParam.SetToken();
            param.token = token;
            param.type = JRClientDefine.MTC_CLIENT_SET_TOKEN_TYPE_MULTI_VIDEO;
            JRClientResult.SetToken result = MtcEngine.getInstance().setToken(param);
            if (!result.isSucceed) {
                // setToken失败
                return false;
            }
        }
        JRCallParam.Answer param = new JRCallParam.Answer();
        param.isConf = mCurrentCallItem.isConf();
        param.isVideo = mCurrentCallItem.isVideo();
        param.callId = mCurrentCallItem.callId;
        JRCallResult.Answer result = new JRCallResult.Answer();
        JRMediaDevice.getInstance().startAudio();
        return result.isSucceed;
    }

    @Override
    public boolean end(@JRCallConstants.CallTermReason int reason) {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem != null || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("不存在通话");
            return false;
        }
        JRCallParam.End param = new JRCallParam.End();
        param.callId = mCurrentCallItem.callId;
        param.reason = reason;
        param.isConf = mCurrentCallItem.isConf();
        param.isVideo = mCurrentCallItem.isVideo();
        JRCallResult.End result = MtcEngine.getInstance().end(param);
        if (result.isSucceed) {
            if (mCurrentCallItem.state == JRCallConstants.CALL_STATE_TALKING) {
                mCurrentCallItem.state = JRCallConstants.CALL_STATE_OK;
            } else {
                mCurrentCallItem.state = mCurrentCallItem.direction == JRCallConstants.CALL_DIRECTION_IN ? JRCallConstants.CALL_STATE_MISSED : JRCallConstants.CALL_STATE_CANCEL;
            }
            mCurrentCallItem.endTime = System.currentTimeMillis();
            notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_TERMED);
            if (mCurrentCallItem.isVideo()) {
                JRCallParam.Detach detach = new JRCallParam.Detach();
                detach.callId = mCurrentCallItem.callId;
                detach.isConf = mCurrentCallItem.isConf();
                MtcEngine.getInstance().detach(detach);
            }
            JRThreadTool.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    JRMediaDevice.getInstance().stopAudio();
                    if (mCurrentCallItem != null) {
                        notifyCallItemRemove(mCurrentCallItem, JRCallConstants.CALL_TERM_REASON_NORMAL);
                        mCurrentCallItem = null;
                    }
                }
            });
        }
        return result.isSucceed;
    }

    @Override
    public boolean mute() {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem != null || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("不存在通话");
            return false;
        }
        JRCallParam.Mute param = new JRCallParam.Mute();
        param.callId = mCurrentCallItem.callId;
        param.toMute = mCurrentCallItem.mute;
        param.isConf = mCurrentCallItem.isConf();
        JRCallResult.Mute result = MtcEngine.getInstance().mute(param);
        if (result.isSucceed) {
            mCurrentCallItem.mute = !mCurrentCallItem.mute;
            notifyCallItemUpdate(mCurrentCallItem, mCurrentCallItem.mute ? JRCallConstants.CALL_UPDATE_TYPE_MUTE : JRCallConstants.CALL_UPDATE_TYPE_UNMUTE);
        }
        return result.isSucceed;
    }

    @Override
    public boolean hold() {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem != null || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("不存在通话");
            return false;
        }
        JRCallParam.Hold param = new JRCallParam.Hold();
        param.callId = mCurrentCallItem.callId;
        param.toHold = !mCurrentCallItem.hold;
        param.isConf = mCurrentCallItem.isConf();
        JRCallResult.Hold result = MtcEngine.getInstance().hold(param);
        return result.isSucceed;
    }

    @Override
    public boolean switchCamera() {
        if (!mCurrentCallItem.isVideo()) {
            JRLog.printf("语音通话不支持切换摄像头");
            return false;
        }
        JRMediaDevice.getInstance().switchCamera();
        return false;
    }

    @Override
    public boolean sendDtmf(@JRCallConstants.CallDtmftype int dtmf) {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem.isConf() || mCurrentCallItem == null || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("不存在一对一通话");
            return false;
        }
        JRCallParam.SendDtmf param = new JRCallParam.SendDtmf();
        param.callId = mCurrentCallItem.callId;
        param.dtmfType = dtmf;
        JRCallResult.SendDtmf result = MtcEngine.getInstance().sendDtmf(param);
        return result.isSucceed;
    }

    @Override
    public boolean updateCall(boolean video) {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem.isConf() || mCurrentCallItem == null || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("不存在一对一通话");
            return false;
        }
        JRCallParam.Update param = new JRCallParam.Update();
        param.callId = mCurrentCallItem.callId;
        param.toVideo = video;
        JRCallResult.Update result = MtcEngine.getInstance().update(param);
        if (!result.isSucceed) {
            notifyCallItemUpdate(mCurrentCallItem, video ? JRCallConstants.CALL_UPDATE_TYPE_TO_VIDEO_FAILED : JRCallConstants.CALL_UPDATE_TYPE_TO_AUDIO_FAILED);
        }
        return true;
    }

    @Override
    public boolean answerUpdate(boolean video) {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem.isConf() || mCurrentCallItem == null || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("不存在一对一通话");
            return false;
        }
        JRCallParam.AnswerUpdate param = new JRCallParam.AnswerUpdate();
        param.callId = mCurrentCallItem.callId;
        param.toVideo = video;
        JRCallResult.AnswerUpdate result = MtcEngine.getInstance().answerUpdate(param);
        return result.isSucceed;
    }

    @Override
    public boolean createMultiCall(List<String> peerNumbers, boolean video, String token) {
        if (!isLogined()) {
            return false;
        }
        if (mCurrentCallItem != null) {
            JRLog.printf("已有通话");
            return false;
        }
        JRCallItem callItem = new JRCallItem();
        callItem.type = video ? JRCallConstants.CALL_TYPE_MULTI_VIDEO : JRCallConstants.CALL_TYPE_MULTI_AUDIO;
        callItem.state = JRCallConstants.CALL_STATE_INIT;
        callItem.selfNumber = JRClient.getInstance().getCurrentNumber();
        callItem.direction = JRCallConstants.CALL_DIRECTION_OUT;
        callItem.beginTime = System.currentTimeMillis();

        List<JRCallMember> callMembers = new ArrayList<>();
        List<String> callNumbers = new ArrayList<>();
        for (String number : peerNumbers) {
            String tempNumber = JRNumberTool.numberWithChineseCountryCode(number);
            if (!tempNumber.isEmpty()) {
                JRCallMember member = new JRCallMember();
                member.number = tempNumber;
                member.status = JRCallConstants.CALL_MEMBER_STATUS_PENDING;
                callMembers.add(member);
                callNumbers.add(tempNumber);
            }
        }
        if (callMembers.size() == 0) {
            JRLog.printf("无呼叫号码");
            return false;
        }
        callItem.callMembers = callMembers;
        mCurrentCallItem = callItem;
        notifyCallItemAdd(callItem);

        if (video) {
            // 多方视频需要设置token
            JRClientParam.SetToken param = new JRClientParam.SetToken();
            param.token = token;
            param.type = JRClientDefine.MTC_CLIENT_SET_TOKEN_TYPE_MULTI_VIDEO;
            JRClientResult.SetToken result = MtcEngine.getInstance().setToken(param);
            if (!result.isSucceed) {
                mCurrentCallItem.state = JRCallConstants.CALL_STATE_CANCEL;
                mCurrentCallItem.endTime = System.currentTimeMillis();
                notifyCallItemUpdate(callItem, JRCallConstants.CALL_UPDATE_TYPE_TERMED);
                JRThreadTool.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        JRMediaDevice.getInstance().stopAudio();
                        if (mCurrentCallItem != null) {
                            notifyCallItemRemove(mCurrentCallItem, JRCallConstants.CALL_TERM_REASON_NORMAL);
                            mCurrentCallItem = null;
                        }
                    }
                });
                return false;
            }
        }
        JRCallParam.Start param = new JRCallParam.Start();
        param.peerNumber = callNumbers;
        param.isVideo = video;
        param.isConf = true;
        JRCallResult.Start result = MtcEngine.getInstance().call(param);
        if (result.isSucceed) {
            JRMediaDevice.getInstance().startAudio();
            callItem.callId = result.callId;
            callItem.state = JRCallConstants.CALL_STATE_PENDING;
            notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_OUTGOING);
        }
        return true;
    }

    @Override
    public boolean addMultiCallMember(String member) {
        if (!isLogined()) {
            return false;
        }
        if (!mCurrentCallItem.isConf() || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("多方通话不存在");
            return false;
        }
        boolean exist = false;
        for (JRCallMember callMember : mCurrentCallItem.callMembers) {
            if (JRNumberTool.isNumberEqual(callMember.number, member)) {
                if (callMember.status > JRCallConstants.CALL_MEMBER_STATUS_DIALINGOUT && callMember.status < JRCallConstants.CALL_MEMBER_STATUS_DISCING) {
                    JRLog.printf("所添加的成员已经存在");
                    return false;
                } else {
                    exist = true;
                    callMember.status = JRCallConstants.CALL_MEMBER_STATUS_PENDING;
                    notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_CONF_MEMBER_UPDATE);
                }
            }
        }
        JRCallParam.AddMember param = new JRCallParam.AddMember();
        param.callId = mCurrentCallItem.callId;
        param.peerNumber = JRNumberTool.numberWithChineseCountryCode(member);
        param.isVideo = mCurrentCallItem.isVideo();
        JRCallResult.AddMember result = MtcEngine.getInstance().addMember(param);
        if (result.isSucceed && !exist) {
            JRCallMember callMember = new JRCallMember();
            callMember.number = JRNumberTool.numberWithChineseCountryCode(member);
            callMember.displayName = member;
            callMember.status = JRCallConstants.CALL_MEMBER_STATUS_PENDING;
            mCurrentCallItem.callMembers.add(callMember);
            notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_CONF_MEMBER_UPDATE);
        }
        return result.isSucceed;
    }

    @Override
    public boolean removeMultiCallMember(JRCallMember member) {
        if (!mCurrentCallItem.isConf() || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("多方通话不存在");
            return false;
        }
        for (JRCallMember callMember : mCurrentCallItem.callMembers) {
            if (JRNumberTool.isNumberEqual(callMember.number, member.number)) {
                JRCallParam.RemoveMember param = new JRCallParam.RemoveMember();
                param.callId = mCurrentCallItem.callId;
                param.peerNumber = member.number;
                param.isVideo = mCurrentCallItem.isVideo();
                JRCallResult.RemoveMember result = MtcEngine.getInstance().removeMember(param);
                return result.isSucceed;
            }
        }
        JRLog.printf("删除的成员不在多方通话中");
        return false;
    }

    @Override
    public boolean muteMultiCallMember(JRCallMember member) {
        if (!mCurrentCallItem.isConf() || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("多方通话不存在");
            return false;
        }
        JRCallParam.ConfMute param = new JRCallParam.ConfMute();
        param.callId = mCurrentCallItem.callId;
        param.peerNumber = JRNumberTool.numberWithChineseCountryCode(member.number);
        param.isAll = false;
        param.toMute = member.status != JRCallConstants.CALL_MEMBER_STATUS_MUTED;
        return MtcEngine.getInstance().muteMultiCallMember(param).isSucceed;
    }

    @Override
    public boolean muteAllCallMember(boolean mute) {
        if (!mCurrentCallItem.isConf() || mCurrentCallItem.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("多方通话不存在");
            return false;
        }
        JRCallParam.ConfMute param = new JRCallParam.ConfMute();
        param.callId = mCurrentCallItem.callId;
        param.isAll = true;
        param.toMute = mute;
        return MtcEngine.getInstance().muteMultiCallMember(param).isSucceed;
    }

    @Override
    public String getStatistics(boolean video, JRCallMember member) {
        JRCallParam.GetStatistics param = new JRCallParam.GetStatistics();
        param.callId = mCurrentCallItem.callId;
        param.isVideo = video;
        param.isConf = mCurrentCallItem.isConf();
        param.videoSource = member.videoSource;
        JRCallResult.GetStatistics result = MtcEngine.getInstance().getStatistics(param);
        return result.statistics;
    }

    @Override
    public long getVideoCallFlow() {
        if (mCurrentCallItem == null) {
            JRLog.printf("当前不存在通话");
            return 0;
        }
        JRCallParam.GetFlow param = new JRCallParam.GetFlow();
        param.callId = mCurrentCallItem.callId;
        param.isVideo = mCurrentCallItem.isVideo();
        JRCallResult.GetFlow result = MtcEngine.getInstance().getFlow(param);
        return result.sentBytes + result.recvBytes;
    }

    @Override
    public void addCallback(JRCallCallback callback) {
        if (!mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    @Override
    public void removeCallback(JRCallCallback callback) {
        mCallbacks.remove(callback);
    }

    @Override
    public JRCallItem getCurrentCallItem() {
        return mCurrentCallItem;
    }

    @Override
    public void onNotify(JRBaseNotify notify) {
        if (notify.type != JRBaseNotify.NotifyType.Call) {
            return;
        }
        if (notify instanceof JRCallNotify.Incoming) {
            JRCallNotify.Incoming incomingNotify = new JRCallNotify.Incoming();
            if (mCurrentCallItem != null) {
                JRCallItem item = new JRCallItem();
                item.callId = incomingNotify.callId;
                item.direction = JRCallConstants.CALL_DIRECTION_IN;
                item.state = JRCallConstants.CALL_STATE_PENDING;
                item.beginTime = System.currentTimeMillis();
                JRCallMember member = new JRCallMember();
                member.number = JRNumberTool.numberWithChineseCountryCode(incomingNotify.peerNumber);
                member.displayName = incomingNotify.displayName;
                member.videoSource = incomingNotify.renderId;
                member.status = JRCallConstants.CALL_MEMBER_STATUS_PENDING;
                mCurrentCallItem = item;
                notifyCallItemAdd(item);
                notifyCallItemUpdate(item, JRCallConstants.CALL_UPDATE_TYPE_INCOMING);
            } else {
                JRCallParam.End param = new JRCallParam.End();
                param.callId = incomingNotify.callId;
                param.reason = JRCallDefine.MTC_CALL_TERM_REASON_BUSY;
                param.isConf = (incomingNotify.callType == JRCallDefine.MTC_CALL_TYPE_MULTI_AUDIO || incomingNotify.callType == JRCallDefine.MTC_CALL_TYPE_MULTI_VIDEO);
                MtcEngine.getInstance().end(param);
            }
        } else if (notify instanceof JRCallNotify.Outgoing) {
            JRCallNotify.Outgoing outgingNotify = (JRCallNotify.Outgoing) notify;
            if (mCurrentCallItem.callId == outgingNotify.callId) {
                mCurrentCallItem.state = JRCallConstants.CALL_STATE_PENDING;
                notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_OUTGOING);
            }
        } else if (notify instanceof JRCallNotify.Alert) {
            JRCallNotify.Alert alertNotify = (JRCallNotify.Alert) notify;
            if (mCurrentCallItem.callId == alertNotify.callId) {
                mCurrentCallItem.state = JRCallConstants.CALL_STATE_ALERTING;
                notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_OUTGOING);
            }
        } else if (notify instanceof JRCallNotify.Talking) {
            JRCallNotify.Talking talkingNotify = (JRCallNotify.Talking) notify;
            if (mCurrentCallItem.callId == talkingNotify.callId) {
                if (talkingNotify.isVideo) {
                    startVideo();
                }
                if (talkingNotify.isConf) {
                    mCurrentCallItem.type = talkingNotify.isVideo ? JRCallConstants.CALL_TYPE_MULTI_VIDEO : JRCallConstants.CALL_TYPE_MULTI_AUDIO;
                } else {
                    mCurrentCallItem.type = talkingNotify.isVideo ? JRCallConstants.CALL_TYPE_ONE_ON_ONE_VIDEO : JRCallConstants.CALL_TYPE_ONE_ON_ONE_AUDIO;
                }
                mCurrentCallItem.state = JRCallConstants.CALL_STATE_TALKING;
                mCurrentCallItem.talkingBeginTime = System.currentTimeMillis();
                if (mCurrentCallItem.type == JRCallConstants.CALL_TYPE_ONE_ON_ONE_AUDIO || mCurrentCallItem.type == JRCallConstants.CALL_TYPE_ONE_ON_ONE_VIDEO) {
                    mCurrentCallItem.callMembers.get(0).number = talkingNotify.peerNumber;
                    mCurrentCallItem.callMembers.get(0).displayName = talkingNotify.displayName;
                }
                notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_TALKING);
            }
        } else if (notify instanceof JRCallNotify.Termed) {
            final JRCallNotify.Termed termedNotify = (JRCallNotify.Termed) notify;
            if (mCurrentCallItem.callId == termedNotify.callId) {
                if (mCurrentCallItem.state == JRCallConstants.CALL_STATE_TALKING) {
                    mCurrentCallItem.state = JRCallConstants.CALL_STATE_OK;
                } else {
                    mCurrentCallItem.state = mCurrentCallItem.direction == JRCallConstants.CALL_DIRECTION_IN ? JRCallConstants.CALL_STATE_MISSED : JRCallConstants.CALL_STATE_CANCELED;
                }
                mCurrentCallItem.endTime = System.currentTimeMillis();
                notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_TERMED);
                if (mCurrentCallItem.isVideo()) {
                    JRCallParam.Detach param = new JRCallParam.Detach();
                    param.isConf = mCurrentCallItem.isConf();
                    param.callId = mCurrentCallItem.callId;
                    MtcEngine.getInstance().detach(param);
                }
                JRThreadTool.getInstance().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        JRMediaDevice.getInstance().stopAudio();
                        if (mCurrentCallItem != null) {
                            notifyCallItemRemove(mCurrentCallItem, termedNotify.reason);
                            mCurrentCallItem = null;
                        }
                    }
                }, 1);
            }
        } else if (notify instanceof JRCallNotify.TypeUpdate) {
            JRCallNotify.TypeUpdate updateNotify = (JRCallNotify.TypeUpdate) notify;
            int type;
            switch (updateNotify.updateType) {
                case JRCallDefine.MTC_CALL_UPDATE_TYPE_REQ_VIDEO:
                    type = JRCallConstants.CALL_UPDATE_TYPE_REQ_VIDEO;
                    break;
                case JRCallDefine.MTC_CALL_UPDATE_TYPE_TO_AUDIO_OK:
                    type = JRCallConstants.CALL_UPDATE_TYPE_TO_AUDIO_OK;
                    break;
                case JRCallDefine.MTC_CALL_UPDATE_TYPE_TO_VIDEO_OK:
                    type = JRCallConstants.CALL_UPDATE_TYPE_TO_AUDIO_OK;
                    break;
                case JRCallDefine.MTC_CALL_UPDATE_TYPE_TO_AUDIO_FAILED:
                    type = JRCallConstants.CALL_UPDATE_TYPE_TO_AUDIO_FAILED;
                    break;
                case JRCallDefine.MTC_CALL_UPDATE_TYPE_TO_VIDEO_FAILED:
                    type = JRCallConstants.CALL_UPDATE_TYPE_TO_VIDEO_FAILED;
                    break;
                default:
                    type = JRCallConstants.CALL_UPDATE_TYPE_TO_VIDEO_FAILED;
                    break;
            }
            notifyCallItemUpdate(mCurrentCallItem, type);
        } else if (notify instanceof JRCallNotify.Hold) {
            JRCallNotify.Hold holdNotify = (JRCallNotify.Hold) notify;
            if (mCurrentCallItem.callId == holdNotify.callId) {
                mCurrentCallItem.hold = holdNotify.holdOk;
                notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_HOLD);
            }
        } else if (notify instanceof JRCallNotify.Unhold) {
            JRCallNotify.Unhold holdNotify = (JRCallNotify.Unhold) notify;
            if (mCurrentCallItem.callId == holdNotify.callId) {
                mCurrentCallItem.hold = !holdNotify.unholdOk;
                notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_UNHOLD);
            }
        } else if (notify instanceof JRCallNotify.Held) {
            JRCallNotify.Held heldNotify = (JRCallNotify.Held)notify;
            if (mCurrentCallItem.callId == heldNotify.callId) {
                mCurrentCallItem.held = heldNotify.held;
                if (heldNotify.held) {
                    notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_HELD);
                } else {
                    notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_UNHELD);
                }
            }
        } else if (notify instanceof JRCallNotify.RecvInfo) {
            // 收到通过callSession发送的消息

        } else if (notify instanceof JRCallNotify.NetStateChanged) {
            JRCallNotify.NetStateChanged netNotify = (JRCallNotify.NetStateChanged)notify;
            if (mCurrentCallItem.callId == netNotify.callId) {
                mCurrentCallItem.netStatus = netNotify.status;
                notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_NET_STATUS_CHANGED);
            }
        } else if (notify instanceof JRCallNotify.MembersUpdate) {
            JRCallNotify.MembersUpdate updateNotify = (JRCallNotify.MembersUpdate)notify;
            if (mCurrentCallItem.callId == updateNotify.callId) {
                if (updateNotify.bFull) {
                    // 多方视频此回调不会带自己，如存在自己则取出缓存
                    JRCallMember selfMember = new JRCallMember();
                    for (JRCallMember member : mCurrentCallItem.callMembers) {
                        if (JRNumberTool.isNumberEqual(member.number, JRClient.getInstance().getCurrentNumber())) {
                            selfMember = member;
                        }
                    }
                    // 全量替换
                    List<JRCallMember> jrMembers = new ArrayList<>();
                    for (MtcCallMember member : updateNotify.members) {
                        if (member.state != MtcPartpConstants.EN_MTC_CMCC_CONF_PARTP_STATE_DISCED && member.state != MtcPartpConstants.EN_MTC_CMCC_CONF_PARTP_STATE_DISCING && member.state != MtcPartpConstants.EN_MTC_CMCC_CONF_PARTP_STATE_HANG_UP) {
                            jrMembers.add(converCallMember(member));
                        }
                    }
                    for (JRCallMember member : jrMembers) {
                        for (JRCallMember currentMember : mCurrentCallItem.callMembers) {
                            if (JRNumberTool.isNumberEqual(currentMember.number, member.number) && !currentMember.videoSource.isEmpty()) {
                                member.videoSource = currentMember.videoSource;
                                break;
                            }
                        }
                    }
                    // 如果目标队列里不存在自己，则插入
                    boolean exist = false;
                    for (JRCallMember member : jrMembers) {
                        if (JRNumberTool.isNumberEqual(member.number, JRClient.getInstance().getCurrentNumber())) {
                            exist = true;
                        }
                    }
                    if (!exist && selfMember != null) {
                        jrMembers.add(selfMember);
                    }
                    mCurrentCallItem.callMembers = jrMembers;
                } else {
                    // 非全量更新
                    List<MtcCallMember> tempArray = new ArrayList<>(updateNotify.members);
                    for (MtcCallMember member : updateNotify.members) {
                        for (JRCallMember callMember : mCurrentCallItem.callMembers) {
                            if (JRNumberTool.isNumberEqual(member.peerNumber, callMember.number)) {
                                callMember.status = member.state;
                                tempArray.remove(member);
                            }
                        }
                    }
                    List<JRCallMember> jrMembers = new ArrayList<>(mCurrentCallItem.callMembers);
                    for (MtcCallMember member : tempArray) {
                        jrMembers.add(converCallMember(member));
                    }
                    for (JRCallMember member : new ArrayList<>(jrMembers)) {
                        // 已断开的删除
                        if (member.status == JRCallConstants.CALL_MEMBER_STATUS_DISCED || member.status == JRCallConstants.CALL_MEMBER_STATUS_DISCING || member.status == JRCallConstants.CALL_MEMBER_STATUS_HANGUP) {
                            jrMembers.remove(member);
                        }
                    }
                    mCurrentCallItem.callMembers = jrMembers;
                }
                notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_CONF_MEMBER_UPDATE);
            }
        } else if (notify instanceof JRCallNotify.VideoStrmPtptUpdt) {
            JRCallNotify.VideoStrmPtptUpdt updateNotify = (JRCallNotify.VideoStrmPtptUpdt)notify;
            if (mCurrentCallItem.callId == updateNotify.callId) {
                if (updateNotify.number.equals("clear")) {
                    // 如果是clear，此时strmId会被重新分配，故无法对应哪个成员退出，依赖JRCallMembersUpdateNotify来进行成员更新
                    return;
                }
                for (JRCallMember currentMember : mCurrentCallItem.callMembers) {
                    if (currentMember.videoSource.equals(updateNotify.strmName)) {
                        if (JRNumberTool.isNumberEqual(updateNotify.number, currentMember.number)) {
                            // 无用的回调
                            return;
                        } else {
                            // 当前的videoSource被使用，且号码变动，则解绑之前的号码
                            currentMember.videoSource = null;
                        }
                    }
                }
                boolean exist = false;
                for (JRCallMember currentMember : mCurrentCallItem.callMembers) {
                    // 如果member存在，更新videoSource
                    if (JRNumberTool.isNumberEqual(updateNotify.number,currentMember.number) && !updateNotify.strmName.isEmpty()) {
                        exist = true;
                        currentMember.videoSource = updateNotify.strmName;
                        currentMember.status = JRCallConstants.CALL_MEMBER_STATUS_CONNED;
                        break;
                    }
                }
                // 如果不存在，新增该成员
                if (!exist) {
                    JRCallMember member = new JRCallMember();
                    member.number = JRNumberTool.numberWithChineseCountryCode(updateNotify.number);
                    member.displayName = updateNotify.number;
                    member.status = JRCallConstants.CALL_MEMBER_STATUS_CONNED;
                    member.videoSource = updateNotify.strmName;
                    JRLog.printf("成员号码 %s", member.number);
                    mCurrentCallItem.callMembers.add(member);
                }
            }
            notifyCallItemUpdate(mCurrentCallItem, JRCallConstants.CALL_UPDATE_TYPE_CONF_MEMBER_UPDATE);
        }
    }

    private void notifyCallItemAdd(JRCallItem item) {
        for (JRCallCallback callback : mCallbacks) {
            callback.onCallItemAdd(item);
        }
    }

    private void notifyCallItemRemove(JRCallItem item, @JRCallConstants.CallTermReason int reason) {
        for (JRCallCallback callback : mCallbacks) {
            callback.onCallItemRemove(item, reason);
        }
    }

    private void notifyCallItemUpdate(JRCallItem item, @JRCallConstants.CallUpdateType int type) {
        for (JRCallCallback callback : mCallbacks) {
            callback.onCallItemUpdate(item, type);
        }
    }

    private boolean isLogined() {
        if (JRClient.getInstance().getState() == JRClientConstants.CLIENT_STATE_LOGINED) {
            return true;
        }
        JRLog.printf("当前无登录的账号");
        return false;
    }

    private boolean startVideo() {
        if (!mCurrentCallItem.isVideo()) {
            JRLog.printf("语音通话无法开启视频");
            return false;
        }
        JRCallParam.StartVideo param = new JRCallParam.StartVideo();
        param.callId = mCurrentCallItem.callId;
        JRCallResult.StartVideo result = MtcEngine.getInstance().startVideo(param);
        if (result.isSucceed) {
            JRLog.printf("开启视频成功");
            return true;
        } else {
            JRLog.printf("开启视频失败");
            return false;
        }
    }

    private JRCallMember converCallMember(MtcCallMember member) {
        JRCallMember callMember = new JRCallMember();
        callMember.displayName = member.displayName;
        callMember.number = member.peerNumber;
        callMember.status = member.state;
        return callMember;
    }

    public void onCameraTypeChange() {
        if (mCurrentCallItem == null) {
            JRLog.printf("当前无通话");
        }

        JRCallParam.Attach param = new JRCallParam.Attach();
        param.callId = mCurrentCallItem.callId;
        param.camera = JRMediaDevice.getInstance().camera();
        param.isConf = mCurrentCallItem.isConf();
        JRCallResult.Attach result = MtcEngine.getInstance().attach(param);
        if (result.isSucceed) {
            JRLog.printf("绑定摄像头成功");
        } else {
            JRLog.printf("绑定摄像头失败");
        }
    }

    public void onAudioOutputTypeChange() {

    }

    public void onRenderStart(JRMediaDeviceVideoCanvas canvas) {

    }
}
