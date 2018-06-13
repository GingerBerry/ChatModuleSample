package com.juphoon.jrsdk;

import java.util.ArrayList;
import java.util.List;

public class JRCallItem {
    public List<JRCallMember> callMembers = new ArrayList<>();
    public String selfNumber;
    public @JRCallConstants.CallDirection int direction;
    public @JRCallConstants.CallType int type;
    public @JRCallConstants.CallState int state;
    public long beginTime;
    public long endTime;
    public long talkingBeginTime;
    public boolean hold;
    public boolean held;
    public boolean mute;
    public int netStatus;

    int callId;

    boolean isConf() {
        return type == JRCallConstants.CALL_TYPE_MULTI_AUDIO || type == JRCallConstants.CALL_TYPE_MULTI_VIDEO;
    }

    boolean isVideo() {
        return type == JRCallConstants.CALL_TYPE_ONE_ON_ONE_AUDIO || type == JRCallConstants.CALL_TYPE_ONE_ON_ONE_VIDEO;
    }
}
