package com.juphoon.jrsdk;

import java.util.List;

class JRGroupParam {
    public static class Create {
        public String groupName;
        List<String> members;
    }

    public static class Reject {
        public String identity;
    }

    public static class Accept {
        public String identity;
    }

    public static class ModifyChairman {
        public String identity;
        public String number;
    }

    public static class ModifyGroupName {
        public String identity;
        public String groupName;
    }

    public static class ModifyNickName {
        public String identity;
        public String displayName;
    }

    public static class Invite {
        public String identity;
        List<String> numbers;
    }

    public static class Kick {
        public String identity;
        List<String> numbers;
    }

    public static class Leave {
        public String identity;
    }

    public static class Dissolve {
        public String identity;
    }

    public static class Rejoin {
        public String identity;
        public String groupId;
        public String groupName;
        public int groupVersion;
        public int groupType;
    }

    public static class SubscribeInfo {
        public String identity;
    }
}
