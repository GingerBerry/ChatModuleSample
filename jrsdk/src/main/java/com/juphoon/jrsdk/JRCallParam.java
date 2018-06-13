package com.juphoon.jrsdk;

import java.util.ArrayList;
import java.util.List;

class JRCallParam {
    public static class Start {
        public List<String> peerNumber = new ArrayList<>();
        public boolean isVideo;
        public boolean isConf;
    }

    public static class Answer {
        public int callId;
        public boolean isVideo;
        public boolean isConf;
    }

    public static class End {
        public int callId;
        public @JRCallDefine.MtcCallTermReason int reason;
        public boolean isConf;
        public boolean isVideo;
    }

    public static class Mute {
        public int callId;
        public boolean isConf;
        public boolean toMute;
    }

    public static class Hold {
        public int callId;
        public boolean isConf;
        public boolean toHold;
    }

    public static class AddMember {
        public int callId;
        public String peerNumber;
        public boolean isVideo;
    }

    public static class RemoveMember {
        public int callId;
        public String peerNumber;
        public boolean isVideo;
    }

    public static class GetStatistics {
        public int callId;
        public boolean isConf;
        public boolean isVideo;
        public String videoSource;
    }

    public static class GetFlow {
        public int callId;
        public boolean isVideo;
    }

    public static class SendDtmf {
        public int callId;
        public int dtmfType;
    }

    public static class Update {
        public int callId;
        public boolean toVideo;
    }

    public static class AnswerUpdate {
        public int callId;
        public boolean toVideo;
    }

    public static class StartVideo {
        public int callId;
    }

    public static class Attach {
        public int callId;
        public boolean isConf;
        public String camera;
    }

    public static class Detach {
        public int callId;
        public boolean isConf;
    }

    public static class ConfMute {
        public int callId;
        public boolean isAll;
        public boolean toMute;
        public String peerNumber;
    }

    public static class ConfJoin {
        public String pcConfUri;
    }
}
