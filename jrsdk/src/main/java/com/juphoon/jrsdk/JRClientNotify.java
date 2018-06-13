package com.juphoon.jrsdk;

class JRClientNotify {
    static class Register extends JRBaseNotify {
        public NotifyType type = NotifyType.Client;

        public int reason;
        public int state;
        public String user;
    }
}
