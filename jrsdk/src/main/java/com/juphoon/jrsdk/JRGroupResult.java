package com.juphoon.jrsdk;

import java.util.List;

class MtcGroupItem {
    public String groupName;
    public String identity;
    public String groupId;
    public int groupVersion;
    public int groupType;
    public String chairmanNumber;
}

class MtcGroupMember {
    public String number;
    public String displayName;
    public String groupChatId;
    public String sessIdentity;
    public int status;
}

class JRGroupResult {
    public static class Create {
        public boolean isSucceed;
        public Object cookie;
    }

    public static class Reject {
        public boolean isSucceed;
    }

    public static class Accept {
        public boolean isSucceed;
    }

    public static class ModifyChairman {
        public boolean isSucceed;
    }

    public static class ModifyGroupName {
        public boolean isSucceed;
    }

    public static class ModifyNickName {
        public boolean isSucceed;
    }

    public static class Invite {
        public boolean isSucceed;
    }

    public static class Kick {
        public boolean isSucceed;
    }

    public static class Leave {
        public boolean isSucceed;
    }

    public static class Dissolve {
        public boolean isSucceed;
    }

    public static class Rejoin {
        public boolean isSucceed;
        public String identity;
    }

    public static class SubscribeList {
        public boolean isSucceed;
    }

    public static class SubscribeInfo {
        public boolean isSucceed;
    }
}
