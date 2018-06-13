/**
 * @file MtcPaCb.java
 * @brief MTC public account callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

/**
 * @brief Class of MTC public account callbacks
 */
public class MtcPaCb {
    /**
     * @brief MTC public account callbacks
     *
     * In order to receive MTC public account callbacks, user should implement this 
     * interface, then use @ref MtcPaCb.setCallback to register callbacks.
     */    
    public interface Callback {
 
     /**
      * @brief Set the public account add subscribe successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessAddSubs results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaAddSubsOk(int dwSessId);
     
     /**
      * @brief Set the public account add subscribe failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessAddSubs results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaAddSubsFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account cancel subscribe successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessCancelSubs results
      *
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaCancelSubsOk(int dwSessId);
     
     /**
      * @brief Set the publia account cancel subscribe failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessCancelSubs results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaCancelSubsFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account query user subscribed successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessQryUsrSubs results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaQryUsrSubsOk(int dwSessId);
     
     /**
      * @brief Set the public account query user subscribed failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessQryUsrSubs results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaQryUsrSubsFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account get public list successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPubLst results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaGetPubLstOk(int dwSessId);
     
     /**
      * @brief Set the public account get public list failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPubLst results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaGetPubLstFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account get public list recommend successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPubLstRecmd results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaGetPubLstRecmdOk(int dwSessId);
     
     /**
      * @brief Set the public account get public list recommend failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPubLstRecmd results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaGetPubLstRecmdFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account get public detail successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPubDetail results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaGetPubDetailOk(int dwSessId);
     
     /**
      * @brief Set the public account get public detail failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPubDetail results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaGetPubDetailFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account get public menu successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPubMenu results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaGetPubMenuOk(int dwSessId);
     
     /**
      * @brief Set the public account get public menu failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPubMenu results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaGetPubMenuFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account get previous message successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPrevMsg results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaGetPrevMsgOk(int dwSessId);
     
     /**
      * @brief Set the public account get previous message failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessGetPrevMsg results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaGetPrevMsgFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account complain public successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessComplainPub results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaComplainPubOk(int dwSessId);
     
     /**
      * @brief Set the public account complain public failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessComplainPub results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaComplainPubFailed(int dwSessId, int iStatCode);
     
     /**
      * @brief Set the public account set accept status successfully callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessSetAcptStat results
      *
      * @param [in] dwSessId The id of session.
      */
     void mtcPaCbPaSetAcptStatOk(int dwSessId);
     
     /**
      * @brief Set the public account set accept status failed callback.
      * The callback is one of @ref MtcPa::Mtc_PaSessSetAcptStat results
      *
      * @param [in] dwSessId The id of session.
      * @param [in] dwStatCode status code.
      */
     void mtcPaCbPaSetAcptStatFailed(int dwSessId, int iStatCode);
     }

    /**
     * @brief MTC public account callback init callbacks.
     *
     * This interface will call the native method to register client 
     * provisioning callback to MTC.
     */
    private static native void initCallback();

    /**
     * @brief MTC public account callback destory callbacks.
     *
     * This interface will call the native method to deregister client 
     * provisioning callback to MTC.
     */
    private static native void destroyCallback();

    private static Callback sCallback;

    /**
     * @brief MTC public account callback register callbacks.
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

    private static final int CALLBACK_PA_ADD_SUBS_OK = 0;
    private static final int CALLBACK_PA_ADD_SUBS_FAIL = CALLBACK_PA_ADD_SUBS_OK + 1;
    private static final int CALLBACK_PA_CANCEL_SUBS_OK = CALLBACK_PA_ADD_SUBS_FAIL + 1;
    private static final int CALLBACK_PA_CANCEL_SUBS_FAIL = CALLBACK_PA_CANCEL_SUBS_OK + 1;
    private static final int CALLBACK_PA_QRY_USR_SUBS_OK = CALLBACK_PA_CANCEL_SUBS_FAIL + 1;
    private static final int CALLBACK_PA_QRY_USR_SUBS_FAIL = CALLBACK_PA_QRY_USR_SUBS_OK + 1;
    private static final int CALLBACK_PA_GET_PUB_LST_OK = CALLBACK_PA_QRY_USR_SUBS_FAIL + 1;
    private static final int CALLBACK_PA_GET_PUB_LST_FAIL = CALLBACK_PA_GET_PUB_LST_OK + 1;
    private static final int CALLBACK_PA_GET_PUB_LST_RECMD_OK = CALLBACK_PA_GET_PUB_LST_FAIL + 1;
    private static final int CALLBACK_PA_GET_PUB_LST_RECMD_FAIL = CALLBACK_PA_GET_PUB_LST_RECMD_OK + 1;
    private static final int CALLBACK_PA_GET_PUB_DETAIL_OK = CALLBACK_PA_GET_PUB_LST_RECMD_FAIL + 1;
    private static final int CALLBACK_PA_GET_PUB_DETAIL_FAIL = CALLBACK_PA_GET_PUB_DETAIL_OK + 1;
    private static final int CALLBACK_PA_GET_PUB_MENU_OK = CALLBACK_PA_GET_PUB_DETAIL_FAIL + 1;
    private static final int CALLBACK_PA_GET_PUB_MENU_FAIL = CALLBACK_PA_GET_PUB_MENU_OK + 1;
    private static final int CALLBACK_PA_GET_PREV_MSG_OK = CALLBACK_PA_GET_PUB_MENU_FAIL + 1;
    private static final int CALLBACK_PA_GET_PREV_MSG_FAIL = CALLBACK_PA_GET_PREV_MSG_OK + 1;
    private static final int CALLBACK_PA_COMPLAIN_PUB_OK = CALLBACK_PA_GET_PREV_MSG_FAIL + 1;
    private static final int CALLBACK_PA_COMPLAIN_PUB_FAIL = CALLBACK_PA_COMPLAIN_PUB_OK + 1;
    private static final int CALLBACK_PA_SET_ACPT_STAT_OK = CALLBACK_PA_COMPLAIN_PUB_FAIL + 1;
    private static final int CALLBACK_PA_SET_ACPT_STAT_FAIL = CALLBACK_PA_SET_ACPT_STAT_OK + 1;

    /**
     * @brief Distribute call callbacks
     *
     * Distribute call callbacks
     */
    private static void mtcPaCbCallback(int function, int type, int dwId, String str, int dwStatCode) {
        switch (function) {
            case CALLBACK_PA_ADD_SUBS_OK:
                sCallback.mtcPaCbPaAddSubsOk(dwId);
                break;
            case CALLBACK_PA_ADD_SUBS_FAIL:
                sCallback.mtcPaCbPaAddSubsFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_CANCEL_SUBS_OK:
                sCallback.mtcPaCbPaCancelSubsOk(dwId);
                break;
            case CALLBACK_PA_CANCEL_SUBS_FAIL:
                sCallback.mtcPaCbPaCancelSubsFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_QRY_USR_SUBS_OK:
                sCallback.mtcPaCbPaQryUsrSubsOk(dwId);
                break;
            case CALLBACK_PA_QRY_USR_SUBS_FAIL:
                sCallback.mtcPaCbPaQryUsrSubsFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_GET_PUB_LST_OK:
                sCallback.mtcPaCbPaGetPubLstOk(dwId);
                break;
            case CALLBACK_PA_GET_PUB_LST_FAIL:
                sCallback.mtcPaCbPaGetPubLstFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_GET_PUB_LST_RECMD_OK:
                sCallback.mtcPaCbPaGetPubLstRecmdOk(dwId);
                break;
            case CALLBACK_PA_GET_PUB_LST_RECMD_FAIL:
                sCallback.mtcPaCbPaGetPubLstRecmdFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_GET_PUB_DETAIL_OK:
                sCallback.mtcPaCbPaGetPubDetailOk(dwId);
                break;
            case CALLBACK_PA_GET_PUB_DETAIL_FAIL:
                sCallback.mtcPaCbPaGetPubDetailFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_GET_PUB_MENU_OK:
                sCallback.mtcPaCbPaGetPubMenuOk(dwId);
                break;
            case CALLBACK_PA_GET_PUB_MENU_FAIL:
                sCallback.mtcPaCbPaGetPubMenuFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_GET_PREV_MSG_OK:
                sCallback.mtcPaCbPaGetPrevMsgOk(dwId);
                break;
            case CALLBACK_PA_GET_PREV_MSG_FAIL:
                sCallback.mtcPaCbPaGetPrevMsgFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_COMPLAIN_PUB_OK:
                sCallback.mtcPaCbPaComplainPubOk(dwId);
                break;
            case CALLBACK_PA_COMPLAIN_PUB_FAIL:
                sCallback.mtcPaCbPaComplainPubFailed(dwId, dwStatCode);
                break;
            case CALLBACK_PA_SET_ACPT_STAT_OK:
                sCallback.mtcPaCbPaSetAcptStatOk(dwId);
                break;
            case CALLBACK_PA_SET_ACPT_STAT_FAIL:
                sCallback.mtcPaCbPaSetAcptStatFailed(dwId, dwStatCode);
                break;
        }
    }
}
