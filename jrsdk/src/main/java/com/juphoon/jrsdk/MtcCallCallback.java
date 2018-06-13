package com.juphoon.jrsdk;

import com.juphoon.cmcc.app.lemon.MtcCall;
import com.juphoon.cmcc.app.lemon.MtcConf;
import com.juphoon.cmcc.app.lemon.MtcConfCmcc;
import com.juphoon.cmcc.app.lemon.MtcNumber;
import com.juphoon.cmcc.app.lemon.MtcPartp;
import com.juphoon.cmcc.app.lemon.MtcString;
import com.juphoon.cmcc.app.lemon.MtcUri;
import com.juphoon.cmcc.app.lemon.callback.MtcCallCb;
import com.juphoon.cmcc.app.lemon.MtcCallConstants;
import com.juphoon.cmcc.app.lemon.callback.MtcConfCb;
import com.juphoon.cmcc.app.lemon.callback.MtcConfCmccCb;

import java.util.ArrayList;
import java.util.List;

class MtcCallCallback implements MtcCallCb.Callback, MtcConfCmccCb.Callback, MtcConfCb.Callback {
    @Override
    public void mtcCallCbIncoming(int dwSessId) {
        JRLog.printf("收到来电 %d", dwSessId);
        JRCallNotify.Incoming notify = new JRCallNotify.Incoming();
        MtcString ppcDispName = new MtcString();
        MtcString ppcUri = new MtcString();
        MtcCall.Mtc_SessGetPeerId(dwSessId, ppcDispName, ppcUri);
        notify.displayName = ppcDispName.getValue();
        notify.peerNumber = MtcUri.Mtc_UriGetUserPart(ppcUri.getValue());
        notify.callId = dwSessId;
        notify.renderId = MtcCall.Mtc_SessGetName(dwSessId);
        notify.callType = MtcCall.Mtc_SessPeerOfferVideo(dwSessId) ? JRCallDefine.MTC_CALL_TYPE_ONE_ON_ONE_VIDEO : JRCallDefine.MTC_CALL_TYPE_ONE_ON_ONE_AUDIO;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbOutgoing(int dwSessId) {
        JRLog.printf("呼出 %d", dwSessId);
        JRCallNotify.Outgoing notify = new JRCallNotify.Outgoing();
        notify.callId = dwSessId;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbAlerted(int dwSessId, int dwAlertType) {
        JRLog.printf("振铃 %d", dwSessId);
        JRCallNotify.Alert notify = new JRCallNotify.Alert();
        notify.callId = dwSessId;
        notify.alertType = dwAlertType;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbTalking(int dwSessId) {
        JRLog.printf("接通 %d", dwSessId);
        JRCallNotify.Talking notify = new JRCallNotify.Talking();
        MtcString ppcDispName = new MtcString();
        MtcString ppcUri = new MtcString();
        MtcCall.Mtc_SessGetPeerId(dwSessId, ppcDispName, ppcUri);
        notify.callId = dwSessId;
        notify.isConf = false;
        notify.isVideo = MtcCall.Mtc_SessPeerOfferVideo(dwSessId);
        notify.displayName = ppcDispName.getValue();
        notify.peerNumber = MtcUri.Mtc_UriGetUserPart(ppcUri.getValue());
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbTermed(int dwSessId, int dwStatCode) {
        JRLog.printf("挂断 %d %d", dwSessId, dwStatCode);
        JRCallNotify.Termed notify = new JRCallNotify.Termed();
        notify.callId = dwSessId;
        switch (dwStatCode) {
            case MtcCallConstants.MTC_CALL_TERM_BYE:
            case MtcCallConstants.MTC_CALL_TERM_CANCEL:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_NORMAL;
                break;
            case MtcCallConstants.MTC_CALL_TERM_BUSY:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_BUSY;
            case MtcCallConstants.MTC_CALL_TERM_DECLINE:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_DECLINE;
                break;
            case MtcCallConstants.MTC_CALL_ERR_FORBIDDEN:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_FORBIDDEN;
                break;
            case MtcCallConstants.MTC_CALL_ERR_NOT_FOUND:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_NOT_FOUND;
                break;
            case MtcCallConstants.MTC_CALL_ERR_NOT_ACPTED:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_NOT_ACPTED;
                break;
            case MtcCallConstants.MTC_CALL_ERR_REQ_TERMED:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_REQ_TERMED;
                break;
            case MtcCallConstants.MTC_CALL_ERR_INTERNAL_ERR:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_INTERNAL_ERR;
                break;
            case MtcCallConstants.MTC_CALL_ERR_SRV_UNAVAIL:
                notify.reason = MtcCallConstants.MTC_CALL_ERR_SRV_UNAVAIL;
                break;
            case MtcCallConstants.MTC_CALL_ERR_NOT_EXIST:
                notify.reason = MtcCallConstants.MTC_CALL_ERR_NOT_EXIST;
                break;
            case MtcCallConstants.MTC_CALL_ERR_TEMP_UNAVAIL:
                notify.reason = MtcCallConstants.MTC_CALL_ERR_TEMP_UNAVAIL;
                break;
            default:
                notify.reason = JRCallDefine.MTC_CALL_TERM_REASON_NONE;
                break;
        }
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbHoldOk(int dwSessId) {
        JRLog.printf("保持通话成功 %d", dwSessId);
        JRCallNotify.Hold notify = new JRCallNotify.Hold();
        notify.callId = dwSessId;
        notify.holdOk = true;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbHoldFailed(int dwSessId) {
        JRLog.printf("保持通话失败 %d", dwSessId);
        JRCallNotify.Hold notify = new JRCallNotify.Hold();
        notify.callId = dwSessId;
        notify.holdOk = false;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbUnHoldOk(int dwSessId) {
        JRLog.printf("解除保持通话成功 %d", dwSessId);
        JRCallNotify.Unhold notify = new JRCallNotify.Unhold();
        notify.callId = dwSessId;
        notify.unholdOk = true;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbUnHoldFailed(int dwSessId) {
        JRLog.printf("解除保持通话失败 %d", dwSessId);
        JRCallNotify.Unhold notify = new JRCallNotify.Unhold();
        notify.callId = dwSessId;
        notify.unholdOk = false;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbHeld(int dwSessId) {
        JRLog.printf("通话被保持 %d", dwSessId);
        JRCallNotify.Held notify = new JRCallNotify.Held();
        notify.callId = dwSessId;
        notify.held = true;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbUnHeld(int dwSessId) {
        JRLog.printf("通话被解除保持 %d", dwSessId);
        JRCallNotify.Held notify = new JRCallNotify.Held();
        notify.callId = dwSessId;
        notify.held = false;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbAddVideoOk(int dwSessId) {
        JRLog.printf("音频转视频成功 %d", dwSessId);
        JRCallNotify.TypeUpdate notify = new JRCallNotify.TypeUpdate();
        notify.callId = dwSessId;
        notify.updateType = JRCallDefine.MTC_CALL_UPDATE_TYPE_TO_VIDEO_OK;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbAddVideoFailed(int dwSessId, int dwStatCode) {
        JRLog.printf("音频转视频失败 %d", dwSessId);
        JRCallNotify.TypeUpdate notify = new JRCallNotify.TypeUpdate();
        notify.callId = dwSessId;
        notify.updateType = JRCallDefine.MTC_CALL_UPDATE_TYPE_TO_VIDEO_FAILED;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbRmvVideoOk(int dwSessId) {
        JRLog.printf("视频转音频成功 %d", dwSessId);
        JRCallNotify.TypeUpdate notify = new JRCallNotify.TypeUpdate();
        notify.callId = dwSessId;
        notify.updateType = JRCallDefine.MTC_CALL_UPDATE_TYPE_TO_AUDIO_OK;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbRmvVideoFailed(int dwSessId, int dwStatCode) {
        JRLog.printf("视频转音频失败 %d", dwSessId);
        JRCallNotify.TypeUpdate notify = new JRCallNotify.TypeUpdate();
        notify.callId = dwSessId;
        notify.updateType = JRCallDefine.MTC_CALL_UPDATE_TYPE_TO_AUDIO_FAILED;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbAddVideoReq(int dwSessId) {
        JRLog.printf("收到转视频请求 %d", dwSessId);
        JRCallNotify.TypeUpdate notify = new JRCallNotify.TypeUpdate();
        notify.callId = dwSessId;
        notify.updateType = JRCallDefine.MTC_CALL_UPDATE_TYPE_REQ_VIDEO;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbInfo(int dwSessId, String info) {
        JRLog.printf("收到消息 %d %s", dwSessId, info);
        JRCallNotify.RecvInfo notify = new JRCallNotify.RecvInfo();
        notify.callId = dwSessId;
        notify.info = info;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCallCbNetStaChanged(int dwSessId, boolean bVideo, boolean bSend, int iType) {
        JRCallNotify.NetStateChanged notify = new JRCallNotify.NetStateChanged();
        notify.callId = dwSessId;
        notify.status = iType;
        MtcEngine.getInstance().dealNotify(notify);
    }


    @Override
    public void mtcConfCbIncoming(int dwConfId) {
        JRLog.printf("会议来电 %d", dwConfId);
        JRCallNotify.Incoming notify = new JRCallNotify.Incoming();
        MtcString ppcDispName = new MtcString();
        MtcString ppcUri = new MtcString();
        MtcConf.Mtc_ConfGetInitiatorUri(dwConfId, ppcDispName, ppcUri);
        notify.displayName = ppcDispName.getValue();
        notify.peerNumber = MtcUri.Mtc_UriGetUserPart(ppcUri.getValue());
        notify.callId = dwConfId;
        notify.renderId = MtcCall.Mtc_SessGetName(dwConfId);
        notify.callType = JRCallDefine.MTC_CALL_TYPE_MULTI_VIDEO;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCbOutgoing(int dwConfId) {
        JRLog.printf("会议呼出 %d", dwConfId);
        JRCallNotify.Outgoing notify = new JRCallNotify.Outgoing();
        notify.callId = dwConfId;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCbAlerted(int dwConfId) {
        JRLog.printf("会议振铃 %d", dwConfId);
        JRCallNotify.Alert notify = new JRCallNotify.Alert();
        notify.callId = dwConfId;
        notify.alertType = MtcCallConstants.MTC_CALL_ALERT_RING;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCbConned(int dwConfId) {
        JRLog.printf("多方视频接通 %d", dwConfId);
        JRCallNotify.Talking notify = new JRCallNotify.Talking();
        MtcString ppcDispName = new MtcString();
        MtcString ppcUri = new MtcString();
        MtcConf.Mtc_ConfGetInitiatorUri(dwConfId, ppcDispName, ppcUri);
        notify.callId = dwConfId;
        notify.displayName = ppcDispName.getValue();
        notify.peerNumber = MtcUri.Mtc_UriGetUserPart(ppcUri.getValue());
        notify.isConf = true;
        notify.isVideo = true;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCbDisced(int dwConfId, int dwStatCode) {
        JRLog.printf("会议结束 %d %d", dwConfId, dwStatCode);
        mtcCallCbTermed(dwConfId, dwStatCode);
    }

    @Override
    public void mtcConfCbIvtAcpt(int dwConfId, String uri) {
        JRLog.printf("多方通话邀请发起成功 %d %s", dwConfId, uri);
    }

    @Override
    public void mtcConfCbIvtFail(int dwConfId, String uri, int iStatCode) {
        JRLog.printf("多方通话邀请发起失败 %d %s %d", dwConfId, uri, iStatCode);
    }

    @Override
    public void mtcConfCbKickAcpt(int dwConfId, String uri) {
        JRLog.printf("多方通话踢出发起成功 %d %s", dwConfId, uri);
    }

    @Override
    public void mtcConfCbKickFail(int dwConfId, String uri, int iStatCode) {
        JRLog.printf("多方通话踢出发起失败 %d %s %d", dwConfId, uri, iStatCode);
    }

    @Override
    public void mtcConfCbPtptUpdt(int iConfId, int iPartpLstId, boolean bFullNtfy) {
        JRCallNotify.MembersUpdate notify = new JRCallNotify.MembersUpdate();
        notify.callId = iConfId;
        int partListSize = MtcPartp.Mtc_PartpLstGetSize(iPartpLstId);
        List<MtcCallMember> array = new ArrayList<>();
        for (int i = 0; i < partListSize; i++) {
            MtcString ppcName = new MtcString();
            MtcNumber pdwPartpId = new MtcNumber();
            MtcString pcUri = new MtcString();
            MtcPartp.Mtc_PartpLstGetPartp(iPartpLstId, i, ppcName, pcUri, pdwPartpId);
            MtcCallMember member = new MtcCallMember();
            member.peerNumber = MtcUri.Mtc_UriGetUserPart(pcUri.getValue());
            member.state = MtcPartp.Mtc_PartpGetCmccConfStat(pdwPartpId.getValue());
            member.displayName = ppcName.getValue();
            JRLog.printf("成员更新 %s %d %d", MtcUri.Mtc_UriGetUserPart(pcUri.getValue()), member.state, bFullNtfy);
        }
        notify.members = array;
        notify.bFull = bFullNtfy;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCbVideoStrmPtptUpdt(int iConfId, int iStrmId, String uri) {
        JRLog.printf("视频流更新 %d %d %s %s ", iConfId, iStrmId, MtcConf.Mtc_ConfGetStrmName(iConfId, iStrmId), uri);
        JRCallNotify.VideoStrmPtptUpdt notify = new JRCallNotify.VideoStrmPtptUpdt();
        notify.callId = iConfId;
        notify.strmName = MtcConf.Mtc_ConfGetStrmName(iConfId, iStrmId);
        notify.number = uri;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCbHoldOk(int dwConfId) {
        JRLog.printf("会议保持成功 %d", dwConfId);
        mtcCallCbHoldOk(dwConfId);
    }

    @Override
    public void mtcConfCbHoldFailed(int dwConfId) {
        JRLog.printf("会议保持失败 %d", dwConfId);
        mtcCallCbHoldFailed(dwConfId);
    }

    @Override
    public void mtcConfCbUnHoldOk(int dwConfId) {
        JRLog.printf("会议解除保持成功 %d", dwConfId);
        mtcCallCbUnHoldOk(dwConfId);
    }

    @Override
    public void mtcConfCbUnHoldFailed(int dwConfId) {
        JRLog.printf("会议解除保持失败 %d", dwConfId);
        mtcCallCbUnHoldFailed(dwConfId);
    }

    @Override
    public void mtcConfCbHeld(int dwConfId) {
        JRLog.printf("会议被保持 %d", dwConfId);
        mtcCallCbHeld(dwConfId);
    }

    @Override
    public void mtcConfCbUnHeld(int dwConfId) {
        JRLog.printf("会议被解除保持 %d", dwConfId);
        mtcCallCbUnHeld(dwConfId);
    }

    @Override
    public void mtcConfCbNetStaChanged(int dwConfId, boolean bSend, int iType) {
        mtcCallCbNetStaChanged(dwConfId, false, bSend, iType);
    }

    @Override
    public void mtcConfCmccCbOutgoing(int iConfId) {
        JRLog.printf("CMCC多方通话呼出 %d", iConfId);
        JRCallNotify.Outgoing notify = new JRCallNotify.Outgoing();
        notify.callId = iConfId;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCmccCbAlerted(int iConfId) {
        JRLog.printf("CMCC多方通话振铃 %d", iConfId);
        JRCallNotify.Alert notify = new JRCallNotify.Alert();
        notify.callId = iConfId;
        notify.alertType = MtcCallConstants.MTC_CALL_ALERT_RING;
        MtcString ppcDisplayName = new MtcString();
        MtcString ppcUri = new MtcString();
        MtcConfCmcc.Mtc_ConfCmccGetConfUri(iConfId, ppcDisplayName, ppcUri);
        notify.confUri = ppcUri.getValue();
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCmccCbConned(int iConfId) {
        JRLog.printf("CMCC多方通话接通 %d", iConfId);
        JRCallNotify.Talking notify = new JRCallNotify.Talking();
        notify.callId = iConfId;
        notify.isConf = true;
        notify.isVideo = false;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcConfCmccCbJoinOk(int iConfId) {
        JRLog.printf("主席加入成功 %d", iConfId);
    }

    @Override
    public void mtcConfCmccCbJoinFailed(int iConfId) {
        JRLog.printf("主席加入失败 %d", iConfId);
    }

    @Override
    public void mtcConfCmccCbIvtAcpt(int iConfId, String userUri) {
        JRLog.printf("多方通话邀请发起成功 %d %s", iConfId, userUri);
    }

    @Override
    public void mtcConfCmccCbIvtFail(int iConfId, String userUri) {
        JRLog.printf("多方通话邀请发起失败 %d %s", iConfId, userUri);
    }

    @Override
    public void mtcConfCmccCbKickAcpt(int iConfId, String userUri) {
        JRLog.printf("多方通话踢出发起成功 %d %s", iConfId, userUri);
    }

    @Override
    public void mtcConfCmccCbKickFail(int iConfId, String userUri) {
        JRLog.printf("多方通话踢出发起失败 %d %s", iConfId, userUri);
    }

    @Override
    public void mtcConfCmccCbPtptUpdt(int iConfId, int iPartpLstId, boolean bFullNtfy) {
        mtcConfCbPtptUpdt(iConfId, iPartpLstId, bFullNtfy);
    }

    @Override
    public void mtcConfCmccCbDisced(int iConfId, int iStatCode) {
        JRLog.printf("CMCC多方通话挂断 %d", iConfId);
        mtcCallCbTermed(iConfId, iStatCode);
    }

    @Override
    public void mtcConfCmccCbMuteOk(int iConfId, String userUri) {
        JRLog.printf("静音成功 %d %s", iConfId, userUri);
    }

    @Override
    public void mtcConfCmccCbMuteFail(int iConfId, String userUri) {
        JRLog.printf("静音失败 %d %s", iConfId, userUri);
    }

    @Override
    public void mtcConfCmccCbUnmuteOk(int iConfId, String userUri) {
        JRLog.printf("解除静音成功 %d %s", iConfId, userUri);
    }

    @Override
    public void mtcConfCmccCbUnmuteFail(int iConfId, String userUri) {
        JRLog.printf("解除静音失败 %d %s", iConfId, userUri);
    }

    @Override
    public void mtcConfCmccCbMuteAllOk(int iConfId) {
        JRLog.printf("静音所有人成功 %d", iConfId);
    }

    @Override
    public void mtcConfCmccCbMuteAllFail(int iConfId) {
        JRLog.printf("静音所有人失败 %d", iConfId);
    }

    @Override
    public void mtcConfCmccCbUnmuteAllOk(int iConfId) {
        JRLog.printf("解除静音所有人成功 %d", iConfId);
    }

    @Override
    public void mtcConfCmccCbUnmuteAllFail(int iConfId) {
        JRLog.printf("静音所有人失败 %d", iConfId);
    }

    @Override
    public void mtcCallCbRefered(int dwSessId) {

    }

    @Override
    public void mtcCallCbTrsfAcpt(int dwSessId) {

    }

    @Override
    public void mtcCallCbTrsfTerm(int dwSessId) {

    }

    @Override
    public void mtcCallCbTrsfFailed(int dwSessId) {

    }

    @Override
    public void mtcCallCbRedirect(int dwSessId) {

    }

    @Override
    public void mtcCallCbInfoX(String pcUri, String info) {

    }

    @Override
    public void mtcCallCbReplaced(int dwSessId) {

    }

    @Override
    public void mtcCallCbReplaceOk(int dwSessId) {

    }

    @Override
    public void mtcCallCbReplaceFailed(int dwSessId) {

    }

    @Override
    public void mtcCallCbSetRtpConnectivity(int dwSessId, boolean bConnected) {

    }

    @Override
    public void mtcCallCbCamDisconned(int dwSessId) {

    }

    @Override
    public void mtcCallCbVideoSize(int dwSessId, int dwWidth, int dwHeight, int dwOrientation) {

    }

    @Override
    public void mtcCallCbVideoIncomingSta(int dwSessId, int dwParm1, int dwParm2) {

    }

    @Override
    public void mtcCallCbVideoOutgoingSta(int dwSessId, int dwParm1, int dwParm2) {

    }

    @Override
    public void mtcCallCbVideoProtectSta(int dwSessId, int dwParm1, int dwParm2) {

    }

    @Override
    public void mtcCallCbCaptureFramerate(int dwSessId, int dwParm) {

    }

    @Override
    public void MtcCallCbCaptureSize(int dwSessId, int dwWidth, int dwHeight) {

    }

    @Override
    public void MtcCallCbPartpUpdted(int dwConfId, int dwStatCode, String uri) {

    }

    @Override
    public void mtcCallCbSetError(int dwSessId, int dwStatCode) {

    }

    @Override
    public void mtcCallCbAddAudioCancel(int dwSessId) {

    }

    @Override
    public void mtcCallCbAddVideoCancel(int dwSessId) {

    }

    @Override
    public void mtcCallCbCryptoStaChanged(int iSessId, int iStatusType, boolean bVideo) {

    }

    @Override
    public void mtcCallCbSendRtpPacket(int iTptId, String pcRmtAddr, byte[] pData) {

    }

    @Override
    public void mtcCallCbRecvRtpPacket(int iStrmId, boolean bVideo, byte[] pucData) {

    }

    @Override
    public void mtcCallCbSetFilePlayStoppedx(int dwSessId) {

    }

    @Override
    public void mtcCallCbPracked(int dwSessId) {

    }


    @Override
    public void mtcCallCbMdfyed(int dwSessId) {

    }


    @Override
    public void mtcCallCbAddAudioOk(int dwSessId) {

    }

    @Override
    public void mtcCallCbAddAudioFailed(int dwSessId) {

    }

    @Override
    public void mtcCallCbRmvAudioOk(int dwSessId) {

    }

    @Override
    public void mtcCallCbRmvAudioFailed(int dwSessId) {

    }

    @Override
    public void mtcCallCbAddAudioReq(int dwSessId) {
    }


    @Override
    public void mtcCallCbMdfyAcpt(int dwSessId) {

    }

    @Override
    public void mtcCallCbMdfyReq(int dwSessId) {

    }

    @Override
    public void mtcConfCbSelectUsrOk(int dwConfId, String uri) {

    }

    @Override
    public void mtcConfCbSelectUsrFail(int dwConfId, String uri, int iStatCode) {

    }

    @Override
    public void mtcConfCbVideoPtptLstUpdt(int iConfId, int iPartpLstId) {

    }

    @Override
    public void mtcConfCbPtptSpkStateUpdt(int iConfId, String uri) {

    }

    @Override
    public void mtcConfCbNoPtptSpk(int iConfId) {

    }

    @Override
    public void mtcConfCbMdfyAcpt(int dwConfId) {

    }

    @Override
    public void mtcConfCbMdfyed(int dwConfId) {

    }

    @Override
    public void mtcConfCbGetConfStateFailed(int iConfId, int iStatCode) {

    }

    @Override
    public void mtcConfCbError(int dwConfId, int dwStatCode) {

    }

    @Override
    public void mtcConfCmccCbError(int iConfId, int iStatCode) {

    }
}
