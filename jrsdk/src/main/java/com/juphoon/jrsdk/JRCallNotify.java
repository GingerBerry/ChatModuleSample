package com.juphoon.jrsdk;

import java.util.ArrayList;
import java.util.List;

class MtcCallMember {
    public String peerNumber;
    public String displayName;
    public int state;
}

class JRCallNotify {
    public static class Incoming extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public String peerNumber;
        public String displayName;
        public int callId;
        public String renderId;
        public @JRCallDefine.MtcCallType int callType;
    }

    public static class Outgoing extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
    }

    public static class Alert extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public int alertType;
        public String confUri;
    }

    public static class Talking extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public String peerNumber;
        public String displayName;
        public boolean isVideo;
        public boolean isConf;
    }

    public static class Termed extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public @JRCallDefine.MtcCallTermReason int reason;
    }

    public static class TypeUpdate extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public @JRCallDefine.MtcCallUpdateType int updateType;
    }

    public static class Hold extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public boolean holdOk;
    }

    public static class Unhold extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public boolean unholdOk;
    }

    public static class Held extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public boolean held;
    }

    public static class RecvInfo extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public String info;
    }

    public static class NetStateChanged extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public int status;
    }

    public static class MembersUpdate extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public List<MtcCallMember> members = new ArrayList<>();
        public boolean bFull;
    }

    public static class VideoStrmPtptUpdt extends JRBaseNotify {
        public NotifyType type = NotifyType.Call;

        public int callId;
        public String number;
        public String strmName;
    }
}
