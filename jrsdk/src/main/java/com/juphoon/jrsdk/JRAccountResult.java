package com.juphoon.jrsdk;

import java.util.List;

class JRAccountResult {
    public static class Create {
        public boolean isSucceed;
    }

    public static class SetConfig {
        public boolean isSucceed;
    }

    public static class GetConfig {
        public String value;
    }

    public static class Delete {
        public boolean isSucceed;
    }

    public static class GetList {
        public List<String> accountList;
    }
}
