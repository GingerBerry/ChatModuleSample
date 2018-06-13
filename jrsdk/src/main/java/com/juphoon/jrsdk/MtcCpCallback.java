package com.juphoon.jrsdk;

import com.juphoon.cmcc.app.lemon.MtcCpDb;
import com.juphoon.cmcc.app.lemon.callback.MtcCpCb;

class MtcCpCallback implements MtcCpCb.Callback {
    @Override
    public void mtcCpCbCpOk(int dwCpId) {
        JRLog.printf("Cp 成功");
        JRAutoConfigNotify.Result notify = new JRAutoConfigNotify.Result();
        notify.cpId = dwCpId;
        notify.isSucceed = true;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCpCbCpFailed(int dwCpId, String pcRspCode, int failedCode) {
        JRLog.printf("Cp 失败 %d %d", dwCpId, failedCode);
        JRAutoConfigNotify.Result notify = new JRAutoConfigNotify.Result();
        notify.cpId = dwCpId;
        notify.errorCode = failedCode;
        notify.isSucceed = MtcCpDb.Mtc_CpDbGetVer() > 0;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCpCbCpAuthInd(int dwCpId) {
        JRLog.printf("Cp AuthInd %d", dwCpId);
        JRAutoConfigNotify.AuthInd notify = new JRAutoConfigNotify.AuthInd();
        notify.cpId = dwCpId;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCpCbCpAutoAuthInd(int dwCpId) {
        JRLog.printf("Cp AutoAuthInd %d", dwCpId);
        JRAutoConfigNotify.AuthInd notify = new JRAutoConfigNotify.AuthInd();
        notify.cpId = dwCpId;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCpCbCpExpire(int iExpireType) {
        JRLog.printf("Cp Expire %d", iExpireType);
        JRAutoConfigNotify.Expire notify = new JRAutoConfigNotify.Expire();
        notify.expireType = iExpireType;
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCpCbCpRecvMsg(int dwCpId, String title, String msg) {

    }

    @Override
    public void mtcCpCbCpPromptMSISDN(int dwCpId) {

    }

    @Override
    public void mtcCpCbCpPromptOTP(int dwCpId) {

    }

    @Override
    public void mtcCpCbCpPromptOTPSMS(int dwCpId) {

    }

    @Override
    public void mtcCpCbCpPromptOTPPIN(int dwCpId) {

    }

    @Override
    public void mtcCpCbEMsgReq(int dwMsgId, String subject, String txt, String acptBtnTxt, String declBtnTxt, boolean needPin) {

    }

    @Override
    public void mtcCpCbEMsgAck(int dwMsgId, String subject, String txt) {

    }

    @Override
    public void mtcCpCbEMsgNtfy(int dwMsgId, String subject, String txt, String okBtnTxt) {

    }

    @Override
    public void mtcCpCbCpCfgInd() {
    }

    @Override
    public void mtcCpCbCpReCfgInd() {

    }

}
