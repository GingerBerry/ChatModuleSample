package com.juphoon.jrsdk;

import java.util.ArrayList;

class JRGroupImpl extends JRGroup implements MtcNotifyListener {

    private ArrayList<JRGroupCallback> mCallbacks = new ArrayList<>();

    JRGroupImpl() {
        MtcEngine.getInstance().addCallback(this);
    }

    @Override
    public boolean create(String groupName, ArrayList<String> numbers) {
        if (!isLogined()) {
            return false;
        }
        if (groupName.isEmpty()) {
            JRLog.printf("群名称为空");
            return false;
        }
        return false;
    }

    @Override
    public boolean leave(JRGroupItem item) {
        return false;
    }

    @Override
    public boolean modifyGroupName(JRGroupItem item, String name) {
        return false;
    }

    @Override
    public boolean modifyNickName(JRGroupItem item, String nickName) {
        return false;
    }

    @Override
    public boolean dissolve(JRGroupItem item) {
        return false;
    }

    @Override
    public boolean rejectInvite(JRGroupItem item) {
        return false;
    }

    @Override
    public boolean acceptInvite(JRGroupItem item) {
        return false;
    }

    @Override
    public boolean invite(JRGroupItem item, ArrayList<String> numbers) {
        return false;
    }

    @Override
    public boolean kick(JRGroupItem item, ArrayList<String> numbers) {
        return false;
    }

    @Override
    public boolean modifyChairman(JRGroupItem item, String number) {
        return false;
    }

    @Override
    public boolean subscribeGroupList() {
        return false;
    }

    @Override
    public boolean subscribeGroupInfo(String sessionIdentity) {
        return false;
    }

    @Override
    public void addCallback(JRGroupCallback callback) {

    }

    @Override
    public void removeCallback(JRGroupCallback callback) {

    }

    @Override
    public void onNotify(JRBaseNotify notify) {

    }

    private boolean isLogined() {
        if (JRClient.getInstance().getState() == JRClientConstants.CLIENT_STATE_LOGINED) {
            return true;
        }
        JRLog.printf("当前无登录的账号");
        return false;
    }
}
