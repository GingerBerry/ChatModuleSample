/**
 * @file MtcLcsCb.java
 * @brief MTC license callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

import java.util.ArrayList;

import android.os.Handler;
import android.os.Message;

import com.juphoon.cmcc.app.lemon.MtcLcs;

/**
 * @brief Class of MTC license callbacks
 */
public class MtcLcsCb {
    /**
     * @brief MTC license callbacks
     *
     * In order to receive MTC license callbacks, user should implement this 
     * interface, then use @ref MtcLcsCb.registerCallback to register callbacks.
     */
    public interface Callback {
        
        /**
         * @brief the license active result callback.
         * The callback will be notified if refresh failed
         * The callback is one of @ref MtcLcs::Mtc_LcsActiveResult results
         *
         * @param [in] bLicenseActive Indicate license active success or fail.
         */

	 public void MtcLcsActiveResult(boolean bLicenseActive);

    }
    
      /**
     * @brief MTC license callback init callbacks.
     *
     * This interface will call the native method to register license callback to 
     * MTC.
     */
    private static native void initCallback();

    /**
     * @brief MTC license callback destory callbacks.
     *
     * This interface will call the native method to deregister license callback to 
     * MTC.
     */
    private static native void destroyCallback();

    static Callback sCallback;

    /**
     * @brief MTC license callback set callbacks.
     *
     * Set the active call callback instance which to receive license callbacks.
     * Use null to deregister all license callbacks.
     *
     * @param c The license callback instance.
     */
    public static void setCallback(Callback c) {
        if (c != null) {
            if (sCallback == null)
                initCallback();
        } else {
            destroyCallback();
        }
        sCallback = c;
    }


    public static final int CALLBACK_LICENSE_ACTIVE = 0;


    public static void mtcLcsCbCallback(int function, int arg1, int arg2, boolean arg3) {
        switch (function) {
        case CALLBACK_LICENSE_ACTIVE:
            sCallback.MtcLcsActiveResult(arg3);
            break;
        }
    }
}
