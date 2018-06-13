/**
 * @file MtcConfCb.java
 * @brief MTC conference callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

/**
 * @brief Class of MTC conference callbacks
 */
public class MtcConfCb {

    /**
     * @brief MTC conference callbacks
     *
     * In order to receive MTC videoshare callbacks, user should implement this 
     * interface, then use @ref MtcVShareCb.setCallback to register callbacks.
     */    
    public interface Callback {

        /**
         * @brief Set callback of client receive an incoming conference.
         *
         * This callback indicates client receive an incoming conference. GUI should 
         * show the conference window.
         * "dwConfId" is the ID of conference. GUI should assign it to the
         *   corresponding window, use MtcConf::Mtc_ConfGetPeerUri to get detail
         *   information of conference.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbIncoming(int dwConfId);

        /**
         * @brief Set callback of client setup an outgoing conference.
         *
         * This callback indicates client has setup an outgoing conference. GUI should not
         * close the conference window, just update the client status in conference.
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbOutgoing(int dwConfId);

        /**
         * @brief Set callback of receive response during conference establish.
         *
         * This client try to establish a conference. And when receive 1xx response
         * this callback will be invoked. GUI should update status of conference
         * window.
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbAlerted(int dwConfId);

        /**
         * @brief Set callback of conference has established.
         *
         * This callback indicates conference has established. GUI should update
         * status of conference window.
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbConned(int dwConfId);

        /**
         * @brief Set callback of disconnected from conference.
         *
         * This callback indicates client has disconnected from conference. GUI 
         * should close conference window.
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         *
         * @param [in] dwConfId conference Id.
         * @param [in] dwStatCode Error code.         
         */
        void mtcConfCbDisced(int dwConfId, int dwStatCode);

        /**
         * @brief Set callback of the invited user has accepted invite.
         *
         * This client add another user to the conference. This callback was invoked
         * when that user accept the invite. Usually, callback which was set by 
         * MtcConf::Mtc_ConfCbSetPtptUpdt will be invoked sequentially. 
         * GUI should then update the participants of the conference. 
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         * "pcUri" is the URI of the participant invited which server has been accepted.
         *
         * @param [in] dwConfId conference Id.
         * @param [in] uri participant uri.
         */
        void mtcConfCbIvtAcpt(int dwConfId, String uri);
        
        /**
         * @brief Set callback of the invited user is failed.
         *
         * This client add another user to the conference. This callback was invoked
         * when that invite user failed. 
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         * "pcUri" is the URI of the participant invited failed.
         *
         * @param [in] dwConfId conference Id.
         * @param [in] uri participant uri.
         * @param [in] iStatCode Error code.         
         */
        void mtcConfCbIvtFail(int dwConfId, String uri, int iStatCode);

        /**
         * @brief Set callback of kick user is accepted.
         *
         * This client want to remove a user from the conference. This callback 
         * was invoked when that user has been removed. Usually, 
         * callback which was set by @ref mtcConfCbPtptUpdt will be invoked sequentially. 
         * GUI should then update the participants of the conference. 
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         * "pcUri" is the URI of the participant kicked which server has been accepted.
         *
         * @param [in] dwConfId conference Id.
         * @param [in] uri participant uri.
         */
        void mtcConfCbKickAcpt(int dwConfId, String uri);
        
        /**
         * @brief Set callback of kick user is failed.
         *
         * This client want to remove a user from the conference. This callback 
         * was invoked when that kick user failed.
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         * "pcUri" is the URI of the participant kicked failed.
         *
         * @param [in] dwConfId conference Id.
         * @param [in] uri participant uri.
         * @param [in] iStatCode Error code.         
         */
        void mtcConfCbKickFail(int dwConfId, String uri, int iStatCode);

        
        /**
         * @brief Set callback of select user is accepted.
         *
         * This client want to select a user from the conference as the remote image. This callback 
         * was invoked when that user has been selected. 
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         * "pcUri" is the URI of the participant selected which server has been accepted.
         *
         * @param [in] dwConfId conference Id.
         * @param [in] uri participant uri.
         */
        void mtcConfCbSelectUsrOk(int dwConfId, String uri);
        
        /**
         * @brief Set callback of select user is failed.
         *
         * This client want to select a user from the conference. This callback 
         * was invoked when that select user failed.
         * "dwConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         * "pcUri" is the URI of the participant select failed.
         *
         * @param [in] dwConfId conference Id.
         * @param [in] uri participant uri.
         * @param [in] iStatCode Error code.         
         */
        void mtcConfCbSelectUsrFail(int dwConfId, String uri, int iStatCode);

    /**
          * @brief Set callback of indicate conference participant's status updated.
          *
          * GUI should update conference participant's status.
          * "iConfId" in @ref PFN_MTCCONFBPTPTUPDT is the id of conference.
          *          GUI use it to locate the conference window.
          * "iPartpLstId" in @ref PFN_MTCCONFBPTPTUPDT is the participant list id of conference.
          * "bFullNtfy" in @ref PFN_MTCCONFCBPTPTUPDT is the conference state full flag, 
          *           TRUE for "full", FALSE for "partial".
    
         *
         * @param [in] iConfId conference Id.
         * @param [in] iPartpLstId participant list id.
         * @param [in] bFullNtfy full notify flag.
         */
        void mtcConfCbPtptUpdt(int iConfId, int iPartpLstId,
                        boolean bFullNtfy);
    /**
          * @brief Set callback of indicate conference video partp list updated.
          *
          * GUI should update conference participant's video partp list info.
          * "iConfId" in @ref PFN_MTCCONFCBVIDEOPTPTLSTIUPDT is the id of conference.
          *          GUI use it to locate the conference window.
          * "iPartpLstId" in @ref PFN_MTCCONFCBVIDEOPTPTLSTIUPDT is the participant list id of conference.
         *
         * "pcUri" is the URI of the participant select failed.
         * @param [in] iConfId conference Id.
         * @param [in] iPartpLstId participant list id.
         */
        void mtcConfCbVideoPtptLstUpdt(int iConfId, int iPartpLstId);

    /**
          * @brief Set callback of indicate conference participant's video stream info updated.
          *
          * GUI should update conference participant's video stream info.
          * "iConfId" in @ref PFN_MTCCONFCBVIDEOSTRMPTPTUPDT is the id of conference.
          *          GUI use it to locate the conference window.
          * "iStrmId" in @ref PFN_MTCCONFCBVIDEOSTRMPTPTUPDT is the video stream id.
         *  "pcUri" is the number(not uri) of the participant. If no participant, the URI will be "clear".
         *
         * @param [in] iConfId conference Id.
         * @param [in] iStrmId video stream id.
         * @param [in] uri the number(not uri) of the participant. If no participant, the URI will be "clear".
         */
     void mtcConfCbVideoStrmPtptUpdt(int iConfId, int iStrmId, String uri);
    
    /**
          * @brief Set callback of indicate participant is in speaking state.
          *
          * GUI should update conference participant's speaking state info.
          * "iConfId" in @ref PFN_MTCCONFCBSPKSTATEPTPTUPDT is the id of conference.
          *          GUI use it to locate the conference window.
         *  "pcUri" is the number(not uri) of the participant.
         *
         * @param [in] iConfId conference Id.
         * @param [in] uri the number(not uri) of the participant.
         */
        void mtcConfCbPtptSpkStateUpdt(int iConfId, String uri);

    /**
          * @brief Set callback of no participant is in speaking state.
          *
          * GUI should update conference participant's speaking state.
          * "iConfId" in @ref PFN_MTCCONFCBNOPTPTSPK is the id of conference.
          *          GUI use it to locate the conference window.
          *
          * @param [in] iConfId conference Id.
          */
        void mtcConfCbNoPtptSpk(int iConfId);

    /**
         * @brief Set callback of indicate conference modifying accepted.
         *
         * To change the attribute of media stream is called conference modify,
         * includes hold/unhold and add/remove media stream. This callback indicates
         * the modification is accept by peer. GUI should check which service has
         * been invoke by user. If user hold or unhold the call, show hold or
         * unhold successfully. If user add video upon a voice call, then switch
         * to video call window.
         * "dwConfId" is the ID of conference. GUI should use it to locate
         *        conference window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbMdfyAcpt(int dwConfId);

        /**
         * @brief Set callback of indicate conference modified.
         *
         * This callback indicates modification was invoke by peer, and this 
         * modification is completed. 
         * "dwConfId" is the ID of conference. GUI should use it to locate
         *    conference window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbMdfyed(int dwConfId);

        /**
         * @brief Set callback of indicate conference hold OK.
         *
         * This callback indicates hold operation has been completed successfully.
         * GUI should update conference status according to conference ID.
         * "dwConfId" is the ID of conference. GUI should use it to locate
         *    call window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbHoldOk(int dwConfId);

        /**
         * @brief Set callback of indicate conference hold failed.
         *
         * This callback indicates hold operation has been failed.
         * GUI should update conference status according to conference ID.
         * "dwConfId" is the ID of conference. GUI should use it to locate
         *    call window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbHoldFailed(int dwConfId);

        /**
         * @brief Set callback of indicate conference un-hold OK.
         *
         * This callback indicates un-hold operation has been completed successfully.
         * GUI should update conference status according to conference ID.
         * "dwConfId" is the ID of conference. GUI should use it to locate
         *    call window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbUnHoldOk(int dwConfId);

        /**
         * @brief Set callback of indicate conference un-hold failed.
         *
         * This callback indicates un-hold operation has been failed.
         * GUI should update conference status according to conference ID.
         * "dwConfId" is the ID of conference. GUI should use it to locate
         *    call window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbUnHoldFailed(int dwConfId);

        /**
         * @brief Set callback of indicate conference was held by peer.
         *
         * This callback indicates peer hold the conference.
         * GUI should update conference status according to conference ID.
         * "dwConfId" is the ID of conference. GUI should use it to locate
         *    call window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbHeld(int dwConfId);

        /**
         * @brief Set callback of indicate conference was un-held by peer.
         *
         * This callback indicates un-held hold the conference.
         * GUI should update conference status according to conference ID.
         * "dwConfId" is the ID of conference. GUI should use it to locate
         *    call window.
         *
         * @param [in] dwConfId conference Id.
         */
        void mtcConfCbUnHeld(int dwConfId);
        
        /**
         * @brief Set callback of indicate call's network status.
         *
         * This callback indicates the call's network status.
         * GUI should update session status according to session ID.
         * "dwConfId" is the ID of session. GUI should 
         * use it to locate call window.
         * "bSend" indicate if the network status of sending direction is changed.
         * "iType" indicate the network status type @ref EN_MTC_CONF_NET_STATUS_TYPE.
         *
         * @param [in] pfnCb Confback function.
         */
        void mtcConfCbNetStaChanged(int dwConfId, boolean bSend, int iType);

        /**
         * @brief Set callback of error occurred during getting conference state.
         *
         * This callback indicates error occurred during getting conference state. GUI should show
         * the detail error information(english) to user.
         *
         * @param [in] iConfId conference Id.
         * @param [in] iStatCode Error code.         
         */
        void mtcConfCbGetConfStateFailed(int iConfId, int iStatCode);

    /**
         * @brief Set callback of error occurred during conference.
         *
         * This callback indicates error occurred during conference. GUI should show
         * the detail error information(english) to user.
         *
         * @param [in] dwConfId conference Id.
         * @param [in] dwStatCode Error code.         
         */
        void mtcConfCbError(int dwConfId, int dwStatCode);
    }

    /**
     * @brief MTC client provisioning callback init callbacks.
     *
     * This interface will call the native method to register client 
     * provisioning callback to MTC.
     */
    private static native void initCallback();

    /**
     * @brief MTC client provisioning callback destory callbacks.
     *
     * This interface will call the native method to deregister client 
     * provisioning callback to MTC.
     */
    private static native void destroyCallback();

    private static Callback sCallback;
    
    /**
     * @brief MTC client provisioning callback register callbacks.
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

    private static final int CALLBACK_CONFCB_INCOMING = 0;
    private static final int CALLBACK_CONFCB_OUTGOING = CALLBACK_CONFCB_INCOMING + 1;
    private static final int CALLBACK_CONFCB_ALERTED = CALLBACK_CONFCB_OUTGOING + 1;
    private static final int CALLBACK_CONFCB_CONNED = CALLBACK_CONFCB_ALERTED + 1;
    private static final int CALLBACK_CONFCB_DISCED = CALLBACK_CONFCB_CONNED + 1;
    private static final int CALLBACK_CONFCB_IVTACPT = CALLBACK_CONFCB_DISCED + 1;
    private static final int CALLBACK_CONFCB_IVTFAIL = CALLBACK_CONFCB_IVTACPT + 1;
    private static final int CALLBACK_CONFCB_KICKACPT = CALLBACK_CONFCB_IVTFAIL + 1;
    private static final int CALLBACK_CONFCB_KICKFAIL = CALLBACK_CONFCB_KICKACPT + 1;
    private static final int CALLBACK_CONFCB_PTPTUPDT = CALLBACK_CONFCB_KICKFAIL + 1;
    private static final int CALLBACK_CONFCB_MDFYACPT = CALLBACK_CONFCB_PTPTUPDT + 1;
    private static final int CALLBACK_CONFCB_MDFYED = CALLBACK_CONFCB_MDFYACPT + 1;
    private static final int CALLBACK_CONFCB_HOLDOK = CALLBACK_CONFCB_MDFYED + 1;
    private static final int CALLBACK_CONFCB_HOLDFAILED = CALLBACK_CONFCB_HOLDOK + 1;
    private static final int CALLBACK_CONFCB_UNHOLDOK = CALLBACK_CONFCB_HOLDFAILED + 1;
    private static final int CALLBACK_CONFCB_UNHOLDFAILED = CALLBACK_CONFCB_UNHOLDOK + 1;
    private static final int CALLBACK_CONFCB_HELD = CALLBACK_CONFCB_UNHOLDFAILED + 1;
    private static final int CALLBACK_CONFCB_UNHELD = CALLBACK_CONFCB_HELD + 1;
    private static final int CALLBACK_CONFCB_NET_STA_CHANGED = CALLBACK_CONFCB_UNHELD + 1;
    private static final int CALLBACK_CONFCB_ERROR = CALLBACK_CONFCB_NET_STA_CHANGED + 1;
    private static final int CALLBACK_CONFCB_VIDEO_PTPT_LST_UPDT = CALLBACK_CONFCB_ERROR + 1;
    private static final int CALLBACK_CONFCB_VIDEO_STRM_PTPT_UPDT = CALLBACK_CONFCB_VIDEO_PTPT_LST_UPDT + 1;
    private static final int CALLBACK_CONFCB_GET_CONF_STATE_FAILED = CALLBACK_CONFCB_VIDEO_STRM_PTPT_UPDT + 1;
    private static final int CALLBACK_CONFCB_SELECT_USR_OK = CALLBACK_CONFCB_GET_CONF_STATE_FAILED + 1;
    private static final int CALLBACK_CONFCB_SELECT_USR_FAIL = CALLBACK_CONFCB_SELECT_USR_OK + 1;
    private static final int CALLBACK_CONFCB_PTPT_SPK_STATE_UPDT = CALLBACK_CONFCB_SELECT_USR_FAIL + 1;
    private static final int CALLBACK_CONFCB_NO_PTPT_SPK = CALLBACK_CONFCB_PTPT_SPK_STATE_UPDT + 1;
    
    /**
     * @brief Distribute call callbacks
     *
     * Distribute call callbacks
     */
    private static void mtcConfCbCallback(int function, int dwConfId,
                    int dwStatCode, String uri, boolean bSend) {
        switch (function) {
            case CALLBACK_CONFCB_INCOMING:
                sCallback.mtcConfCbIncoming(dwConfId);
                break;
            case CALLBACK_CONFCB_OUTGOING:
                sCallback.mtcConfCbOutgoing(dwConfId);
                break;
            case CALLBACK_CONFCB_ALERTED:
                sCallback.mtcConfCbAlerted(dwConfId);
                break;
            case CALLBACK_CONFCB_CONNED:
                sCallback.mtcConfCbConned(dwConfId);
                break;
            case CALLBACK_CONFCB_DISCED:
                sCallback.mtcConfCbDisced(dwConfId, dwStatCode);
                break;
            case CALLBACK_CONFCB_IVTACPT:
                sCallback.mtcConfCbIvtAcpt(dwConfId, uri);
                break;
            case CALLBACK_CONFCB_IVTFAIL:
                sCallback.mtcConfCbIvtFail(dwConfId, uri, dwStatCode);
                break;
            case CALLBACK_CONFCB_KICKACPT:
                sCallback.mtcConfCbKickAcpt(dwConfId, uri);
                break;
            case CALLBACK_CONFCB_KICKFAIL:
                sCallback.mtcConfCbKickFail(dwConfId, uri, dwStatCode);
                break;
            case CALLBACK_CONFCB_PTPTUPDT:
                sCallback.mtcConfCbPtptUpdt(dwConfId, dwStatCode, bSend);
                break;
            case CALLBACK_CONFCB_MDFYACPT:
                sCallback.mtcConfCbMdfyAcpt(dwConfId);
                break;
            case CALLBACK_CONFCB_MDFYED:
                sCallback.mtcConfCbMdfyed(dwConfId);
                break;
            case CALLBACK_CONFCB_HOLDOK:
                sCallback.mtcConfCbHoldOk(dwConfId);
                break;
            case CALLBACK_CONFCB_HOLDFAILED:
                sCallback.mtcConfCbHoldFailed(dwConfId);
                break;
            case CALLBACK_CONFCB_UNHOLDOK:
                sCallback.mtcConfCbUnHoldOk(dwConfId);
                break;
            case CALLBACK_CONFCB_UNHOLDFAILED:
                sCallback.mtcConfCbUnHoldFailed(dwConfId);
                break;
            case CALLBACK_CONFCB_HELD:
                sCallback.mtcConfCbHeld(dwConfId);
                break;
            case CALLBACK_CONFCB_UNHELD:
                sCallback.mtcConfCbUnHeld(dwConfId);
                break;
            case CALLBACK_CONFCB_NET_STA_CHANGED:
                sCallback.mtcConfCbNetStaChanged(dwConfId, bSend, dwStatCode);
                break;
            case CALLBACK_CONFCB_ERROR:
                sCallback.mtcConfCbError(dwConfId, dwStatCode);
                break;
            case CALLBACK_CONFCB_VIDEO_PTPT_LST_UPDT:
                sCallback.mtcConfCbVideoPtptLstUpdt(dwConfId, dwStatCode);
                break;
            case CALLBACK_CONFCB_VIDEO_STRM_PTPT_UPDT:
                sCallback.mtcConfCbVideoStrmPtptUpdt(dwConfId, dwStatCode, uri);
                break;
            case CALLBACK_CONFCB_GET_CONF_STATE_FAILED:
                sCallback.mtcConfCbGetConfStateFailed(dwConfId, dwStatCode);
                break;
            case CALLBACK_CONFCB_SELECT_USR_OK:
                sCallback.mtcConfCbSelectUsrOk(dwConfId, uri);
                break;
            case CALLBACK_CONFCB_SELECT_USR_FAIL:
                sCallback.mtcConfCbSelectUsrFail(dwConfId, uri, dwStatCode);
                break;
            case CALLBACK_CONFCB_PTPT_SPK_STATE_UPDT:
                sCallback.mtcConfCbPtptSpkStateUpdt(dwConfId, uri);
                break;
            case CALLBACK_CONFCB_NO_PTPT_SPK:
                sCallback.mtcConfCbNoPtptSpk(dwConfId);
                break;
        }
    }
}
