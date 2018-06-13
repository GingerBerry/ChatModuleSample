package com.juphoon.jrsdk;

import com.juphoon.cmcc.app.lemon.MtcCli;
import com.juphoon.cmcc.app.lemon.MtcCliDb;
import com.juphoon.cmcc.app.lemon.callback.MtcCliCb;

class MtcClientCallback implements MtcCliCb.Callback {

    @Override
    public void mtcCliCbAuthInd(int iAuthType, int iRegId, String pcNonce) {
        MtcCli.Mtc_CliEnterDgstPwd(iRegId, MtcCliDb.Mtc_CliDbGetAuthPass());
    }

    @Override
    public void mtcCliCbRegStatChanged(int iRegStat, int dwStatCode) {
        JRLog.printf("注册状态改变 %d %d", iRegStat, dwStatCode);
        JRClientNotify.Register notify = new JRClientNotify.Register();
        notify.state = iRegStat;
        notify.reason = dwStatCode;
        notify.user = MtcCliDb.Mtc_CliDbGetUserName();
        MtcEngine.getInstance().dealNotify(notify);
    }

    @Override
    public void mtcCliCbRegOk() {

    }

    @Override
    public void mtcCliCbRegFailed(int dwStatCode) {

    }

    @Override
    public void mtcCliCbServLoginOk() {

    }

    @Override
    public void mtcCliCbLclLoginOk() {

    }

    @Override
    public void mtcCliCbLoginFailed(int dwStatCode) {

    }

    @Override
    public void mtcCliCbRefreshOk(boolean bActive, boolean bChanged) {

    }

    @Override
    public void mtcCliCbRefreshFailed(boolean bActive, int dwStatCode) {

    }

    @Override
    public void mtcCliCbLclLogout() {

    }

    @Override
    public void mtcCliCbServLogout(boolean bActive, int iStatCode, int dwExpires) {

    }

    @Override
    public void mtcCliCbBeforeLogin() {

    }
}
