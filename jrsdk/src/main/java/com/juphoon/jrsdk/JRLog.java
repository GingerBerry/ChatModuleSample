package com.juphoon.jrsdk;

import com.juphoon.cmcc.app.lemon.MtcUtil;

import java.util.Locale;

class JRLog {
    public static void printf(String format, Object... args) {
        MtcUtil.Mtc_AnyLogDbgStr("JRSDK_LOG",
                String.format("%s %s", String.format(Locale.getDefault(), format, args)));
    }
}
