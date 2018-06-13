package com.juphoon.jrsdk;

class JRBaseNotify {
     enum NotifyType {
        Client,
        AutoConfig,
        Message,
        Call,
        Group,
    }

    public NotifyType type = NotifyType.Client;
}
