/**
 * @file MtcGbaCb.java
 * @brief MTC gba callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

/**
 * @brief Class of MTC gba callbacks
 */
public class MtcGbaCb {
    /**
     * @brief MTC gba callbacks
     *
     * In order to receive MTC gba callbacks, user should implement this 
     * interface, then use @ref MtcGbaCb.setCallback to register callbacks.
     */    
    public interface Callback {
 
     /**
      * @brief Set the gba add subscribe successfully callback.
      * The callback is one of @ref MtcGba::Mtc_GbaBootStart results
      *
      * @param [in] iBootId The id of bootstrapping.
      */
     void mtcGbaCbBootOk(int iBootId, int zCookie);
     
     /**
      * @brief Set the gba add subscribe failed callback.
      * The callback is one of @ref MtcGba::Mtc_GbaBootStart results
      *
      * @param [in] iBootId The id of bootstrapping.
      * @param [in] zCookie The user cookie.
      * @param [in] dwStatCode status code.
      */
     void mtcGbaCbBootFailed(int iBootId, int zCookie, int dwStatCode);
     
     /**
      * @brief Set the authorizaiton indication callback.
      * The callback will be notified if authorizaiton indication
      * The callback is one of @ref Mtc_GbaBootStart results
      *
      * @param [in] iBootId The id of bootstrapping.
      * @param [in] pcNonce The Nonce used for calculate RES,IK,CK.
      */
     void mtcGbaCbAuthInd(int iBootId, String pcNonce);

      /**
      * @brief Set the btid expired callback.
      * The callback will be notified if btid expired
      */
     void mtcGbaCbBtidExpired();
     }

    /**
     * @brief MTC gba callback init callbacks.
     *
     * This interface will call the native method to register client 
     * provisioning callback to MTC.
     */
    private static native void initCallback();

    /**
     * @brief MTC gba callback destory callbacks.
     *
     * This interface will call the native method to deregister client 
     * provisioning callback to MTC.
     */
    private static native void destroyCallback();

    private static Callback sCallback;

    /**
     * @brief MTC gba callback register callbacks.
     *
     * Set the active client provisioning callback instance which to receive 
     * client provisioning callbacks.
     * Use null to deregister provisioning callbacks.
     *
     * @param c The client provisioning callback instance.
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

    private static final int CALLBACK_GBA_BOOT_OK = 0;
    private static final int CALLBACK_GBA_BOOT_FAIL = CALLBACK_GBA_BOOT_OK + 1;
    private static final int CALLBACK_GBA_AUTH_IND = CALLBACK_GBA_BOOT_FAIL + 1;
    private static final int CALLBACK_GBA_BTID_EXPIRED = CALLBACK_GBA_AUTH_IND + 1;

    /**
     * @brief Distribute call callbacks
     *
     * Distribute call callbacks
     */
    private static void mtcGbaCbCallback(int function, int zCookie, int dwId, int dwStatCode, String nonce, String notused) {
        switch (function) {
            case CALLBACK_GBA_BOOT_OK:
                sCallback.mtcGbaCbBootOk(dwId, zCookie);
                break;
            case CALLBACK_GBA_BOOT_FAIL:
                sCallback.mtcGbaCbBootFailed(dwId, zCookie, dwStatCode);
                break;
            case CALLBACK_GBA_AUTH_IND:
                sCallback.mtcGbaCbAuthInd(dwId, nonce);
                break;
            case CALLBACK_GBA_BTID_EXPIRED:
                sCallback.mtcGbaCbBtidExpired();
                break;
        }
    }
}
