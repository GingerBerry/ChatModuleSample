package com.juphoon.jrsdk;

class JRClientParam {
    public static class InitSDK {

    }

    public static class Login {
        public String accountName;
    }

    public static class SetToken {
        public String token;
        public @JRClientDefine.MtcClientSetTokenType int type;
    }
}
