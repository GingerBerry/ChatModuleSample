/**
 * @file MtcCallCb.java
 * @brief MTC call callbacks Interface Functions
 */
package com.juphoon.cmcc.app.lemon.callback;

import java.util.ArrayList;

/**
 * @brief Class of MTC call callbacks
 */
public class MtcCallCb {
    /**
     * @brief MTC call callbacks
     *
     * In order to receive MTC call callbacks, user should implement this 
     * interface, then use @ref MtcCallCb.setCallback to register callbacks.
     */
    public interface Callback {
        /**
         * @brief callback of indicate received a new call.
         *
         * GUI use like @ref MtcCall::Mtc_SessGetPeerUri, 
         * to get detail information and present those to user. And wait for 
         * user action, accept or decline.
         * "dwSessId" is the ID of session. GUI should use it to get session
         * status.
         * <p>
         * - Use @ref MtcCall::Mtc_SessPeerOfferVideo to check if incoming call has 
         *   video request.
         *   - Has video, show video call alert window with 3 options
         *     - accept
         *     - accept with video
         *     - decline
         *   - No Video, show voice call alert window with 2 options
         *     - accept
         *     - decline
         * - Assign dwSessId to alert window. It will be used when invoke
         *   @ref MtcCall::Mtc_SessAnswer or @ref MtcCall::Mtc_SessTerm.
         * - Get peer information by @ref MtcCall::Mtc_SessGetPeerUri.
         *
         * @param dwSessId The ID of session. GUI should use it to get session
         *                 status. 
         */
        void mtcCallCbIncoming(int dwSessId);

        /**
         * @brief callback of indicate send a new call.
         *
         * GUI use like @ref MtcCall::Mtc_SessGetPeerUri, 
         * to get detail information and present those to user. And show a 
         * outgoing call window.
         * "dwSessId" is the ID of session. GUI should use it to get session
         * status.
         * <p>
         * - Use @ref MtcCall::Mtc_SessHasVideo to check if outgoing call has video request.
         *   - Has video, show video call window
         *   - No Video, show voice call window
         * - Get peer information by @ref MtcCall::Mtc_SessGetPeerUri.
         *
         * @param dwSessId The ID of session. 
         */
        void mtcCallCbOutgoing(int dwSessId);

        /**
         * @brief callback of indicate the callee is ringing.
         *
         * GUI should update the status of outgoing call window
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * <p>
         * - Use @ref MtcCall::Mtc_SessGetEarlyMediaStatus and 
         *   @ref MtcCall::Mtc_SessHasVideo to check if there is a video early media. 
         *   If so, use @ref MtcCall::Mtc_SessHasOfferAnswer to check if offer answer
         *   exchanged is completed. If so, GUI should invoke 
         *   @ref MtcCall::Mtc_SessVideoStart to start receive video early media.
         *
         * "dwAlertType" has below values:
         * - MTC_CALL_ALERT_RING Alerted by 180 ringing.
         * - MTC_CALL_ALERT_QUEUE Alerted by 182 queued.
         * - MTC_CALL_ALERT_PROGRESS Alerted by 183 session progress.
         *
         * @param dwSessId The ID of session.
         * @param dwAlertType The alter type.
         */
        void mtcCallCbAlerted(int dwSessId, int dwAlertType);

        
        /**
         * @brief callback of indicate the callee receive prack.
         *
         * GUI may use reliable provision response to check if peer is still 
         * alive. If do so, GUI should show a incoming call view in this 
         * callback instead incoming callback. GUI should update the status of 
         * outgoing call window.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbPracked(int dwSessId);

        /**
         * @brief callback of indicate the call has established.
         *
         * The call can be a received call or a send out call. GUI should switch 
         * to talking window.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * - Use @ref MtcCall::Mtc_SessHasVideo to check if there is a video media. 
         *   If so, GUI should invoke @ref MtcCall::Mtc_SessVideoStart to start send and
         *   receive video media.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbTalking(int dwSessId);

        /**
         * @brief callback of indicate call is terminated.
         *
         * "dwStatCode" has below values.
         * - MTC_CALL_TERM_BYE Terminated by remote BYE
         * - MTC_CALL_TERM_CANCEL Terminated by remote CANCEL
         * - MTC_CALL_TERM_BUSY Terminated by 486 Busy Here
         * - MTC_CALL_TERM_DECLINE Terminated by 603 Decline
         * - MTC_CALL_ERR_FORBIDDEN Terminated by 403 Forbidden
         * - MTC_CALL_ERR_NOT_FOUND Terminated by 404 Not Found
         * - MTC_CALL_ERR_NOT_ACPTED Terminated by 406 Not Acceptable or 488 Not Acceptable Here
         * - MTC_CALL_ERR_REQ_TERMED Terminated by 487 Request Terminated
         * - MTC_CALL_ERR_INTERNAL_ERR Terminated by 500 Server Internal Error
         * - MTC_CALL_ERR_SRV_UNAVAIL Terminated by 503 Service Unavailable
         * - MTC_CALL_ERR_NOT_EXIST Terminated by 604 Does Not Exist Anywhere
         * The call can be a received call or a send out call. GUI should show 
         * the reason why the call was terminated for a few seconds, and switch to
         * main working window.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         *
         * @param dwSessId The ID of session.
         * @param dwStatCode The terminate state code.
         */
        void mtcCallCbTermed(int dwSessId, int dwStatCode);

        /**
         * @brief callback of indicate call modifying accepted.
         *
         * To change the attribute of media stream is called call modify, 
         * includes hold and add or remove media stream. This callback indicates 
         * the modification is accept by peer. GUI should check which service 
         * has been invoke by user. If user hold or un-hold the call, show hold 
         * or un-hold successfully. If user add video upon a voice call, then 
         * switch to video call window.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbMdfyAcpt(int dwSessId);

        /**
         * @brief callback of indicate call modifyed.
         *
         * This callback indicates modification was invoke by peer, and this 
         * modification is completed. GUI use like 
         * @ref MtcCall::Mtc_SessHasVideo, check media status.
         * And then show corresponding changes.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbMdfyed(int dwSessId);

        /**
         * @brief callback of indicate call modify request.
         *
         * This callback indicates modification was invoke by peer, and this 
         * modification is waiting for user's confirm. GUI use 
         * like @ref MtcCall::Mtc_SessHasAudio and
         * @ref MtcCall::Mtc_SessHasVideo, to check media status and make a decision 
         * whether accept one or all of active these streams.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbMdfyReq(int dwSessId);

        /**
         * @brief callback of indicate call hold successfully.
         *
         * This callback indicates hold operation has been completed 
         * successfully.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbHoldOk(int dwSessId);

        /**
         * @brief callback of indicate call hold failed.
         *
         * This callback indicates hold operation has been failed.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbHoldFailed(int dwSessId);

        /**
         * @brief callback of indicate call un-hold successfully.
         *
         * This callback indicates un-hold operation has been completed 
         * successfully.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbUnHoldOk(int dwSessId);

        /**
         * @brief callback of indicate call un-hold failed.
         *
         * This callback indicates un-hold operation has been failed.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbUnHoldFailed(int dwSessId);

        /**
         * @brief callback of indicate call was held by peer.
         *
         * This callback indicates peer hold the call.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbHeld(int dwSessId);

        /**
         * @brief callback of indicate call was un-held by peer.
         *
         * This callback indicates un-held hold the call.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbUnHeld(int dwSessId);

        /**
         * @brief callback of indicate adding voice stream has been 
         *  completed successfully.
         *
         * This callback indicates the operation of adding voice stream 
         * to the call has been completed successfully.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbAddAudioOk(int dwSessId);

        /**
         * @brief callback of indicate adding voice stream failed.
         *
         * This callback indicates failed to add voice stream to the call.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbAddAudioFailed(int dwSessId);

        /**
         * @brief callback of indicate removing voice stream has been 
         *  completed successfully.
         *
         * This callback indicates the operation of removing voice stream 
         * of the call has been completed successfully.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbRmvAudioOk(int dwSessId);

        /**
         * @brief callback of indicate removing voice stream has been failed.
         *
         * This callback indicates failed to remove voice stream of the call.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbRmvAudioFailed(int dwSessId);

        /**
         * @brief callback of indicate receiving request of adding voice stream.
         *
         * This callback indicates receiving request of adding voice stream
         * which was invoked by peer. GUI should show a message box to inform
         * user this information, and wait for user to accept or reject this requst.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbAddAudioReq(int dwSessId);

        /**
         * @brief callback of indicate adding video stream has been 
         *  completed successfully.
         *
         * This callback indicates the operation of adding video stream 
         * to the call has been completed successfully.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbAddVideoOk(int dwSessId);

        /**
         * @brief callback of indicate adding video stream has been failed.
         *
         * "dwStatCode"has below values.
         * - @ref MTC_CALL_ERR_REJECTED Failed by remote rejected
         * - @ref MTC_CALL_ERR_NETWORK Failed by network disconnected
         * - @ref MTC_CALL_ERR_TIMEOUT Failed by timeout for request
         * This callback indicates failed to add video stream to the call.
         * GUI should update session status according to session ID.
         * "dwSessId"  is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         * @param dwStatCode The Status code of adding video failed.
         */
        void mtcCallCbAddVideoFailed(int dwSessId, int dwStatCode);

        /**
         * @brief callback of indicate removing video stream has been 
         *  completed successfully.
         *
         * This callback indicates the operation of removing video stream 
         * of the call has been completed successfully.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbRmvVideoOk(int dwSessId);

        /**
         * @brief callback of indicate removing video stream has been failed.
         *
         * "dwStatCode"has below values.
         * - @ref MTC_CALL_ERR_REJECTED Failed by remote rejected
         * - @ref MTC_CALL_ERR_NETWORK Failed by network disconnected
         * - @ref MTC_CALL_ERR_TIMEOUT Failed by timeout for request
         * This callback indicates failed to remove video stream of the call.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         * @param dwStatCode The Status code of removing video failed.
         */
        void mtcCallCbRmvVideoFailed(int dwSessId, int dwStatCode);

        /**
         * @brief callback of indicate receiving request of adding video stream.
         *
         * This callback indicates receiving request of adding video stream
         * which was invoked by peer. GUI should show a message box to inform
         * user this information, and wait for user to accept or reject this requst.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbAddVideoReq(int dwSessId);

        /**
         * @brief callback of indicate call refered.
         *
         * This callback indicates the session is transfered by peer to a new
         * destination. The new session will be indicated by callback of 
         * @ref mtcCallCbOutgoing.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbRefered(int dwSessId);

        /**
         * @brief callback of indicate call transferred accepted.
         *
         * This callback indicates receiving a transfer request. GUI should show
         * a dialog to inform user, current call was transfered.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbTrsfAcpt(int dwSessId);

        /**
         * @brief callback of indicate call transferred terminated.
         *
         * For extension.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbTrsfTerm(int dwSessId);

        /**
         * @brief callback of indicate call transferred failed.
         *
         * This callback indicates transfer failed. GUI should show a dialog to 
         * inform user, current call was transfered.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbTrsfFailed(int dwSessId);

        /**
         * @brief callback of indicate call redirected.
         *
         * This callback indicates the outgoing call was forwarded to another
         * number. GUI should update call status.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbRedirect(int dwSessId);
        
        /**
         * @brief callback of indicate receive info.
         *
         * This callback indicates the call has received info message. GUI
        * should update the INFO information.
         * "dwSessId" is the ID of session, GUI should use it to locate call 
         * window.
         *
         * @param dwSessId The ID of session.
         * @param info The info string.
         */
        void mtcCallCbInfo(int dwSessId, String info);

        /**
         * @brief callback of indicate receive info.
         *
         * This callback indicates the call has received info message.
         * GUI should use it to locate call window.
         *
         * @param pcUri The URI(SIP or Tel URI) where the info message from.
         * @param info The info string.
         */
        void mtcCallCbInfoX(String pcUri, String info);
    
        /**
         * @brief Set callback of indicate call replaced.
         *
         * This callback indicates the call has replaced by another call, which
         * should happen when previous call was transfered to another peer. GUI
         * should update the peer information.
         * "dwSessId"  is the ID of session replaced, 
         * GUI should use it to locate call window.
         * "dwNewSessId" is the new session ID. 
         *
         * @param [in] dwSessId session id.
         */
        void mtcCallCbReplaced(int dwSessId);
        
        /**
         * @brief Set callback of indicate call replace ok.
         *
         * This callback indicates the call has replace ok by another call, which
         * should happen when previous call was transfered to another peer. GUI
         * should update the peer information.
         * "dwSessId" is the ID of session replace ok, 
         * GUI should use it to locate call window.
         * "dwNewSessId" is the new session ID. 
         *
         * @param [in] dwSessId Callback function.
         */
        void mtcCallCbReplaceOk(int dwSessId);
        
        /**
         * @brief Set callback of indicate call replace failed.
         *
         * This callback indicates the call has replace failed by another call, which
         * should happen when previous call was transfered to another peer. GUI
         * should update the peer information.
         * "dwSessId" is the ID of session replace failed, 
         * GUI should use it to locate call window.
         * "dwNewSessId" is the new session ID. 
         *
         * @param [in] dwSessId Callback function.
         */
        void mtcCallCbReplaceFailed(int dwSessId);

        /**
         * @brief callback of indicate rtp connectivity.
         *
         * This callback indicates the RTP connectivity status is changed.
         *
         * @param dwSessId is the ID of session, 
         * GUI should use it to locate call window.
         *
         * @param bConned When ZTRUE indicate media
         * engine detected RTP packet received, ZFALSE indicate media engine hasn't
         * detected RTP packet received for about 20 seconds.
         */
        void mtcCallCbSetRtpConnectivity(int dwSessId, boolean bConnected);
    
        /**
         * @brief Set callback of indicate camera has discinnected.
         *
         * This callback indicates the camera has disconnected with this call.
         * "dwSessId" is the ID of session. GUI should use it to locate call
         * window.
         *
         * @param dwSessId The ID of session.
         */        
         void mtcCallCbCamDisconned(int dwSessId);

        /**
         * @brief callback of indicate call's video size changed.
         *
         * This callback indicates the call's video size changed.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * "dwWidth" indicate the width of frame.
         * "dwHeight" indicate the height of frame.
         * "dwOrientation" indicate the orientation.
         * @ref MtcMediaConstants::EN_MTC_ORIENTATION_PORTRAIT
         *
         * @param dwSessId The ID of session.
         * @param dwWidth The width of frame.
         * @param dwHeight The height of frame.
         * @param dwOrientation The orientation.
         */
        void mtcCallCbVideoSize(int dwSessId, int dwWidth, int dwHeight, int dwOrientation);
        
        /**
         * @brief callback of indicate call's network status.
         *
         * This callback indicates the call's network status.
         * GUI should update session status according to session ID.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * "bVideo" indicate if the network status of video stream is changed.
         * "bSend" indicate if the network status of sending direction is changed.
         * "iType" indicate the network status type 
         * @ref MtcMediaConstants::EN_MTC_NET_STATUS_BAD.
         *
         * @param dwSessId The ID of session.
         * @param bVideo The video network status flag.
         * @param bSend The sending direction network status flag.
         * @param iType The network status type.
         */
        void mtcCallCbNetStaChanged(int dwSessId, boolean bVideo, boolean bSend, int iType);

        /**
         * @brief callback of indicate call's video incoming status.
         *
         * This callback indicates the call's video incoming status.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * "dwParm1" indicate the framerate.
         * "dwParm2" indicate the bitrate.
         *
         * @param dwSessId The ID of session.
         * @param dwParm1 The framerate.
         * @param dwParm2 The bitrate.
         */
        void mtcCallCbVideoIncomingSta(int dwSessId, int dwParm1, int dwParm2);

        /**
         * @brief callback of indicate call's video outgoing status.
         *
         * This callback indicates the call's video outgoing status.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * "dwParm1" indicate the framerate.
         * "dwParm2" indicate the bitrate.
         *
         * @param dwSessId The ID of session.
         * @param dwParm1 The framerate.
         * @param dwParm2 The bitrate.
         */
        void mtcCallCbVideoOutgoingSta(int dwSessId, int dwParm1, int dwParm2);

        /**
         * @brief callback of indicate call's video protection status.
         *
         * This callback indicates the call's video protection status.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * "dwParm1" indicate the FEC bitrate in kbps.
         * "dwParm2" indicate the NACK bitrate in kbps.
         *
         * @param dwSessId The ID of session.
         * @param dwParm1 The FEC bitrate in kbps.
         * @param dwParm2 The NACK bitrate in kbps.
         */
        void mtcCallCbVideoProtectSta(int dwSessId, int dwParm1, int dwParm2);

        /**
         * @brief callback of indicate capture framerate.
         *
         * This callback indicates the capture statistics.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * "dwParm" indicate the framerate.
         *
         * @param dwSessId The ID of session.
         * @param dwParm The framerate.
         */
        void mtcCallCbCaptureFramerate(int dwSessId, int dwParm);

        /**
         * @brief callback of indicate capture framerate.
         *
         * This callback indicates the capture size.
         * "dwSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * "dwWidth" indicate capture width.
         * "dwHeight" indicate capture height.
         *
         * @param dwSessId The ID of session.
         * @param dwWidth The capture width.
         * @param dwHeight The capture height.
         */
        void MtcCallCbCaptureSize(int dwSessId, int dwWidth, int dwHeight);
        
        /**
         * @brief Set callback of indicate session participant's status updated.
         *
         * GUI should update session participant's status.
         * "dwSessId" is the ID of session. GUI should use it to locate
         *        call window.
         * "pcUri" is the URI of the participant which status has been updated.
         * "dwState" see below values:
         * -EN_MTC_SESS_PARTP_STATE_PENDING Unconfirmed state
         * -EN_MTC_SESS_PARTP_STATE_DIALINGIN Creating or join a session
         * -EN_MTC_SESS_PARTP_STATE_DIALINGOUT Being invited to a session
         * -EN_MTC_SESS_PARTP_STATE_ALERTING Being invited and is alerting
         * -EN_MTC_SESS_PARTP_STATE_CONNED Attend to a session successfully
         * -EN_MTC_SESS_PARTP_STATE_ONHOLD In hold state
         * -EN_MTC_SESS_PARTP_STATE_DISCING Leaving a session
         * -EN_MTC_SESS_PARTP_STATE_DISCED Already leave a session
         *
         * @param [in] pfnCb Callback function.
         * @param [in] dwStatCode Status code.
         * @param [in] uri participant uri.
         */
        void MtcCallCbPartpUpdted(int dwConfId, int dwStatCode, String uri); 

        /**
         * @brief callback of indicate call error.
         *
         * This callback indicates error occured during call. GUI should show
         * the detail error information to user.
         * "dwSessId" is the ID of the session which occurs error.
         * "dwStatCode" is the error code, it can be
         * - If dwStatCode < 0x1000, contains SIP response status code, use
         *      Sip_ReasonFromCode to get description string
         * - Otherwise, use @ref MtcCall::Mtc_SessGetStatDesc to get description string
         *
         * @param dwSessId The ID of session.
         * @param dwStatCode The error code.
         */
        void mtcCallCbSetError(int dwSessId, int dwStatCode);
        /**
         * @brief callback of indicate cancel request of adding voice stream.
         *
         * This callback indicates cancel request of adding voice stream
         * which was invoked by peer. GUI should cancel showed message box.
         * "dwSessId" is the ID of the session which occurs error.
         * call window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbAddAudioCancel(int dwSessId);
        
        /**
         * @brief callback of indicate cancel request of adding video stream.
         *
         * This callback indicates cancel request of adding video stream
         * which was invoked by peer. GUI should cancel showed message box.
         * "dwSessId" is the ID of the session which occurs error.
         * call window.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbAddVideoCancel(int dwSessId);
        
        /**
         * @brief callback of indicate call's crypto status.
         *
         * This callback indicates the call's crypto status.
         * GUI should update session status according to session ID.
         * "iSessId" is the ID of session. GUI should use it to locate call 
         * window.
         * "iStatusType" indicate the crypto status type 
         * "bVideo" indicate if the crypto status of video stream is changed.
         * @ref MtcCallConstants::EN_MTC_SESS_NO_CRYPTO.
         *
         * @param iSessId The ID of session.
         * @param iStatusType The crypto status type.
         * @param bVideo The video crypto status flag.
         */
        void mtcCallCbCryptoStaChanged(int iSessId, int iStatusType, boolean bVideo);

    /**
         * @brief callback of indicate sending a rtp packtet.
         *
         * This callback indicates sending a  RTP packtet report.
         *
         * "iTptId" in @ref PFN_MTCCALLCBSENDRTPPACKET is the ID of transport id.
         *
         * "pcRmtAddr" in @ref PFN_MTCCALLCBSENDRTPPACKET indicates RTP packet 
         * will be sent the the remote addr
         *
         * "pData" in @ref PFN_MTCCALLCBSENDRTPPACKET is the RTP packet data.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcCallCbSendRtpPacket(int iTptId, String pcRmtAddr, 
                        byte[] pData);
    
    /**
         * @brief callback of indicate receiving a rtp packtet.
         *
         * This callback indicates receiving a RTP packtet.
         *
         * "iStrmId" in @ref PFN_MTCCALLCBRECVRTPPACKET is the ID of stream(audio or video).
         *
         * "bVideo" in @ref PFN_MTCCALLCBRECVRTPPACKET is ZTRUE indicates RTP packet 
         * is video packet, ZFALSE indicate the rtp packet is audio packet.
         *
         * "pData" in @ref PFN_MTCCALLCBRECVRTPPACKET is the RTP packet data.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcCallCbRecvRtpPacket(int iStrmId, boolean bVideo,
                        byte[] pucData);

    /**
         * @brief callback of indicate stream file play stoppedx.
         *
         * This callback indicates stream file play stoppedx occured during call. GUI should show
         * the detail information to user.
         * "dwSessId" is the ID of the session which occurs error.
         *
         * @param dwSessId The ID of session.
         */
        void mtcCallCbSetFilePlayStoppedx(int dwSessId);
    }

    /**
     * @brief MTC call callback init callbacks.
     *
     * This interface will call the native method to register call callback to 
     * MTC.
     */
    private static native void initCallback();

    /**
     * @brief MTC call callback destory callbacks.
     *
     * This interface will call the native method to deregister call callback to 
     * MTC.
     */
    private static native void destroyCallback();

    private static ArrayList<Callback> sCallbacks;
    
     /**
     * @brief MTC call callback set callbacks.
     *
     * Set the active call callback instance which to receive call callbacks.
     * Use null to deregister all call callbacks.
     *
     * @param c The call callback instance.
     */

    public static void registerCallback(final Callback c) {
        if (sCallbacks == null) {
            sCallbacks = new ArrayList<Callback>();
            initCallback();
        }
        sCallbacks.add(c);
    }
    
    public static void unregisterCallback(final Callback c) {
        if (sCallbacks == null) {
            return;
        }
        sCallbacks.remove(c);
        if (sCallbacks.size() == 0) {
            sCallbacks = null;
            destroyCallback();
        }
    }

    private static final int CALLBACK_INCOMING = 0;
    private static final int CALLBACK_OUTGOING = CALLBACK_INCOMING + 1;
    private static final int CALLBACK_ALERTED = CALLBACK_OUTGOING + 1;
    private static final int CALLBACK_PRACKED = CALLBACK_ALERTED + 1;
    private static final int CALLBACK_TALKING = CALLBACK_PRACKED + 1;
    private static final int CALLBACK_TERMED = CALLBACK_TALKING + 1;
    private static final int CALLBACK_MDFY_ACPT = CALLBACK_TERMED + 1;
    private static final int CALLBACK_MDFYED = CALLBACK_MDFY_ACPT + 1;
    private static final int CALLBACK_MDFY_REQ = CALLBACK_MDFYED + 1;
    private static final int CALLBACK_HOLD_OK = CALLBACK_MDFY_REQ + 1;
    private static final int CALLBACK_HOLD_FAILED = CALLBACK_HOLD_OK + 1;
    private static final int CALLBACK_UNHOLD_OK = CALLBACK_HOLD_FAILED + 1;
    private static final int CALLBACK_UNHOLD_FAILED = CALLBACK_UNHOLD_OK + 1;
    private static final int CALLBACK_HELD = CALLBACK_UNHOLD_FAILED + 1;
    private static final int CALLBACK_UNHELD = CALLBACK_HELD + 1;
    private static final int CALLBACK_ADD_AUDIO_OK = CALLBACK_UNHELD + 1;
    private static final int CALLBACK_ADD_AUDIO_FAILED = CALLBACK_ADD_AUDIO_OK + 1;
    private static final int CALLBACK_RMV_AUDIO_OK = CALLBACK_ADD_AUDIO_FAILED + 1;
    private static final int CALLBACK_RMV_AUDIO_FAILED = CALLBACK_RMV_AUDIO_OK + 1;
    private static final int CALLBACK_ADD_AUDIO_REQ = CALLBACK_RMV_AUDIO_FAILED + 1;
    private static final int CALLBACK_ADD_VIDEO_OK = CALLBACK_ADD_AUDIO_REQ + 1;
    private static final int CALLBACK_ADD_VIDEO_FAILED = CALLBACK_ADD_VIDEO_OK + 1;
    private static final int CALLBACK_RMV_VIDEO_OK = CALLBACK_ADD_VIDEO_FAILED + 1;
    private static final int CALLBACK_RMV_VIDEO_FAILED = CALLBACK_RMV_VIDEO_OK + 1;
    private static final int CALLBACK_ADD_VIDEO_REQ = CALLBACK_RMV_VIDEO_FAILED + 1;
    private static final int CALLBACK_REFERED = CALLBACK_ADD_VIDEO_REQ + 1;
    private static final int CALLBACK_TRSF_ACPT = CALLBACK_REFERED + 1;
    private static final int CALLBACK_TRSF_TERM = CALLBACK_TRSF_ACPT + 1;
    private static final int CALLBACK_TRSF_FAILED = CALLBACK_TRSF_TERM + 1;
    private static final int CALLBACK_REDIRECT = CALLBACK_TRSF_FAILED + 1;
    private static final int CALLBACK_INFO = CALLBACK_REDIRECT + 1;
    private static final int CALLBACK_INFOX = CALLBACK_INFO + 1;
    private static final int CALLBACK_REPLACED = CALLBACK_INFOX + 1;
    private static final int CALLBACK_REPLACEOK = CALLBACK_REPLACED + 1;
    private static final int CALLBACK_REPLACEFAILED = CALLBACK_REPLACEOK + 1;
    private static final int CALLBACK_RTP_CONNECTIVITY = CALLBACK_REPLACEFAILED + 1;
    private static final int CALLBACK_CAM_DISCONNED = CALLBACK_RTP_CONNECTIVITY + 1;
    private static final int CALLBACK_VIDEO_SIZE = CALLBACK_CAM_DISCONNED + 1;
    private static final int CALLBACK_NET_STA_CHANGED = CALLBACK_VIDEO_SIZE + 1;
    private static final int CALLBACK_VIDEO_INCOMING_STA = CALLBACK_NET_STA_CHANGED + 1;
    private static final int CALLBACK_VIDEO_OUTGOING_STA = CALLBACK_VIDEO_INCOMING_STA + 1;
    private static final int CALLBACK_VIDEO_PROTECT_STA = CALLBACK_VIDEO_OUTGOING_STA + 1;
    private static final int CALLBACK_CAPTURE_FRAMERATE = CALLBACK_VIDEO_PROTECT_STA + 1;
    private static final int CALLBACK_CAPTURE_SIZE = CALLBACK_CAPTURE_FRAMERATE + 1;
    private static final int CALLBACK_PARTP_UPDATED = CALLBACK_CAPTURE_SIZE + 1;
    private static final int CALLBACK_SET_ERROR = CALLBACK_PARTP_UPDATED + 1;
    private static final int CALLBACK_ADD_AUDIO_CANCEL = CALLBACK_SET_ERROR + 1;
    private static final int CALLBACK_ADD_VIDEO_CANCEL = CALLBACK_ADD_AUDIO_CANCEL + 1;
    private static final int CALLBACK_CRYPTO_STA_CHANGED = CALLBACK_ADD_VIDEO_CANCEL + 1;
    private static final int CALLBACK_SEND_RTP_PACKET = CALLBACK_CRYPTO_STA_CHANGED + 1;
    private static final int CALLBACK_RECV_RTP_PACKET = CALLBACK_SEND_RTP_PACKET + 1;
    private static final int CALLBACK_FILE_PLAY_STOPPEDX = CALLBACK_RECV_RTP_PACKET + 1;

    /**
     * @brief Distribute call callbacks
     *
     * Distribute call callbacks
     */
    private static void mtcCallCbCallback(int function, int arg1, int arg2,
                    int arg3, int arg4, String arg5, boolean arg6, boolean arg7,
                    byte[] arg8, String arg9)
    {
        switch (function)
        {
            case CALLBACK_INCOMING:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbIncoming(arg1);
                }
                break;
            case CALLBACK_OUTGOING:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbOutgoing(arg1);
                }
                break;
            case CALLBACK_ALERTED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAlerted(arg1, arg2);
                }
                break;
            case CALLBACK_PRACKED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbPracked(arg1);
                }
                break;
            case CALLBACK_TALKING:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbTalking(arg1);
                }
                break;
            case CALLBACK_TERMED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbTermed(arg1, arg2);
                }
                break;
            case CALLBACK_MDFY_ACPT:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbMdfyAcpt(arg1);
                }
                break;
            case CALLBACK_MDFYED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbMdfyed(arg1);
                }
                break;
            case CALLBACK_MDFY_REQ:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbMdfyReq(arg1);
                }
                break;
            case CALLBACK_HOLD_OK:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbHoldOk(arg1);
                }
                break;
            case CALLBACK_HOLD_FAILED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbHoldFailed(arg1);
                }
                break;
            case CALLBACK_UNHOLD_OK:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbUnHoldOk(arg1);
                }
                break;
            case CALLBACK_UNHOLD_FAILED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbUnHoldFailed(arg1);
                }
                break;
            case CALLBACK_HELD:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbHeld(arg1);
                }
                break;
            case CALLBACK_UNHELD:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbUnHeld(arg1);
                }
                break;
            case CALLBACK_ADD_AUDIO_OK:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAddAudioOk(arg1);
                }
                break;
            case CALLBACK_ADD_AUDIO_FAILED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAddAudioFailed(arg1);
                }
                break;
            case CALLBACK_RMV_AUDIO_OK:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbRmvAudioOk(arg1);
                }
                break;
            case CALLBACK_RMV_AUDIO_FAILED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbRmvAudioFailed(arg1);
                }
                break;
            case CALLBACK_ADD_AUDIO_REQ:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAddAudioReq(arg1);
                }
                break;
            case CALLBACK_ADD_VIDEO_OK:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAddVideoOk(arg1);
                }
                break;
            case CALLBACK_ADD_VIDEO_FAILED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAddVideoFailed(arg1, arg2);
                }
                break;
            case CALLBACK_RMV_VIDEO_OK:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbRmvVideoOk(arg1);
                }
                break;
            case CALLBACK_RMV_VIDEO_FAILED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbRmvVideoFailed(arg1, arg2);
                }
                break;
            case CALLBACK_ADD_VIDEO_REQ:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAddVideoReq(arg1);
                }
                break;
            case CALLBACK_REFERED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbRefered(arg1);
                }
                break;
            case CALLBACK_TRSF_ACPT:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbTrsfAcpt(arg1);
                }
                break;
            case CALLBACK_TRSF_TERM:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbTrsfTerm(arg1);
                }
                break;
            case CALLBACK_TRSF_FAILED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbTrsfFailed(arg1);
                }
                break;
            case CALLBACK_REDIRECT:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbRedirect(arg1);
                }
                break;
            case CALLBACK_INFO:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbInfo(arg1,arg5);
                }
                break;
            case CALLBACK_INFOX:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbInfoX(arg5,arg9);
                }
                break;
            case CALLBACK_REPLACED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbReplaced(arg1);
                }
                break;
            case CALLBACK_REPLACEOK:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbReplaceOk(arg1);
                }
                break;
            case CALLBACK_REPLACEFAILED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbReplaceFailed(arg1);
                }
                break;
            case CALLBACK_RTP_CONNECTIVITY:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbSetRtpConnectivity(arg1, arg6);
                }
                break;
            case CALLBACK_CAM_DISCONNED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbCamDisconned(arg1);
                }
                break;
            case CALLBACK_VIDEO_SIZE:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbVideoSize(arg1, arg2, arg3, arg4);
                }
                break;
            case CALLBACK_NET_STA_CHANGED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbNetStaChanged(arg1, arg6, arg7, arg2);
                }
                break;
            case CALLBACK_VIDEO_INCOMING_STA:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbVideoIncomingSta(arg1, arg2, arg3);
                }
                break;
            case CALLBACK_VIDEO_OUTGOING_STA:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbVideoOutgoingSta(arg1, arg2, arg3);
                }
                break;
            case CALLBACK_VIDEO_PROTECT_STA:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbVideoProtectSta(arg1, arg2, arg3);
                }
                break;
            case CALLBACK_CAPTURE_FRAMERATE:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbCaptureFramerate(arg1, arg2);
                }
                break;
            case CALLBACK_CAPTURE_SIZE:
                for (Callback sCallback : sCallbacks) {
                    sCallback.MtcCallCbCaptureSize(arg1, arg2, arg3);
                }
                break;
            case CALLBACK_PARTP_UPDATED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.MtcCallCbPartpUpdted(arg1, arg2, arg5);
                }
                break;
            case CALLBACK_SET_ERROR:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbSetError(arg1, arg2);
                }
                break;
            case CALLBACK_ADD_AUDIO_CANCEL:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAddAudioCancel(arg1);
                }
                break;
            case CALLBACK_ADD_VIDEO_CANCEL:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbAddVideoCancel(arg1);
                }
                break;
            case CALLBACK_CRYPTO_STA_CHANGED:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbCryptoStaChanged(arg1, arg2, arg6);
                }
                break;
            case CALLBACK_SEND_RTP_PACKET:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbSendRtpPacket(arg1, arg5, arg8); 
                }
                break;
            case CALLBACK_RECV_RTP_PACKET:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbRecvRtpPacket(arg1, arg6, arg8);
                }
                break;
            case CALLBACK_FILE_PLAY_STOPPEDX:
                for (Callback sCallback : sCallbacks) {
                    sCallback.mtcCallCbSetFilePlayStoppedx(arg1);
                }
                break;
        }
    }
}

