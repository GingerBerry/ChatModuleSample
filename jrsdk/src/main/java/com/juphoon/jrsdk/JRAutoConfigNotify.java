package com.juphoon.jrsdk;

class JRAutoConfigNotify {
    public static class Result extends JRBaseNotify {
        public NotifyType type = NotifyType.AutoConfig;

        public int cpId;
        public int errorCode;
        public boolean isSucceed;

    }

    public static class Expire extends JRBaseNotify {
        public NotifyType type = NotifyType.AutoConfig;

        public int expireType;
    }

    public static class AuthInd extends JRBaseNotify {
        public NotifyType type = NotifyType.AutoConfig;

        public int cpId;
    }
}
