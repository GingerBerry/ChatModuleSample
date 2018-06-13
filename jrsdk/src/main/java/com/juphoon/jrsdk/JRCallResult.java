package com.juphoon.jrsdk;

import java.util.List;

class JRCallResult {
    public static class Start {
        public int callId;
        public boolean isSucceed;
        public String renderId;
        public @JRCallDefine.MtcCallTermReason int reason;
    }

    public static class Answer {
        public boolean isSucceed;
    }

    public static class End {
        public boolean isSucceed;
    }

    public static class Mute {
        public boolean isSucceed;
    }

    public static class Hold {
        public boolean isSucceed;
    }

    public static class AddMember {
        public boolean isSucceed;
    }

    public static class RemoveMember {
        public boolean isSucceed;
    }

    public static class GetStatistics {
        public String statistics;
    }

    public static class GetFlow {
        public long sentBps;
        public long recvBps;
        public long sentBytes;
        public long recvBytes;
    }

    public static class SendDtmf {
        public boolean isSucceed;
    }

    public static class Update {
        public boolean isSucceed;
    }

    public static class AnswerUpdate {
        public boolean isSucceed;
    }

    public static class StartVideo {
        public boolean isSucceed;
    }

    public static class Attach {
        public boolean isSucceed;
    }

    public static class Detach {
        public boolean isSucceed;
    }

    public static class ConfMute {
        public boolean isSucceed;
    }

    public static class ConfJoin {
        public boolean isSucceed;
        public int callId;
    }
}
