package com.juphoon.jrsdk;

class JRClientResult {
    public static class InitSDK {
        public boolean isSucceed;
    }

    public static class Login {
        public boolean isSucceed;
    }

    public static class Logout {
        public boolean isSucceed;
    }

    public static class GetUser {
        public String user;
    }

    public static class GetNumber {
        public String number;
    }

    public static class GetState {
        public int state;
    }

    public static class SetToken {
        public boolean isSucceed;
    }
}
