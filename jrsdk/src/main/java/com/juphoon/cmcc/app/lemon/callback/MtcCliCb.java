/**
 * @file MtcCliCb.java
 * @brief MTC client callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

import java.util.ArrayList;

import android.os.Handler;
import android.os.Message;

import com.juphoon.cmcc.app.lemon.MtcCli;

/**
 * @brief Class of MTC client callbacks
 */
public class MtcCliCb {
    /**
     * @brief MTC client callbacks
     *
     * In order to receive MTC client callbacks, user should implement this 
     * interface, then use @ref MtcCliCb.registerCallback to register callbacks.
     */
    public interface Callback {

        /**
         * @brief the REGISETER successfully indication callback.
         * The callback will be notified if REGISETER successfully
         * The callback is one of @ref MtcCli::Mtc_CliLogin results
         */
        public void mtcCliCbRegOk();

        /**
         * @brief the REGISETER failed indication callback.
         * The callback will be notified if REGISETER failed
         * The callback is one of @ref MtcCli::Mtc_CliLogin results
         *
         * @param dwStatCode REGISETER failed code.
         */
        public void mtcCliCbRegFailed(int dwStatCode);
        
        /**
         * @brief Set the authorizaiton indication callback.
         * The callback will be notified if authorizaiton indication
         * The callback is one of @ref Mtc_CliLogin results
         *
         * @param iAuthType is authorization type(see @ref MtcCliConstants::EN_MTC_IMS_AUTH_IMS_AKA...).
         * @param iRegId is used for locate which register.
         * @param pcNonce is used when authorization type is MtcCliConstants::EN_MTC_IMS_AUTH_IMS_AKA.
         *
         */
        public void mtcCliCbAuthInd(int iAuthType, int iRegId,
                String pcNonce);

        /**
         * @brief the login successfully indication callback.
         * The callback will be notified if server login successfully
         * If @ref MtcCliDb::Mtc_CliDbGetUserReg is ZFALSE, this callback will not be called.
         * 
         * The callback is one of @ref MtcCli::Mtc_CliLogin results
         */
        public void mtcCliCbServLoginOk();

        /**
         * @brief the login successfully indication callback.
         * The callback will be notified if local login successfully
         * If @ref MtcCliDb::Mtc_CliDbGetUserReg is ZTRUE, this callback will not be called.
         * 
         * The callback is one of @ref MtcCli::Mtc_CliLogin results
         */
        public void mtcCliCbLclLoginOk();

        /**
         * @brief the login failed indication callback.
         * The callback will be notified if login failed
         * The callback is one of @ref MtcCli::Mtc_CliLogin results
         *
         * @param dwStatCode Login failed code.
         */
        public void mtcCliCbLoginFailed(int dwStatCode);
        
        /**
         * @brief the refresh successfully indication callback.
         * The callback will be notified if refresh successfully
         * The callback is one of @ref MtcCli::Mtc_CliRefresh results
         *
         * @param bActive Indicate if it is a user action results.
         * @param bChanged Indicate if the registration information changed.
         */
        public void mtcCliCbRefreshOk(boolean bActive, boolean bChanged);
        
        /**
         * @brief the refresh indication callback.
         * The callback will be notified if refresh failed
         * The callback is one of @ref MtcCli::Mtc_CliRefresh results
         *
         * @param [in] bActive Indicate if it is a user action results.
         * @param [in] dwStatCode status code.
         */
        public void mtcCliCbRefreshFailed(boolean bActive, int dwStatCode);

        /**
         * @brief the local logout indication callback.
         * The callback will be notified if client is not in register with server.
         * If @ref MtcCliDb::Mtc_CliDbGetUserReg is ZTRUE, this callback will not be called.
         * 
         * The callback is one of @ref MtcCli::Mtc_CliLogout results
         */
        public void mtcCliCbLclLogout();

        /**
         * @brief the server logout indication callback.
         * The callback will be notified if client has un-register from server.
         * If @ref MtcCliDb::Mtc_CliDbGetUserReg is ZFALSE, this callback will not be called.
         * 
         * The callback is one of @ref MtcCli::Mtc_CliLogout results
         *
         * @param bActive Indicate if it is a user action results.
         * @param iStatCode The event type which trigger logout.
         * @param dwExpires The expire time value if server notify logout later.
         */
        public void mtcCliCbServLogout(boolean bActive, int iStatCode, int dwExpires);
        
        /**
         * @brief the register state changed indication callback.
         * The callback will be notified if register state has changed.
         *
         * @param iRegStat is register state type(see @ref MtcCliConstants::MTC_REG_STATE_IDLE).
         * @param dwStatCode is changed reason(see @ref MtcCliConstants::MTC_CLI_ERR_LCL_FAILED).
         */
        public void mtcCliCbRegStatChanged(int iRegStat, int dwStatCode);

        /**
         * @brief the before login indication callback.
         * The callback will be notified before login.
         * 
         */
        public void mtcCliCbBeforeLogin();        
    }
    
    /**
     * @brief MTC client callback set callbacks.
     *
     * Set the active client callback instance which to receive client callbacks.
     * Use unregisterCallback to deregister all client callbacks.
     *
     * @param c The client callback instance.
     */
    public static void registerCallback(final Callback c) {
        if (sCallbacks == null) {
            sCallbacks = new ArrayList<Callback>();
            initCallback();
        }
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                sCallbacks.add(c);
            } 
        });
    }
    
    public static void unregisterCallback(final Callback c) {
        if (sCallbacks == null) {
            return;
        }
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                sCallbacks.remove(c);
                if (sCallbacks.size() == 0) {
                    sCallbacks = null;
                    destroyCallback();
                }
            } 
        }); 
    }
    
    private static ArrayList<Callback> sCallbacks;
    
    /**
     * @brief MTC client callback init callbacks.
     *
     * This interface will call the native method to register client callback to 
     * MTC.
     */
    private static native void initCallback();

    /**
     * @brief MTC client callback destory callbacks.
     *
     * This interface will call the native method to deregister client callback to 
     * MTC.
     */
    private static native void destroyCallback();

    static final Handler sHandler = new Handler() {
        public void handleMessage(Message msg) {
		    Long evnetId = (Long) msg.obj;
		    MtcCli.Mtc_CliDrive(evnetId);
        }
    };

    private static void mtcCliCbEvnt(long eventId) {
        Message msg = sHandler.obtainMessage(1, Long.valueOf(eventId));
        sHandler.sendMessage(msg);
    }

    public static final int CALLBACK_EVENT = 0;
    public static final int CALLBACK_REG_OK = CALLBACK_EVENT + 1;
    public static final int CALLBACK_REG_FAILED = CALLBACK_REG_OK + 1;
    public static final int CALLBACK_AUTH_IND = CALLBACK_REG_FAILED + 1;
    public static final int CALLBACK_SERV_LOGIN_OK = CALLBACK_AUTH_IND + 1;
    public static final int CALLBACK_LCL_LOGIN_OK = CALLBACK_SERV_LOGIN_OK + 1;
    public static final int CALLBACK_LOGIN_FAILED = CALLBACK_LCL_LOGIN_OK + 1;
    public static final int CALLBACK_REFRESH_OK = CALLBACK_LOGIN_FAILED + 1;
    public static final int CALLBACK_REFRESH_FAILED = CALLBACK_REFRESH_OK + 1;
    public static final int CALLBACK_LCL_LOGOUT = CALLBACK_REFRESH_FAILED + 1;
    public static final int CALLBACK_SERV_LOGOUT = CALLBACK_LCL_LOGOUT + 1;
    public static final int CALLBACK_REG_STAT_CHANGED = CALLBACK_SERV_LOGOUT + 1;
    public static final int CALLBACK_BEFORE_LOGIN = CALLBACK_REG_STAT_CHANGED+1;

    public static void mtcCliCbCallback(int function, int arg1, int arg2,
        boolean arg3, boolean arg4, String arg5, String arg6, long arg7) {
        switch (function) {
            case CALLBACK_EVENT:
                mtcCliCbEvnt(arg7);
                break;
            case CALLBACK_REG_OK:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbRegOk();
                }
                break;
            case CALLBACK_REG_FAILED:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbRegFailed(arg1);
                }
                break;
            case CALLBACK_AUTH_IND:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbAuthInd(arg1, arg2, arg5);
                }
                break;
            case CALLBACK_SERV_LOGIN_OK:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbServLoginOk();
                }
                break;
            case CALLBACK_LCL_LOGIN_OK:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbLclLoginOk();
                }
                break;
            case CALLBACK_LOGIN_FAILED:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbLoginFailed(arg1);
                }
                break;
            case CALLBACK_REFRESH_OK:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbRefreshOk(arg3, arg4);
                }
                break;
            case CALLBACK_REFRESH_FAILED:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbRefreshFailed(arg3, arg1);
                }
                break;
            case CALLBACK_LCL_LOGOUT:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbLclLogout();
                }
                break;
            case CALLBACK_SERV_LOGOUT:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbServLogout(arg3, arg1, arg2);
                }
                break;
            case CALLBACK_REG_STAT_CHANGED:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbRegStatChanged(arg1, arg2);
                }
                break;
            case CALLBACK_BEFORE_LOGIN:
                for (Callback c : sCallbacks) {
                    c.mtcCliCbBeforeLogin();
                }
                break;                
        }
    }
}
