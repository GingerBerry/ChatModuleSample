package com.juphoon.jrsdk;

import java.util.Map;

class JRAccountParam {
    public static class Create {
        public String accountName;
    }

    public static class SetConfig {
        public String accountName;
        public Map<JRAccountDefine.ConfigKey, String> extraParams;
    }

    public static class GetConfig {
        public String accountName;
        public JRAccountDefine.ConfigKey key;
    }

    public static class Delete {
        public String accountName;
    }
}
