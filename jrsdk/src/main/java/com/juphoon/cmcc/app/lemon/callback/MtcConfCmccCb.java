/**
 * @file MtcConfCmccCb.java
 * @brief MTC conference callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

/**
 * @brief Class of MTC conference callbacks
 */
public class MtcConfCmccCb {
    
    /**
     * @brief MTC cmcc conference callbacks
     *
     * In order to receive MTC cmcc conference callbacks, user should implement this 
     * interface, then use @ref MtcConfCmccCb.setCallback to register callbacks.
     */    
    public interface Callback {
        /**
         * @brief Set callback of client setup an outgoing conference.
         *
         * This callback indicates client has setup an outgoing conference. GUI should not
         * close the conference window, just update the client status in conference.
         * "iConfId" in @ref PFN_MTCCONFCMCCCB is the ID of conference.
         *       GUI use it to locate the conference window.
         *
         * @param [in] iConfId conference Id.
         */
        void mtcConfCmccCbOutgoing(int iConfId);
        
        /**
         * @brief Set callback of receive response during conference establish.
         *
         * This client try to establish a conference. And when receive 1xx response
         * this callback will be invoked. GUI should update status of conference
         * window.
         * "iConfId" in @ref PFN_MTCCONFCMCCCB is the ID of conference.
         *       GUI use it to locate the conference window.
         *
         * @param [in] iConfId conference Id.
         */
        void mtcConfCmccCbAlerted(int iConfId);


        /**
         * @brief Set callback of conference has established.
         *
         * This callback indicates conference has established. GUI should update
         * status of conference window.
         * "iConfId" is the ID of conference. GUI use it to locate the conference
         *   window.
         *
         * @param [in] iConfId conference Id.
         */
        void mtcConfCmccCbConned(int iConfId);

        /**
         * @brief Set callback of client(chairman) join an exist cmcc conference ok.
         *
         * This callback indicates client has join an exist cmcc conference. GUI should not
         * close the conference window, just update the client status in conference.
         * "iConfId" in @ref PFN_MTCCONFCMCCCB is the ID of conference.
         *       GUI use it to locate the conference window.
         *
         * @param [in] iConfId conference Id.
         */
        void mtcConfCmccCbJoinOk(int iConfId);
        
        /**
         * @brief Set callback of client(chairman) join an exist cmcc conference failed.
         *
         * This callback indicates client has join an exist cmcc conference. GUI should not
         * close the conference window, just update the client status in conference.
         * "iConfId" in @ref PFN_MTCCONFCMCCCB is the ID of conference.
         *       GUI use it to locate the conference window.
         *
         * @param [in] iConfId conference Id.
         */
        void mtcConfCmccCbJoinFailed(int iConfId);

        /**
          * @brief Set callback of the invited user has accepted invite.
          *
          * This client add another user to the conference. This callback was invoked
          * when that user accept the invite. Usually, callback which was set by 
          * Mtc_ConfCmccCbSetPtptUpdt will be invoked sequentially. 
          * GUI should then update the participants of the conference. 
          * "iConfId" in @ref PFN_MTCCONFCMCCCB is the id of conference.
          *          GUI use it to locate the conference window.
          * "pcUserUri" in @ref PFN_MTCCONFCMCCCB is the URI of the participant
          *          invited which server has been accepted.
          *
          * @param [in] iConfId conference Id.
          * @param [in] userUri participant uri.
        */
        void mtcConfCmccCbIvtAcpt(int iConfId, String userUri);
        
        /**
         * @brief Set callback of the invited user is failed.
         *
         * This client add another user to the conference. This callback was invoked
         * when that invite user failed. 
         * "iConfId" in @ref PFN_MTCCONFCMCCCB is the id of conference.
         *          GUI use it to locate the conference window.
         * "pcUserUri" in @ref PFN_MTCCONFCMCCCB is the URI of the participant
         *          invited which server has been accepted.
         *
         * @param [in] confUri conference uri Id.
         * @param [in] userUri participant uri.
         */
        void mtcConfCmccCbIvtFail(int iConfId, String userUri);

        /**
         * @brief Set callback of kick user is accepted.
         *
         * This client want to remove a user from the conference. This callback 
         * was invoked when that user has been removed. Usually, 
         * callback which was set by @ref mtcConfCmccCbPtptUpdt will be invoked sequentially. 
         * GUI should then update the participants of the conference. 
         * "iConfId" in @ref PFN_MTCCONFCMCCCB is the id of conference.
         *          GUI use it to locate the conference window.
         * "pcUserUri" in @ref PFN_MTCCONFCMCCCB is the URI of the participant
         *          invited which server has been accepted.
         *
         * @param [in] iConfId conference Id.
         * @param [in] userUri participant uri.
         */
        void mtcConfCmccCbKickAcpt(int iConfId, String userUri);
        
        /**
         * @brief Set callback of kick user is failed.
         *
         * This client want to remove a user from the conference. This callback 
         * was invoked when that kick user failed.
         * "iConfId" in @ref PFN_MTCCONFCMCCCB is the id of conference.
         *          GUI use it to locate the conference window.
         * "pcUserUri" in @ref PFN_MTCCONFCMCCCB is the URI of the participant
         *          invited which server has been accepted.
         *    
         * @param [in] iConfId conference Id.
         * @param [in] userUri participant uri.
         */
        void mtcConfCmccCbKickFail(int iConfId, String userUri);


    /**
          * @brief Set callback of indicate conference participant's status updated.
          *
          * GUI should update conference participant's status.
          * "iConfId" in @ref PFN_MTCCONFCMCCCBPTPTUPDT is the id of conference.
          *          GUI use it to locate the conference window.
          * "iPartpLstId" in @ref PFN_MTCCONFCMCCCBPTPTUPDT is the participant list id of conference.
          * "bFullNtfy" in @ref PFN_MTCCONFCMCCCBPTPTUPDT is the conference state full flag, 
          *           TRUE for "full", FALSE for "partial".

         *
         * @param [in] iConfId conference Id.
         * @param [in] iPartpLstId participant list id.
         * @param [in] bFullNtfy full notify flag.
         */
        void mtcConfCmccCbPtptUpdt(int iConfId, int iPartpLstId,
                        boolean bFullNtfy);

        /**
         * @brief Set callback of disconnected from conference.
         *
         * This callback indicates client has disconnected from conference. GUI 
         * should close conference window.
         * "iConfId" in @ref PFN_MTCCONFCMCCCBERR is the ID of conference.
         *      GUI use it to locate the conference window.
         *
         * @param [in] iConfId conference Id.
         * @param [in] iStatCode Error code.
         */
        void mtcConfCmccCbDisced(int iConfId, int iStatCode); 

        /**
         * @brief Set callback of error occurred during conference.
         *
         * This callback indicates error occurred during call. GUI should show
         * the detail error information(english) to user.
         *
         * @param [in] iConfId conference Id.
         * @param [in] iStatCode Error code.         
         */
        void mtcConfCmccCbError(int iConfId, int iStatCode);

        /**
                * @brief Set callback of mute participant ok.
                *
                * This chairman mutes a participant in the conference. This callback was invoked
                * when server accepts the MUTE request. GUI should then update the UI of the conference. 
                * "iConfId" in @ref PFN_MTCCONFCMCCCBX is the id of conference.
                *          GUI use it to locate the conference window.
                * "pcUserUri" in @ref PFN_MTCCONFCMCCCBX is the URI of the participant.
                *
                * @param [in] iConfId conference Id.
                * @param [in] userUri participant uri.
                */
        void mtcConfCmccCbMuteOk(int iConfId, String userUri);

        /**
                * @brief Set callback of mute participant failed.
                *
                * This chairman mutes a participant in the conference. This callback was invoked
                * when server cannot accept the MUTE request. GUI should then update the UI of the conference. 
                * "iConfId" in @ref PFN_MTCCONFCMCCCBX is the id of conference.
                *          GUI use it to locate the conference window.
                * "pcUserUri" in @ref PFN_MTCCONFCMCCCBX is the URI of the participant.
                *
                * @param [in] iConfId conference Id.
                * @param [in] userUri participant uri.
                */
        void mtcConfCmccCbMuteFail(int iConfId, String userUri);

        /**
                * @brief Set callback of unmute participant ok.
                *
                * This chairman unmutes a participant in the conference. This callback was invoked
                * when server accepts the UNMUTE request. GUI should then update the UI of the conference. 
                * "iConfId" in @ref PFN_MTCCONFCMCCCBX is the id of conference.
                *          GUI use it to locate the conference window.
                * "pcUserUri" in @ref PFN_MTCCONFCMCCCBX is the URI of the participant.
                *
                * @param [in] iConfId conference Id.
                * @param [in] userUri participant uri.
                */
        void mtcConfCmccCbUnmuteOk(int iConfId, String userUri);

        /**
                * @brief Set callback of unmute participant failed.
                *
                * This chairman unmutes a participant in the conference. This callback was invoked
                * when server cannot accept the UNMUTE request. GUI should then update the UI of the conference. 
                * "iConfId" in @ref PFN_MTCCONFCMCCCBX is the id of conference.
                *          GUI use it to locate the conference window.
                * "pcUserUri" in @ref PFN_MTCCONFCMCCCBX is the URI of the participant.
                *
                * @param [in] iConfId conference Id.
                * @param [in] userUri participant uri.
                */
        void mtcConfCmccCbUnmuteFail(int iConfId, String userUri);

        /**
                * @brief Set callback of mute all participants ok.
                *
                * This chairman mutes all participants in the conference. This callback was invoked
                * when server accepts the MUTE request. GUI should then update the UI of the conference. 
                * "iConfId" in @ref PFN_MTCCONFCMCCCB is the id of conference.
                *          GUI use it to locate the conference window.
                *
                * @param [in] iConfId conference Id.
                */
        void mtcConfCmccCbMuteAllOk(int iConfId);

        /**
                * @brief Set callback of mute all participants failed.
                *
                * This chairman mutes all participanst in the conference. This callback was invoked
                * when server cannot accept the MUTE request. GUI should then update the UI of the conference. 
                * "iConfId" in @ref PFN_MTCCONFCMCCCB is the id of conference.
                *          GUI use it to locate the conference window.
                *
                * @param [in] iConfId conference Id.
                */
        void mtcConfCmccCbMuteAllFail(int iConfId);

        /**
                * @brief Set callback of unmute all participants ok.
                *
                * This chairman unmutes all participants in the conference. This callback was invoked
                * when server accepts the UNMUTE request. GUI should then update the UI of the conference. 
                * "iConfId" in @ref PFN_MTCCONFCMCCCB is the id of conference.
                *          GUI use it to locate the conference window.
                *
                * @param [in] iConfId conference Id.
                */
        void mtcConfCmccCbUnmuteAllOk(int iConfId);

        /**
                * @brief Set callback of unmute all participants failed.
                *
                * This chairman unmutes all participant in the conference. This callback was invoked
                * when server cannot accept the UNMUTE request. GUI should then update the UI of the conference. 
                * "iConfId" in @ref PFN_MTCCONFCMCCCB is the id of conference.
                *          GUI use it to locate the conference window.
                *
                * @param [in] iConfId conference Id.
                */
        void mtcConfCmccCbUnmuteAllFail(int iConfId);
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

    private static final int CALLBACK_CONFCMCCCB_OUTGOING = 0;
    private static final int CALLBACK_CONFCMCCCB_ALERTED = CALLBACK_CONFCMCCCB_OUTGOING + 1;
    private static final int CALLBACK_CONFCMCCCB_CONNED = CALLBACK_CONFCMCCCB_ALERTED + 1;
    private static final int CALLBACK_CONFCMCCCB_JOINOK = CALLBACK_CONFCMCCCB_CONNED + 1;
    private static final int CALLBACK_CONFCMCCCB_JOINFAIL = CALLBACK_CONFCMCCCB_JOINOK + 1;
    private static final int CALLBACK_CONFCMCCCB_IVTACPT = CALLBACK_CONFCMCCCB_JOINFAIL + 1;
    private static final int CALLBACK_CONFCMCCCB_IVTFAIL = CALLBACK_CONFCMCCCB_IVTACPT + 1;
    private static final int CALLBACK_CONFCMCCCB_KICKACPT = CALLBACK_CONFCMCCCB_IVTFAIL + 1;
    private static final int CALLBACK_CONFCMCCCB_KICKFAIL = CALLBACK_CONFCMCCCB_KICKACPT + 1;
    private static final int CALLBACK_CONFCMCCCB_PTPT_UPDT = CALLBACK_CONFCMCCCB_KICKFAIL + 1;
    private static final int CALLBACK_CONFCMCCCB_DISCED = CALLBACK_CONFCMCCCB_PTPT_UPDT + 1;
    private static final int CALLBACK_CONFCMCCCB_ERROR = CALLBACK_CONFCMCCCB_DISCED + 1;
    private static final int CALLBACK_CONFCMCCCB_MUTE_OK = CALLBACK_CONFCMCCCB_ERROR + 1;
    private static final int CALLBACK_CONFCMCCCB_MUTE_FAIL = CALLBACK_CONFCMCCCB_MUTE_OK + 1;
    private static final int CALLBACK_CONFCMCCCB_UNMUTE_OK = CALLBACK_CONFCMCCCB_MUTE_FAIL + 1;
    private static final int CALLBACK_CONFCMCCCB_UNMUTE_FAIL = CALLBACK_CONFCMCCCB_UNMUTE_OK + 1;
    private static final int CALLBACK_CONFCMCCCB_MUTE_ALL_OK = CALLBACK_CONFCMCCCB_UNMUTE_FAIL + 1;
    private static final int CALLBACK_CONFCMCCCB_MUTE_ALL_FAIL = CALLBACK_CONFCMCCCB_MUTE_ALL_OK + 1;
    private static final int CALLBACK_CONFCMCCCB_UNMUTE_ALL_OK = CALLBACK_CONFCMCCCB_MUTE_ALL_FAIL + 1;
    private static final int CALLBACK_CONFCMCCCB_UNMUTE_ALL_FAIL = CALLBACK_CONFCMCCCB_UNMUTE_ALL_OK + 1;

    /**
     * @brief Distribute call callbacks
     *
     * Distribute call callbacks
     */
    private static void mtcConfCmccCbCallback(int function, int iConfId, 
                int iStatCode, int iPartpLstId, String userUri, boolean bFullNtfy) {
        switch (function) {
            case CALLBACK_CONFCMCCCB_OUTGOING:
                sCallback.mtcConfCmccCbOutgoing(iConfId);
                break;
            case CALLBACK_CONFCMCCCB_ALERTED:
                sCallback.mtcConfCmccCbAlerted(iConfId);
                break;
            case CALLBACK_CONFCMCCCB_CONNED:
                sCallback.mtcConfCmccCbConned(iConfId);
                break;
            case CALLBACK_CONFCMCCCB_JOINOK:
                sCallback.mtcConfCmccCbJoinOk(iConfId);
                break;
            case CALLBACK_CONFCMCCCB_JOINFAIL:
                sCallback.mtcConfCmccCbJoinFailed(iConfId);
                break;
            case CALLBACK_CONFCMCCCB_IVTACPT:
                sCallback.mtcConfCmccCbIvtAcpt(iConfId, userUri);
                break;
            case CALLBACK_CONFCMCCCB_IVTFAIL:
                sCallback.mtcConfCmccCbIvtFail(iConfId, userUri);
                break;
            case CALLBACK_CONFCMCCCB_KICKACPT:
                sCallback.mtcConfCmccCbKickAcpt(iConfId, userUri);
                break;
            case CALLBACK_CONFCMCCCB_KICKFAIL:
                sCallback.mtcConfCmccCbKickFail(iConfId, userUri);
                break;
            case CALLBACK_CONFCMCCCB_PTPT_UPDT:
                sCallback.mtcConfCmccCbPtptUpdt(iConfId, iPartpLstId, bFullNtfy);
                break;
            case CALLBACK_CONFCMCCCB_DISCED:
                sCallback.mtcConfCmccCbDisced(iConfId, iStatCode);
                break;
            case CALLBACK_CONFCMCCCB_ERROR:
                sCallback.mtcConfCmccCbError(iConfId, iStatCode);
                break;
            case CALLBACK_CONFCMCCCB_MUTE_OK:
                sCallback.mtcConfCmccCbMuteOk(iConfId, userUri);
                break;
            case CALLBACK_CONFCMCCCB_MUTE_FAIL:
                sCallback.mtcConfCmccCbMuteFail(iConfId, userUri);
                break;
            case CALLBACK_CONFCMCCCB_UNMUTE_OK:
                sCallback.mtcConfCmccCbUnmuteOk(iConfId, userUri);
                break;
            case CALLBACK_CONFCMCCCB_UNMUTE_FAIL:
                sCallback.mtcConfCmccCbUnmuteFail(iConfId, userUri);
                break;
            case CALLBACK_CONFCMCCCB_MUTE_ALL_OK:
                sCallback.mtcConfCmccCbMuteAllOk(iConfId);
                break;
            case CALLBACK_CONFCMCCCB_MUTE_ALL_FAIL:
                sCallback.mtcConfCmccCbMuteAllFail(iConfId);
                break;
            case CALLBACK_CONFCMCCCB_UNMUTE_ALL_OK:
                sCallback.mtcConfCmccCbUnmuteAllOk(iConfId);
                break;
            case CALLBACK_CONFCMCCCB_UNMUTE_ALL_FAIL:
                sCallback.mtcConfCmccCbUnmuteAllFail(iConfId);
                break;
        }
    }
}
