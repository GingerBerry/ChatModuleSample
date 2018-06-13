/**
 * @file MtcImCb.java
 * @brief MTC im callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

/**
 * @brief Class of MTC im callbacks
 */
public class MtcImCb {

    public interface Callback {

        /**
         * @brief Set the page message received a new message callback.
         * The callback will be notified if user received a new message.
         *
         * @param [in] dwMsgId The message id, you can use it to get message info like MtcIm::Mtc_ImPMsgGetPartp.
         */
        void mtcImCbPMsgRecvMsg(int dwMsgId);

        /**
         * @brief Set the page message received a new SMS-INFO message callback.
         * The callback will be notified if user received a new message.
         *
         * @param [in] dwMsgId The message id, you can use it to get message info.
         */
        void mtcImCbPMsgRecvSmsInfo(int dwMsgId);

        /**
         * @brief Set the page message send one text message successfully callback.
         * The callback is one of @ref MtcIm::Mtc_ImPMsgSend results.
         *
         * @param [in] dwMsgId The message id, you can use it to get message info.
         */
        void mtcImCbPMsgSendOk(int dwMsgId);

        /**
         * @brief Set the page message send one text message failed callback.
         * The callback is one of @ref MtcIm::Mtc_ImPMsgSend results.
         *
         * @param [in] dwMsgId The message id,you can use it to get message info.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbPMsgSendFailed(int dwMsgId, int dwStatCode);

        /**
         * @brief Set the large message received a new large message callback.
         * The callback will be notified if user received a new message.
         *
         * @param [in] dwMsgId The message id,you can use it to get message info.
         */
        void mtcImCbLMsgRecvMsg(int dwMsgId);

        /**
         * @brief Set the large message send one large message successfully callback.
         * The callback is one of @ref MtcImLarge::Mtc_ImLMsgSend results.
         *
         * @param [in] dwMsgId The message id,you can use it to get message info.
         */
        void mtcImCbLMsgSendOk(int dwMsgId);

        /**
         * @brief Set the large message send one large message failed callback.
         * The callback is one of @ref MtcImLarge::Mtc_ImLMsgSend results.
         *
         * @param [in] dwMsgId The message id,you can use it to get message info.
         * @param [in] dwStatCode Status code.                                   
         */
        void mtcImCbLMsgSendFailed(int dwMsgId, int dwStatCode);

        /**
         * @brief Set the session received a new invitation callback.
         * The callback will be notified if user received session invitation.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessRecvIvt(int dwSessId);


        /**
         * @brief Set the session replace with the previous session id callback.
         * The callback will be notified if user received a new session invitation 
         * whitch will replace the previous session.
         *
         * @param [in] dwSessId Old Session Id.
         * @param [in] dwReSessId New Session Id.
         */
        void mtcImCbSessReplace(int dwSessId, int dwReSessId);

        /**
         * @brief Set the session replace with the previous session id and a message in 
         * the new invite callback.
         * The callback will be notified if user received a new session invitation with 
         * first message whitch will replace the previous session.
         *
         * @param [in] dwSessId Old Session Id.  
         * @param [in] dwReSessId New Session Id.   
         * @param [in] dwMsgId The message id,you can use it to get message info. 
         */
        void mtcImCbSessReplaceM(int dwSessId, int dwReSessId, int dwMsgId);

        /**
         * @brief Set the session received a new invitation with frist message callback.
         * The callback will be notified if user received session invitation with frist message in it.
         *
         * @param [in] dwSessId Old Session Id.                                   
         * @param [in] dwMsgId The message id,you can use it to get message info. 
         */
        void mtcImCbSessRecvIvtM(int dwSessId, int dwMsgId);
		
		/**
         * @brief Set the session invite signal accepted callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessEstab, @ref MtcImSess::Mtc_ImSessEstabU 
         * or @ref MtcImSess::Mtc_ImSessExtend results.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessSignalAcpted(int dwSessId);
		
        /**
         * @brief Set the session invite accepted callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessEstab, @ref MtcImSess::Mtc_ImSessEstabU 
         * or @ref MtcImSess::Mtc_ImSessExtend results.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessAcpted(int dwSessId);

        /**
         * @brief Set the session invite rejected callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessEstab, @ref MtcImSess::Mtc_ImSessEstabU 
         * or @ref MtcImSess::Mtc_ImSessExtend results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwStatCode status code.
         */
        void mtcImCbSessRejected(int dwSessId, int dwStatCode);

        /**
         * @brief Set the session canceled callback.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessCanceled(int dwSessId);

        /**
         * @brief Set the session released callback.
         * The callback will be notified if user received session release.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwStatCode status code.         
         */
        void mtcImCbSessReleased(int dwSessId, int dwStatCode);
        
        /**
         * @brief Set the session leave successfully callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessLeave results.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessLeaveOk(int dwSessId); 
        
        /**
         * @brief Set the session leave failed callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessLeave results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwStatCode status code.         
         */
        void mtcImCbSessLeaveFailed(int dwSessId, int dwStatCode); 
        
        /**
         * @brief Set the session dissolve successfully callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessDissolve results.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessDissolveOk(int dwSessId); 
        
        /**
         * @brief Set the session dissolve failed callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessDissolve results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwStatCode status code.         
         */
        void mtcImCbSessDissolveFailed(int dwSessId, int dwStatCode); 

        /**
         * @brief Set the session composing callback.
         * The callback will be notified if the session iscomposing state change.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwPartpId participant id.
         */
        void mtcImCbSessComposing(int dwSessId, int dwPartpId);

        /**
         * @brief Set the session add participant successfully callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessAddPartp, MtcImSess::Mtc_ImSessAddPartpU results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwPartpLstId participant list Id.
         */
        void mtcImCbSessPartpAddOk(int dwSessId, int dwPartpLstId);

        /**
         * @brief Set the session add participant failed callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessAddPartp, MtcImSess::Mtc_ImSessAddPartpU results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwPartpLstId participant list Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbSessPartpAddFailed(int dwSessId, int dwPartpLstId, int dwStatCode);

        /**
         * @brief Set the session expel participant successfully callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessEplPartp results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwPartpLstId participant list Id.
         */
        void mtcImCbSessPartpEplOk(int dwSessId, int dwPartpLstId);

        /**
         * @brief Set the session expel participant failed callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessEplPartp results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwPartpLstId participant list Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbSessPartpEplFailed(int dwSessId, int dwPartpLstId, int dwStatCode);

        /**
         * @brief Set the session updated participant information callback.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwPartpLstId participant list Id.
         * @param [in] bFullNtfy Fully notify flag.
         */
        void mtcImCbSessPartpUpted(int dwSessId, int dwPartpLstId, boolean bFullNtfy);

        /**
         * @brief Set the session subject modify successfully callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessMdfySubject results.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessSubjectMdfyOk(int dwSessId);

        /**
         * @brief Set the session subject modify failed callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessMdfySubject results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbSessSubjectMdfyFailed(int dwSessId, int dwStatCode);
        
        /**
         * @brief Set the session Chairman modify successfully callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessMdfyChairman results.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessChairmanMdfyOk(int dwSessId);

        /**
         * @brief Set the session Chairman modify failed callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessMdfyChairman results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbSessChairmanMdfyFailed(int dwSessId, int dwStatCode);
        
        /**
         * @brief Set the session display name modify successfully callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessMdfyDispName results.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessDispNameMdfyOk(int dwSessId);

        /**
         * @brief Set the session display name modify failed callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessMdfyDispName results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbSessDispNameMdfyFailed(int dwSessId, int dwStatCode);

        /**
         * @brief Set the session Chairman modify request callback.
         * The callback will be notified if user received modify chairman request.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbSessChairmanMdfyReq(int dwSessId);

        /**
         * @brief Set the session message received a new message callback.
         * The callback will be notified if user received a new message.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwMsgId The message id,you can use it to get message info.
         */
        void mtcImCbSessMsgRecvMsg(int dwSessId, int dwMsgId);

        /**
         * @brief Set the session message send one message successfully callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessMsgSend results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwMsgId The message id,you can use it to get message info.
         */
        void mtcImCbSessMsgSendOk(int dwSessId, int dwMsgId);

        /**
         * @brief Set the session message send one message failed callback.
         * The callback is one of @ref MtcImSess::Mtc_ImSessMsgSend results.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwMsgId message Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbSessMsgSendFailed(int dwSessId, int dwMsgId, int dwStatCode);

        /**
         * @brief Set the store and forward received a new invitation callback.
         * The callback will be notified if user received store and forward invitation.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbStFwdRecvIvt(int dwSessId);

        /**
         * @brief Set the store and forward received a new invitation with frist message callback.
         * The callback will be notified if user received store and forward with frist message invitation.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwMsgId The message id,you can use it to get message info.
         */
        void mtcImCbStFwdRecvIvtM(int dwSessId, int dwMsgId);

        /**
         * @brief Set the store and forward replace with the previous session id callback.
         * The callback will be notified if user replace the store and forward session.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwReSessId Replace Session Id.
         */
        void mtcImCbStFwdReplace(int dwSessId, int dwReSessId);

        /**
         * @brief Set the store and forward replace with the previous session id and a message in the new invite callback.
         * The callback will be notified if user replace the store and forward session.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwReSessId Replace Session Id.
         * @param [in] dwMsgId The message id,you can use it to get message info.
         */
        void mtcImCbStFwdReplaceM(int dwSessId, int dwReSessId, int dwMsgId);


        /**
         * @brief Set the store and forward canceled callback.
         *
         * @param [in] dwSessId Session Id.
         */
        void mtcImCbStFwdCanceled(int dwSessId);

        /**
         * @brief Set the store and forward released callback.
         * The callback will be notified if user received store and forward release.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbStFwdReleased(int dwSessId, int dwStatCode);

        /**
         * @brief Set the store and forward message received a new message callback.
         * The callback will be notified if user received a new store and forward message.
         *
         * @param [in] dwSessId Session Id.
         * @param [in] dwMsgId The message id,you can use it to get message info.
         */
        void mtcImCbStFwdMsgRecvMsg(int dwSessId, int dwMsgId);

        /**
         * @brief Set the imdn received a delivery notification callback.
         * The callback will be notified if user received a imdn delivery notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id. 
         */
        void mtcImCbImdnRecvDeliNtfy(int dwImdnId);

        /**
         * @brief Set the imdn received a delivery failed notification callback.
         * The callback will be notified if user received a imdn delivery failed notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id. 
         */
        void mtcImCbImdnRecvDeliFailed(int dwImdnId, int dwStatCode);

		/**
         * @brief Set the imdn received a delivery forwarded notification callback.
         * The callback will be notified if user received a imdn delivery forwarded notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id. 
         */
        void mtcImCbImdnRecvDeliForwarded(int dwImdnId);

        /**
         * @brief Set the imdn received a display notification callback.
         * The callback will be notified if user received a imdn display notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId, @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id.
         */
        void mtcImCbImdnRecvDispNtfy(int dwImdnId);
        
        /**
         * @brief Set the imdn received a burn after reading notification callback.
         * The callback will be notified if user received a imdn burn after reading notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId, @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id.
         */
        void mtcImCbImdnRecvBurnNtfy(int dwImdnId);

		/**
         * @brief Set the imdn sent a delivery ok notification callback.
         * The callback will be notified if user sent a imdn delivery ok notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id. 
         */
        void mtcImCbImdnSendDeliOk(int dwImdnId);

		/**
         * @brief Set the imdn sent a delivery failed notification callback.
         * The callback will be notified if user sent a imdn delivery failed notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id. 
         */
        void mtcImCbImdnSendDeliFailed(int dwImdnId, int dwStatCode);

		/**
         * @brief Set the imdn sent a display ok notification callback.
         * The callback will be notified if user sent a imdn delivery ok notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id. 
         */
        void mtcImCbImdnSendDispOk(int dwImdnId);

		/**
         * @brief Set the imdn sent a display failed notification callback.
         * The callback will be notified if user sent a imdn display failed notification.
         * you can use dwImdnId to get more info @ref MtcImImdn::Mtc_ImdnGetIMsgId @ref MtcImImdn::Mtc_ImdnGetPartp.
         *
         * @param [in] dwImdnId mdn message id. 
         */
        void mtcImCbImdnSendDispFailed(int dwImdnId, int dwStatCode);

        /**
         * @brief Set the file transfer received a new invitation callback.
         * The callback will be notified if user received file transfer invitation.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName
         */
        void mtcImCbFileRecvIvt(int dwTrsfId);

        /**
         * @brief Set the file transfer receiving a file data callback.
         * The callback will be notified if user receiving file transfer data.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize have recived size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFileRecving(int dwTrsfId, int iRecvSize, int iTotalSize);

        /**
         * @brief Set the file transfer received a file data callback.
         * The callback will be notified if user received file transfer data.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize have recived size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFileRecvDone(int dwTrsfId, int iRecvSize, int iTotalSize);
        
        /**
         * @brief Set the file transfer invite accepted callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileTrsf or @ref MtcImFile::Mtc_ImFileTrsfU results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFileAcpted(int dwTrsfId);

        /**
         * @brief Set the file transfer invite rejected callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileTrsf or @ref MtcImFile::Mtc_ImFileTrsfU results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFileRejected(int dwTrsfId, int dwStatCode);

        /**
         * @brief Set the file transfer session canceled callback.
         *
         * @param [in] dwTrsfId file transfer Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFileCanceled(int dwTrsfId, int dwStatCode);

        /**
         * @brief Set the file transfer session released callback.
         *
         * @param [in] dwTrsfId file transfer Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFileReleased(int dwTrsfId, int dwStatCode);

        /**
         * @brief Set the file transfer sending callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileTrsf results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFileSending(int dwTrsfId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the file transfer send last file successfully callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileTrsf results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFileSendLast(int dwTrsfId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the file transfer send one file successfully callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileTrsf results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFileSendOk(int dwTrsfId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the file transfer send one file failed callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileTrsf results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFileSendFailed(int dwTrsfId, int dwStatCode);

        /**
         * @brief Set the file resume received a new invitation from file sender callback.
         * The callback will be notified if user received file resume invitation.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFResumeRecvIvtFromSender(int dwTrsfId);

        /**
         * @brief Set the file resume received a new invitation from file receiver callback.
         * The callback will be notified if user received file resume invitation.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFResumeRecvIvtFromRecver(int dwTrsfId);

        /**
         * @brief Set the file resume receiving a file data callback.
         * The callback will be notified if user receiving file resume data.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize Have recived size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFResumeRecving(int dwTrsfId, int iRecvSize, int iTotalSize);

        /**
         * @brief Set the file resume received a file data callback.
         * The callback will be notified if user received file resume data.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize Have recived size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFResumeRecvDone(int dwTrsfId, int iRecvSize, int iTotalSize);
        
        /**
         * @brief Set the file resume invite accepted callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileResume results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFResumeAcpted(int dwTrsfId);
        
        /**
         * @brief Set the file resume invite rejected callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileResume.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFResumeRejected(int dwTrsfId, int dwStatCode);
        
        /**
         * @brief Set the file resume session canceled callback.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFResumeCanceled(int dwTrsfId);
        
        /**
         * @brief Set the file resume session released callback.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFResumeReleased(int dwTrsfId, int dwStatCode);
        
        /**
         * @brief Set the file resume sending callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileResume results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFResumeSending(int dwTrsfId, int iSentSize, int iTotalSize);
        
        /**
         * @brief Set the file resume send last file successfully callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileResume results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFResumeSendLast(int dwTrsfId, int iSentSize, int iTotalSize);
        
        /**
         * @brief Set the file resume send one file successfully callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileResume results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFResumeSendOk(int dwTrsfId, int iSentSize, int iTotalSize);
        
        /**
         * @brief Set the file resume send one file failed callback.
         * The callback is one of @ref MtcImFile::Mtc_ImFileResume results.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFResumeSendFailed(int dwTrsfId, int dwStatCode);
        
        /**
         * @brief Set the file fetch receiving a file data callback.
         * The callback will be notified if user receiving file fetch data.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize Have received size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFFetchRecving(int dwTrsfId, int iRecvSize, int iTotalSize);
        
        /**
         * @brief Set the file fetch received a file data callback.
         * The callback will be notified if user received file fetch data.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize Have received size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFFetchRecvDone(int dwTrsfId, int iRecvSize, int iTotalSize);
        
        /**
         * @brief Set the file fetch invite accepted callback.
         * The callback @ref MtcImFile::Mtc_ImFileFetchViaMsrp .
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFFetchAcpted(int dwTrsfId);
        
        /**
         * @brief Set the file fetch invite rejected callback.
         * The callback @ref MtcImFile::Mtc_ImFileFetchViaMsrp.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFFetchRejected(int dwTrsfId, int dwStatCode);
        
        /**
         * @brief Set the file fetch session canceled callback.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFFetchCanceled(int dwTrsfId);
        
        /**
         * @brief Set the file fetch session released callback.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFFetchReleased(int dwTrsfId, int dwStatCode);
        
        /**
         * @brief Set the file store and forward received a new invitation callback.
         * The callback will be notified if user received file store and forward invitation.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFStfwdRecvIvt(int dwTrsfId);

        /**
         * @brief Set the file store and forward receiving a file data callback.
         * The callback will be notified if user receiving file store and forward data.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize Have received size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFStfwdRecving(int dwTrsfId, int iRecvSize, int iTotalSize);
        
        /**
         * @brief Set the file store and forward received a file data callback.
         * The callback will be notified if user received file store and forward data.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize Have received size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFStfwdRecvDone(int dwTrsfId, int iRecvSize, int iTotalSize);
        
        /**
         * @brief Set the file store and forward session canceled callback.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFStfwdCanceled(int dwTrsfId, int dwStatCode);

        /**
         * @brief Set the file store and forward session released callback.
         *
         * @param [in] dwTrsfId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFStfwdReleased(int dwTrsfId, int dwStatCode);

        /**
         * @brief Set the file transfer via http received a new invitation callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFtHttpRecvIvt(int dwFtHttpId);

        /**
         * @brief Set the file transfer via http receiving a file data callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize Have recived size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFtHttpRecving(int dwFtHttpId, int iRecvSize, int iTotalSize);

        /**
         * @brief Set the file transfer via http received a file data callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iRecvSize Have recived size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFtHttpRecvDone(int dwFtHttpId, int iRecvSize, int iTotalSize);

        /**
         * @brief Set the file transfer via http receive a file failed callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFtHttpRecvFailed(int dwFtHttpId, int dwStatCode);

        /**
         * @brief Set the file transfer via http released callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFtHttpReleased(int dwFtHttpId, int dwStatCode);

        /**
         * @brief Set the file transfer via http sending callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize have sent size size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFtHttpSending(int dwFtHttpId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the file transfer via http send last file successfully callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFtHttpSendLast(int dwFtHttpId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the file transfer via http send one file successfully callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbFtHttpSendOk(int dwFtHttpId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the file transfer via http send one file failed callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbFtHttpSendFailed(int dwFtHttpId, int dwStatCode);

        /**
         * @brief Set the file transfer via http send a file message successfully callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFtHttpMsgSendOk(int dwFtHttpId);

        /**
         * @brief Set the file transfer via http send a file message failed callback.
         *
         * @param [in] dwFtHttpId file transfer Id. use like MtcImFile::Mtc_ImFileGetName.
         */
        void mtcImCbFtHttpMsgSendFailed(int dwFtHttpId);
        

        /**
         * @brief Set the image share received a new invitation callback.
         * The callback will be notified if user received image share invitation.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         */
        void mtcImCbIShareRecvIvt(int dwShareId);

        /**
         * @brief Set the image share receiving a image data callback.
         * The callback will be notified if user received image share data.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         * @param [in] iRecvSize Have recived size.
         * @param [in] iTotalSize File total size..
         */
        void mtcImCbIShareRecving(int dwShareId, int iRecvSize, int iTotalSize);

        /**
         * @brief Set the image share received a image data callback.
         * The callback will be notified if user received image share data.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         * @param [in] iRecvSize Have recived size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbIShareRecvDone(int dwShareId, int iRecvSize, int iTotalSize);

        /**
         * @brief Set the image share invite accepted callback.
         * The callback is one of @ref MtcImIshare::Mtc_ImIShareSend results.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         */
        void mtcImCbIShareAcpted(int dwShareId);

        /**
         * @brief Set the image share invite rejected callback.
         * The callback is one of @ref MtcImIshare::Mtc_ImIShareSend results.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         */
        void mtcImCbIShareRejected(int dwShareId);

        /**
         * @brief Set the image share session canceled callback.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         */
        void mtcImCbIShareCanceled(int dwShareId);

        /**
         * @brief Set the image share session released callback.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbIShareReleased(int dwShareId, int dwStatCode);


        /**
         * @brief Set the image share sending callback.
         * The callback is one of @ref MtcImIshare::Mtc_ImIShareSend results.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbIShareSending(int dwShareId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the image share send last callback.
         * The callback is one of @ref MtcImIshare::Mtc_ImIShareSend results.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbIShareSendLast(int dwShareId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the image share send one image successfully callback.
         * The callback is one of @ref MtcImIshare::Mtc_ImIShareSend results.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         * @param [in] iSentSize Have sent size.
         * @param [in] iTotalSize File total size.
         */
        void mtcImCbIShareSendOk(int dwShareId, int iSentSize, int iTotalSize);

        /**
         * @brief Set the image share send one image failed callback.
         * The callback is one of @ref MtcImIshare::Mtc_ImIShareSend results.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbIShareSendFailed(int dwShareId, int dwStatCode);

        /**
         * @brief Set the deferred message retrieve metadata successfully callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_ImDeferRetrieveAll.
         *
         * @param [in] dwDeferId The message session id, use like MtcImDefer::Mtc_ImDeferMsgGetPartp.
         */
        void mtcImCbDeferRetrieveOk(int dwDeferId);

        /**
         * @brief Set the deferred message retrieve metadata failed callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_ImDeferRetrieveAll.
         *
         * @param [in] dwDeferId The message session id, use like MtcImDefer::Mtc_ImDeferMsgGetPartp.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbDeferRetrieveFailed(int dwDeferId, int dwStatCode);

        /**
         * @brief Set the deferred page message retrieve metadata successfully callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_ImDeferRetrievePager.
         *
         * @param [in] dwDeferId The message session id, use like MtcImDefer::Mtc_ImDeferMsgGetPartp.
         */
        void mtcImCbDeferRetrievePagerOk(int dwDeferId);

        /**
         * @brief Set the deferred page message retrieve metadata failed callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_ImDeferRetrievePager.
         *
         * @param [in] dwDeferId The message session id, use like MtcImDefer::Mtc_ImDeferMsgGetPartp.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbDeferRetrievePagerFailed(int dwDeferId, int dwStatCode);

        /**
         * @brief Set the deferred file retrieve metadata successfully callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_ImDeferRetrieveFile.
         *
         * @param [in] dwDeferId deferred id.
         */
        void mtcImCbDeferRetrieveFileOk(int dwDeferId);

        /**
         * @brief Set the deferred file retrieve metadata failed callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_ImDeferRetrieveFile.
         *
         * @param [in] dwDeferId The message session id, use like MtcImDefer::Mtc_ImDeferMsgGetPartp.
         * @param [in] dwStatCode status code.
         */
        void mtcImCbDeferRetrieveFileFailed(int dwDeferId, int dwStatCode);

        /**
         * @brief Set the deferred-list load successfully callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_DmsgLoadAll.
         *
         */
        void mtcImCbDmsgLoadOk();

        /**
         * @brief Set the deferred-list load failed callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_DmsgLoadAll.
         *
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbDmsgLoadFailed(int dwStatCode);

        /**
         * @brief Set the deferred-list remove successfully callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_DmsgRmvAll.
         *
         */
        void mtcImCbDmsgRmvOk();

        /**
         * @brief Set the deferred-list remove failed callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_DmsgRmvAll.
         *
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbDmsgRmvFailed(int dwStatCode);

        /**
         * @brief Set the deferred-list remove a history successfully callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_DmsgRmvHis.
         *
         * @param [in] dwHisId history Id.
         */
        void mtcImCbDmsgRmvHisOk(int dwHisId);

        /**
         * @brief Set the deferred-list remove a history failed callback.
         * The callback will be notified if user call @ref MtcImDefer::Mtc_DmsgRmvHis.
         *
         * @param [in] dwHisId History Id.
         * @param [in] dwStatCode Status code.
         */
        void mtcImCbDmsgRmvHisFailed(int dwHisId, int dwStatCode);

        /**
         * @brief Set the store and forward session invite accepted callback.
         * The callback is one of @ref MtcImStfwd::Mtc_ImStFwdAccept results.
         *
         * @param [in] dwSessionId Session Id.
         */
        void mtcImCbStFwdAcpted(int dwSessionId);

        /**
         * @brief Set the image share session exited callback.
         *
         * @param [in] dwShareId The image share id, use like MtcImIshare::Mtc_ImIShareGetName.
         */
        void mtcImCbIShareExited(int dwShareId);

        /**
         * @brief Set the login OK callback.
         * The callback will be notified after @ref Mtc_ImMsLogin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsLoginOk(int dwSessId);

        /**
         * @brief Set the login failed callback.
         * The callback will be notified after @ref Mtc_ImMsLogin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsLoginFailed(int dwSessId);

        /**
         * @brief Set the backup directory create OK callback.
         * The callback will be notified after @ref Mtc_ImMsBackupBegin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsCreateOk(int dwSessId);

        /**
         * @brief Set the backup directory create failed callback.
         * The callback will be notified after @ref Mtc_ImMsBackupBegin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsCreateFailed(int dwSessId);

        /**
         * @brief Set the select backup directory OK callback.
         * The callback will be notified after @ref Mtc_ImMsBackupBegin.
         *
         * After this callback, UI can append message by Mtc_ImMsBackupAppend.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsSelectOk(int dwSessId);

        /**
         * @brief Set the select backup directory OK callback.
         * The callback will be notified after @ref Mtc_ImMsBackupBegin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsSelectFailed(int dwSessId);

        /**
         * @brief Set the append one message object OK callback.
         * The callback will be notified after @ref Mtc_ImMsBackupAppend.
         * 
         * @param [in] dwSessId The message storage session ID.
         * @param [in] dwObjId Message object ID, create by @ref Mtc_ImMsObjCreate.
         */
        void mtcImCbMsAppendOk(int dwSessId, int dwObjId);

        /**
         * @brief Set the append one message object failed callback.
         * The callback will be notified after @ref Mtc_ImMsBackupAppend.
         * 
         * @param [in] dwSessId The message storage session ID.
         * @param [in] dwObjId Message object ID, create by @ref Mtc_ImMsObjCreate.
         */
        void mtcImCbMsAppendFailed(int dwSessId, int dwObjId);

        /**
         * @brief Set the list restore directory OK callback.
         * The callback will be notified after @ref Mtc_ImMsRestoreBegin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsListOk(int dwSessId);

        /**
         * @brief Set the list restore directory failed callback.
         * The callback will be notified after @ref Mtc_ImMsRestoreBegin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsListFailed(int dwSessId);

        /**
         * @brief Set the fetch message object OK callback.
         * The callback will be notified after @ref Mtc_ImMsRestoreBegin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsFetchOk(int dwSessId);

        /**
         * @brief Set the fetch message failed callback.
         * The callback will be notified after @ref Mtc_ImMsRestoreBegin.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsFetchFailed(int dwSessId);

        /**
         * @brief Set the close directory OK callback.
         * The callback will be notified after @ref Mtc_ImMsBackupEnd
         * @ref Mtc_ImMsRestoreEnd.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsCloseOk(int dwSessId);

        /**
         * @brief Set the close directory failed callback.
         * The callback will be notified after @ref Mtc_ImMsBackupEnd
         * @ref Mtc_ImMsRestoreEnd.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsCloseFailed(int dwSessId);

        /**
         * @brief Set the logout OK callback.
         * The callback will be notified after @ref Mtc_ImMsLogout.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsLogoutOk(int dwSessId);

        /**
         * @brief Set the logout failed callback.
         * The callback will be notified after @ref Mtc_ImMsLogout.
         * 
         * @param [in] dwSessId The message storage session ID.
         */
        void mtcImCbMsLogoutFailed(int dwSessId);

        /**
         * @brief Set the receive one message object OK callback.
         * The callback will be notified after @ref Mtc_ImMsRestoreBegin.
         * 
         * @param [in] dwSessId The message storage session ID.
         * @param [in] dwObjId The message storage object ID.
         */
        void mtcImCbMsObjectReceived(int dwSessId, int dwObjId);
        
        /**
         * @brief Set the subscribe conference list ok callback.
         * The callback will be notified if user call @ref MtcImConf::Mtc_ImConfMSubsConfLst.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcImCbConfSubsLstOk();
        
        /**
         * @brief Set the subscribe conference list failed callback.
         * The callback will be notified if user call @ref MtcImConf::Mtc_ImConfMSubsConfLst.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcImCbConfSubsLstFailed(int dwStatCode);

        /**
         * @brief Set the subscribe conference info ok callback.
         * The callback will be notified if user call @ref MtcImConf::Mtc_ImConfMSubsConf.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcImCbConfSubsInfoOk(int dwConfId);
        
        /**
         * @brief Set the subscribe conference info failed callback.
         * The callback will be notified if user call @ref MtcImConf::Mtc_ImConfMSubsConf.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcImCbConfSubsInfoFailed(int dwConfId, int dwStatCode);
        
        /**
         * @brief Set the subscribe conference info failed callback.
         * The callback will be notified if conference info is updated.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcImCbConfInfoUpdated(int dwConfId);
        
        /**
         * @brief Set offline message start callback.
         * The callback will be notified if offline message is start.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcImCbOfflineMsgStart(int iSessId);
        
        /**
         * @brief Set offline message stop callback.
         * The callback will be notified if offline message is stop.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcImCbOfflineMsgStop(int iSessId, int dwStatCode);

		/**
         * @brief Set offline message list recv callback.
         * The callback will be notified if offline message list recv.
         *
         * @param [in] pfnCb Callback function.
         */
        void mtcImCbOfflineMsgLstRecv(int iMsgLstId);

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

    private static final int CALLBACK_IM_PMSG_RECV_MSG = 0;
    private static final int CALLBACK_IM_PMSG_RECV_SMS_INFO = CALLBACK_IM_PMSG_RECV_MSG + 1;
    private static final int CALLBACK_IM_PMSG_SEND_OK = CALLBACK_IM_PMSG_RECV_SMS_INFO + 1;
    private static final int CALLBACK_IM_PMSG_SEND_FAILED = CALLBACK_IM_PMSG_SEND_OK + 1;

    private static final int CALLBACK_IM_LMSG_RECV_MSG = CALLBACK_IM_PMSG_SEND_FAILED + 1;
    private static final int CALLBACK_IM_LMSG_SEND_OK = CALLBACK_IM_LMSG_RECV_MSG + 1;
    private static final int CALLBACK_IM_LMSG_SEND_FAILED = CALLBACK_IM_LMSG_SEND_OK + 1;

    private static final int CALLBACK_IM_SESS_RECV_IVT = CALLBACK_IM_LMSG_SEND_FAILED + 1;
    private static final int CALLBACK_IM_SESS_RECV_IVTM = CALLBACK_IM_SESS_RECV_IVT + 1;
    private static final int CALLBACK_IM_SESS_REPLACE = CALLBACK_IM_SESS_RECV_IVTM + 1;
    private static final int CALLBACK_IM_SESS_REPLACEM = CALLBACK_IM_SESS_REPLACE + 1;

	private static final int CALLBACK_IM_SESS_SIGNAL_ACPTED = CALLBACK_IM_SESS_REPLACEM + 1;
    private static final int CALLBACK_IM_SESS_ACPTED = CALLBACK_IM_SESS_SIGNAL_ACPTED + 1;
    private static final int CALLBACK_IM_SESS_REJECTED = CALLBACK_IM_SESS_ACPTED + 1;
    private static final int CALLBACK_IM_SESS_CANCELED = CALLBACK_IM_SESS_REJECTED + 1;
    private static final int CALLBACK_IM_SESS_RELEASED = CALLBACK_IM_SESS_CANCELED + 1;
    private static final int CALLBACK_IM_SESS_LEAVE_OK = CALLBACK_IM_SESS_RELEASED + 1;
    private static final int CALLBACK_IM_SESS_LEAVE_FAILED = CALLBACK_IM_SESS_LEAVE_OK + 1;
    private static final int CALLBACK_IM_SESS_DISSOLVE_OK = CALLBACK_IM_SESS_LEAVE_FAILED + 1;
    private static final int CALLBACK_IM_SESS_DISSOLVE_FAILED = CALLBACK_IM_SESS_DISSOLVE_OK + 1;
    private static final int CALLBACK_IM_SESS_COMPOSING = CALLBACK_IM_SESS_DISSOLVE_FAILED + 1;
    private static final int CALLBACK_IM_SESS_PARTP_ADD_OK = CALLBACK_IM_SESS_COMPOSING + 1;
    private static final int CALLBACK_IM_SESS_PARTP_ADD_FAILED = CALLBACK_IM_SESS_PARTP_ADD_OK + 1;
    private static final int CALLBACK_IM_SESS_PARTP_EPL_OK = CALLBACK_IM_SESS_PARTP_ADD_FAILED + 1;
    private static final int CALLBACK_IM_SESS_PARTP_EPL_FAILED = CALLBACK_IM_SESS_PARTP_EPL_OK + 1;
    private static final int CALLBACK_IM_SESS_PARTP_UPTED = CALLBACK_IM_SESS_PARTP_EPL_FAILED + 1;
    private static final int CALLBACK_IM_SESS_SUBJECT_MDFY_OK = CALLBACK_IM_SESS_PARTP_UPTED + 1;
    private static final int CALLBACK_IM_SESS_SUBJECT_MDFY_FAILED = CALLBACK_IM_SESS_SUBJECT_MDFY_OK + 1;
    private static final int CALLBACK_IM_SESS_CHAIRMAN_MDFY_OK = CALLBACK_IM_SESS_SUBJECT_MDFY_FAILED + 1;
    private static final int CALLBACK_IM_SESS_CHAIRMAN_MDFY_FAILED = CALLBACK_IM_SESS_CHAIRMAN_MDFY_OK + 1;
    private static final int CALLBACK_IM_SESS_CHAIRMAN_MDFY_REQ = CALLBACK_IM_SESS_CHAIRMAN_MDFY_FAILED + 1;
    private static final int CALLBACK_IM_SESS_DISPNAME_MDFY_OK = CALLBACK_IM_SESS_CHAIRMAN_MDFY_REQ + 1;
    private static final int CALLBACK_IM_SESS_DISPNAME_MDFY_FAILED = CALLBACK_IM_SESS_DISPNAME_MDFY_OK + 1;

    private static final int CALLBACK_IM_SESS_MSG_RECV_MSG = CALLBACK_IM_SESS_DISPNAME_MDFY_FAILED + 1;
    private static final int CALLBACK_IM_SESS_MSG_SEND_OK = CALLBACK_IM_SESS_MSG_RECV_MSG + 1;
    private static final int CALLBACK_IM_SESS_MSG_SEND_FAILED = CALLBACK_IM_SESS_MSG_SEND_OK + 1;

    private static final int CALLBACK_IM_ST_FWD_RECV_IVT = CALLBACK_IM_SESS_MSG_SEND_FAILED + 1;
    private static final int CALLBACK_IM_ST_FWD_RECV_IVTM = CALLBACK_IM_ST_FWD_RECV_IVT + 1;
    private static final int CALLBACK_IM_ST_FWD_REPLACE = CALLBACK_IM_ST_FWD_RECV_IVTM + 1;
    private static final int CALLBACK_IM_ST_FWD_REPLACEM = CALLBACK_IM_ST_FWD_REPLACE + 1;
    private static final int CALLBACK_IM_ST_FWD_CANCELED = CALLBACK_IM_ST_FWD_REPLACEM + 1;
    private static final int CALLBACK_IM_ST_FWD_RELEASED = CALLBACK_IM_ST_FWD_CANCELED + 1;
    private static final int CALLBACK_IM_ST_FWD_MSG_RECV_MSG = CALLBACK_IM_ST_FWD_RELEASED + 1;

    private static final int CALLBACK_IMDN_RECV_DELI_NTFY = CALLBACK_IM_ST_FWD_MSG_RECV_MSG + 1;
    private static final int CALLBACK_IMDN_RECV_DELI_FAILED = CALLBACK_IMDN_RECV_DELI_NTFY + 1;
	private static final int CALLBACK_IMDN_RECV_DELI_FORWARDED = CALLBACK_IMDN_RECV_DELI_FAILED + 1;
    private static final int CALLBACK_IMDN_RECV_DISP_NTFY = CALLBACK_IMDN_RECV_DELI_FORWARDED + 1;
    private static final int CALLBACK_IMDN_RECV_BURN_NTFY = CALLBACK_IMDN_RECV_DISP_NTFY + 1;
	private static final int CALLBACK_IMDN_SEND_DELI_OK = CALLBACK_IMDN_RECV_BURN_NTFY + 1;
	private static final int CALLBACK_IMDN_SEND_DELI_FAILED = CALLBACK_IMDN_SEND_DELI_OK + 1;
	private static final int CALLBACK_IMDN_SEND_DISP_OK = CALLBACK_IMDN_SEND_DELI_FAILED + 1;
	private static final int CALLBACK_IMDN_SEND_DISP_FAILED = CALLBACK_IMDN_SEND_DISP_OK + 1;

    private static final int CALLBACK_IM_FILE_RECV_IVT = CALLBACK_IMDN_SEND_DISP_FAILED + 1;
    private static final int CALLBACK_IM_FILE_RECVING = CALLBACK_IM_FILE_RECV_IVT + 1;
    private static final int CALLBACK_IM_FILE_RECV_DONE = CALLBACK_IM_FILE_RECVING + 1;
    private static final int CALLBACK_IM_FILE_ACPTED = CALLBACK_IM_FILE_RECV_DONE + 1;
    private static final int CALLBACK_IM_FILE_REJECTED = CALLBACK_IM_FILE_ACPTED + 1;
    private static final int CALLBACK_IM_FILE_CANCELED = CALLBACK_IM_FILE_REJECTED + 1;
    private static final int CALLBACK_IM_FILE_RELEASED = CALLBACK_IM_FILE_CANCELED + 1;
    private static final int CALLBACK_IM_FILE_SENDING = CALLBACK_IM_FILE_RELEASED + 1;
    private static final int CALLBACK_IM_FILE_SEND_LAST = CALLBACK_IM_FILE_SENDING + 1;
    private static final int CALLBACK_IM_FILE_SEND_OK = CALLBACK_IM_FILE_SEND_LAST + 1;
    private static final int CALLBACK_IM_FILE_SEND_FAILED = CALLBACK_IM_FILE_SEND_OK + 1;
    
    private static final int CALLBACK_IM_FRESUME_RECV_IVT_FROM_SENDER = CALLBACK_IM_FILE_SEND_FAILED + 1;
    private static final int CALLBACK_IM_FRESUME_RECV_IVT_FROM_RECVER = CALLBACK_IM_FRESUME_RECV_IVT_FROM_SENDER + 1;
    private static final int CALLBACK_IM_FRESUME_RECVING = CALLBACK_IM_FRESUME_RECV_IVT_FROM_RECVER + 1;
    private static final int CALLBACK_IM_FRESUME_RECV_DONE = CALLBACK_IM_FRESUME_RECVING + 1;
    private static final int CALLBACK_IM_FRESUME_ACPTED = CALLBACK_IM_FRESUME_RECV_DONE + 1;
    private static final int CALLBACK_IM_FRESUME_REJECTED = CALLBACK_IM_FRESUME_ACPTED + 1;
    private static final int CALLBACK_IM_FRESUME_CANCELED = CALLBACK_IM_FRESUME_REJECTED + 1;
    private static final int CALLBACK_IM_FRESUME_RELEASED = CALLBACK_IM_FRESUME_CANCELED + 1;
    private static final int CALLBACK_IM_FRESUME_SENDING = CALLBACK_IM_FRESUME_RELEASED + 1;
    private static final int CALLBACK_IM_FRESUME_SEND_LAST = CALLBACK_IM_FRESUME_SENDING + 1;
    private static final int CALLBACK_IM_FRESUME_SEND_OK = CALLBACK_IM_FRESUME_SEND_LAST + 1;
    private static final int CALLBACK_IM_FRESUME_SEND_FAILED = CALLBACK_IM_FRESUME_SEND_OK + 1;
    private static final int CALLBACK_IM_FFETCH_RECVING = CALLBACK_IM_FRESUME_SEND_FAILED + 1;
    private static final int CALLBACK_IM_FFETCH_RECV_DONE = CALLBACK_IM_FFETCH_RECVING + 1;
    private static final int CALLBACK_IM_FFETCH_ACPTED = CALLBACK_IM_FFETCH_RECV_DONE + 1;
    private static final int CALLBACK_IM_FFETCH_REJECTED = CALLBACK_IM_FFETCH_ACPTED + 1;
    private static final int CALLBACK_IM_FFETCH_CANCELED = CALLBACK_IM_FFETCH_REJECTED + 1;
    private static final int CALLBACK_IM_FFETCH_RELEASED = CALLBACK_IM_FFETCH_CANCELED + 1;

    private static final int CALLBACK_IM_FSTFWD_RECV_IVT = CALLBACK_IM_FFETCH_RELEASED + 1;
    private static final int CALLBACK_IM_FSTFWD_RECVING = CALLBACK_IM_FSTFWD_RECV_IVT + 1;
    private static final int CALLBACK_IM_FSTFWD_RECV_DONE = CALLBACK_IM_FSTFWD_RECVING + 1;
    private static final int CALLBACK_IM_FSTFWD_CANCELED = CALLBACK_IM_FSTFWD_RECV_DONE + 1;
    private static final int CALLBACK_IM_FSTFWD_RELEASED = CALLBACK_IM_FSTFWD_CANCELED + 1;
    
    private static final int CALLBACK_IM_FTHTTP_RECVIVT = CALLBACK_IM_FSTFWD_RELEASED + 1;
    private static final int CALLBACK_IM_FTHTTP_RECVING = CALLBACK_IM_FTHTTP_RECVIVT + 1;
    private static final int CALLBACK_IM_FTHTTP_RECV_DONE = CALLBACK_IM_FTHTTP_RECVING + 1;
    private static final int CALLBACK_IM_FTHTTP_RECV_FAILED = CALLBACK_IM_FTHTTP_RECV_DONE + 1;
    private static final int CALLBACK_IM_FTHTTP_RELEASE = CALLBACK_IM_FTHTTP_RECV_FAILED + 1;
    private static final int CALLBACK_IM_FTHTTP_SENDING = CALLBACK_IM_FTHTTP_RELEASE + 1;
    private static final int CALLBACK_IM_FTHTTP_SEND_LAST = CALLBACK_IM_FTHTTP_SENDING + 1;
    private static final int CALLBACK_IM_FTHTTP_SEND_OK = CALLBACK_IM_FTHTTP_SEND_LAST + 1;
    private static final int CALLBACK_IM_FTHTTP_SEND_FAILED = CALLBACK_IM_FTHTTP_SEND_OK + 1; 
    private static final int CALLBACK_IM_FTHTTP_MSG_SEND_OK = CALLBACK_IM_FTHTTP_SEND_FAILED + 1;
    private static final int CALLBACK_IM_FTHTTP_MSG_SEND_FAILED = CALLBACK_IM_FTHTTP_MSG_SEND_OK + 1; 

    private static final int CALLBACK_IM_ISHARE_RECV_IVT = CALLBACK_IM_FTHTTP_MSG_SEND_FAILED + 1;
    private static final int CALLBACK_IM_ISHARE_RECVING = CALLBACK_IM_ISHARE_RECV_IVT + 1;
    private static final int CALLBACK_IM_ISHARE_RECV_DONE = CALLBACK_IM_ISHARE_RECVING + 1;
    private static final int CALLBACK_IM_ISHARE_ACPTED = CALLBACK_IM_ISHARE_RECV_DONE + 1;
    private static final int CALLBACK_IM_ISHARE_REJECTED = CALLBACK_IM_ISHARE_ACPTED + 1;
    private static final int CALLBACK_IM_ISHARE_CANCELED = CALLBACK_IM_ISHARE_REJECTED + 1;
    private static final int CALLBACK_IM_ISHARE_RELEASED = CALLBACK_IM_ISHARE_CANCELED + 1;
    private static final int CALLBACK_IM_ISHARE_SENDING = CALLBACK_IM_ISHARE_RELEASED + 1;
    private static final int CALLBACK_IM_ISHARE_SEND_LAST = CALLBACK_IM_ISHARE_SENDING + 1;
    private static final int CALLBACK_IM_ISHARE_SEND_OK = CALLBACK_IM_ISHARE_SEND_LAST + 1;
    private static final int CALLBACK_IM_ISHARE_SEND_FAILED = CALLBACK_IM_ISHARE_SEND_OK + 1;

    private static final int CALLBACK_IM_DEFER_RETRIEVE_OK = CALLBACK_IM_ISHARE_SEND_FAILED + 1;
    private static final int CALLBACK_IM_DEFER_RETRIEVE_FAILED = CALLBACK_IM_DEFER_RETRIEVE_OK + 1;
    private static final int CALLBACK_IM_DEFER_RETRIEVE_PAGER_OK = CALLBACK_IM_DEFER_RETRIEVE_FAILED + 1;
    private static final int CALLBACK_IM_DEFER_RETRIEVE_PAGER_FAILED = CALLBACK_IM_DEFER_RETRIEVE_PAGER_OK + 1;
    private static final int CALLBACK_IM_DEFER_RETRIEVE_FILE_OK = CALLBACK_IM_DEFER_RETRIEVE_PAGER_FAILED + 1;
    private static final int CALLBACK_IM_DEFER_RETRIEVE_FILE_FAILED = CALLBACK_IM_DEFER_RETRIEVE_FILE_OK + 1;

    private static final int CALLBACK_IM_DMSG_LOAD_OK = CALLBACK_IM_DEFER_RETRIEVE_FILE_FAILED + 1;
    private static final int CALLBACK_IM_DMSG_LOAD_FAILED = CALLBACK_IM_DMSG_LOAD_OK + 1;
    private static final int CALLBACK_IM_DMSG_RMV_OK = CALLBACK_IM_DMSG_LOAD_FAILED + 1;
    private static final int CALLBACK_IM_DMSG_RMV_FAILED = CALLBACK_IM_DMSG_RMV_OK + 1;
    private static final int CALLBACK_IM_DMSG_RMV_HIS_OK = CALLBACK_IM_DMSG_RMV_FAILED + 1;
    private static final int CALLBACK_IM_DMSG_RMV_HIS_FAILED = CALLBACK_IM_DMSG_RMV_HIS_OK + 1;

    private static final int CALLBACK_IM_ISHARE_EXIT = CALLBACK_IM_DMSG_RMV_HIS_FAILED + 1;
    private static final int CALLBACK_STFWD_ACPTED = CALLBACK_IM_ISHARE_EXIT + 1;
    
    private static final int CALLBACK_IM_MS_LOGIN_OK = CALLBACK_STFWD_ACPTED + 1;
    private static final int CALLBACK_IM_MS_LOGIN_FAILED = CALLBACK_IM_MS_LOGIN_OK + 1;
    private static final int CALLBACK_IM_MS_CREATE_OK = CALLBACK_IM_MS_LOGIN_FAILED + 1;
    private static final int CALLBACK_IM_MS_CREATE_FAILED = CALLBACK_IM_MS_CREATE_OK + 1;
    private static final int CALLBACK_IM_MS_SELECT_OK = CALLBACK_IM_MS_CREATE_FAILED + 1;
    private static final int CALLBACK_IM_MS_SELECT_FAILED = CALLBACK_IM_MS_SELECT_OK + 1;
    private static final int CALLBACK_IM_MS_APPEND_OK = CALLBACK_IM_MS_SELECT_FAILED + 1;
    private static final int CALLBACK_IM_MS_APPEND_FAILED = CALLBACK_IM_MS_APPEND_OK + 1;
    private static final int CALLBACK_IM_MS_LIST_OK = CALLBACK_IM_MS_APPEND_FAILED + 1;
    private static final int CALLBACK_IM_MS_LIST_FAILED = CALLBACK_IM_MS_LIST_OK + 1;
    private static final int CALLBACK_IM_MS_FETCH_OK = CALLBACK_IM_MS_LIST_FAILED + 1;
    private static final int CALLBACK_IM_MS_FETCH_FAILED = CALLBACK_IM_MS_FETCH_OK + 1;
    private static final int CALLBACK_IM_MS_CLOSE_OK = CALLBACK_IM_MS_FETCH_FAILED + 1;
    private static final int CALLBACK_IM_MS_CLOSE_FAILED = CALLBACK_IM_MS_CLOSE_OK + 1;
    private static final int CALLBACK_IM_MS_LOGOUT_OK = CALLBACK_IM_MS_CLOSE_FAILED + 1;
    private static final int CALLBACK_IM_MS_LOGOUT_FAILED = CALLBACK_IM_MS_LOGOUT_OK + 1;
    private static final int CALLBACK_IM_MS_OBJECT_RECEIVED = CALLBACK_IM_MS_LOGOUT_FAILED + 1;
    
    private static final int CALLBACK_IM_CONF_SUBS_LST_OK = CALLBACK_IM_MS_OBJECT_RECEIVED + 1;
    private static final int CALLBACK_IM_CONF_SUBS_LST_FAILED = CALLBACK_IM_CONF_SUBS_LST_OK + 1;
    private static final int CALLBACK_IM_CONF_SUBS_INFO_OK = CALLBACK_IM_CONF_SUBS_LST_FAILED + 1;
    private static final int CALLBACK_IM_CONF_SUBS_INFO_FAILED = CALLBACK_IM_CONF_SUBS_INFO_OK + 1;
    private static final int CALLBACK_IM_CONF_INFO_UPDATED = CALLBACK_IM_CONF_SUBS_INFO_FAILED + 1;
    private static final int CALLBACK_IM_OFFLINE_SESS_START = CALLBACK_IM_CONF_INFO_UPDATED + 1;
    private static final int CALLBACK_IM_OFFLINE_SESS_END = CALLBACK_IM_OFFLINE_SESS_START + 1;
	private static final int CALLBACK_IM_OFFLINE_MSGLST_RECV = CALLBACK_IM_OFFLINE_SESS_END + 1;
    
    /**
     * @brief Distribute call callbacks
     *
     * Distribute call callbacks
     */
    private static void mtcImCbCallback(int function, int arg1, int arg2, int arg3, int arg4, int arg5, boolean bFullNtfy) {
        switch (function) {
            case CALLBACK_IM_PMSG_RECV_MSG:
                sCallback.mtcImCbPMsgRecvMsg(arg1);
                break;
            case CALLBACK_IM_PMSG_RECV_SMS_INFO:
                sCallback.mtcImCbPMsgRecvSmsInfo(arg1);
                break;
            case CALLBACK_IM_PMSG_SEND_OK:
                sCallback.mtcImCbPMsgSendOk(arg1);
                break;
            case CALLBACK_IM_PMSG_SEND_FAILED:
                sCallback.mtcImCbPMsgSendFailed(arg1, arg2);
                break;
            case CALLBACK_IM_LMSG_RECV_MSG:
                sCallback.mtcImCbLMsgRecvMsg(arg1);
                break;
            case CALLBACK_IM_LMSG_SEND_OK:
                sCallback.mtcImCbLMsgSendOk(arg1);
                break;
            case CALLBACK_IM_LMSG_SEND_FAILED:
                sCallback.mtcImCbLMsgSendFailed(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_RECV_IVT:
                sCallback.mtcImCbSessRecvIvt(arg1);
                break;
            case CALLBACK_IM_SESS_RECV_IVTM:
                sCallback.mtcImCbSessRecvIvtM(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_REPLACE:
                sCallback.mtcImCbSessReplace(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_REPLACEM:
                sCallback.mtcImCbSessReplaceM(arg1, arg2, arg3);
                break;
			case CALLBACK_IM_SESS_SIGNAL_ACPTED:
                sCallback.mtcImCbSessSignalAcpted(arg1);
                break;
            case CALLBACK_IM_SESS_ACPTED:
                sCallback.mtcImCbSessAcpted(arg1);
                break;
            case CALLBACK_IM_SESS_REJECTED:
                sCallback.mtcImCbSessRejected(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_CANCELED:
                sCallback.mtcImCbSessCanceled(arg1);
                break;
            case CALLBACK_IM_SESS_RELEASED:
                sCallback.mtcImCbSessReleased(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_LEAVE_OK:
                sCallback.mtcImCbSessLeaveOk(arg1);
                break;
            case CALLBACK_IM_SESS_LEAVE_FAILED:
                sCallback.mtcImCbSessLeaveFailed(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_DISSOLVE_OK:
                sCallback.mtcImCbSessDissolveOk(arg1);
                break;
            case CALLBACK_IM_SESS_DISSOLVE_FAILED:
                sCallback.mtcImCbSessDissolveFailed(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_COMPOSING:
                sCallback.mtcImCbSessComposing(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_PARTP_ADD_OK:
                sCallback.mtcImCbSessPartpAddOk(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_PARTP_ADD_FAILED:
                sCallback.mtcImCbSessPartpAddFailed(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_SESS_PARTP_EPL_OK:
                sCallback.mtcImCbSessPartpEplOk(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_PARTP_EPL_FAILED:
                sCallback.mtcImCbSessPartpEplFailed(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_SESS_PARTP_UPTED:
                sCallback.mtcImCbSessPartpUpted(arg1, arg2, bFullNtfy);
                break;
            case CALLBACK_IM_SESS_SUBJECT_MDFY_OK:
                sCallback.mtcImCbSessSubjectMdfyOk(arg1);
                break;
            case CALLBACK_IM_SESS_SUBJECT_MDFY_FAILED:
                sCallback.mtcImCbSessSubjectMdfyFailed(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_CHAIRMAN_MDFY_OK:
                sCallback.mtcImCbSessChairmanMdfyOk(arg1);
                break;
            case CALLBACK_IM_SESS_CHAIRMAN_MDFY_FAILED:
                sCallback.mtcImCbSessChairmanMdfyFailed(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_CHAIRMAN_MDFY_REQ:
                sCallback.mtcImCbSessChairmanMdfyReq(arg1);
                break;
            case CALLBACK_IM_SESS_DISPNAME_MDFY_OK:
                sCallback.mtcImCbSessDispNameMdfyOk(arg1);
                break;
            case CALLBACK_IM_SESS_DISPNAME_MDFY_FAILED:
                sCallback.mtcImCbSessDispNameMdfyFailed(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_MSG_RECV_MSG:
                sCallback.mtcImCbSessMsgRecvMsg(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_MSG_SEND_OK:
                sCallback.mtcImCbSessMsgSendOk(arg1, arg2);
                break;
            case CALLBACK_IM_SESS_MSG_SEND_FAILED:
                sCallback.mtcImCbSessMsgSendFailed(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_ST_FWD_RECV_IVT:
                sCallback.mtcImCbStFwdRecvIvt(arg1);
                break;
            case CALLBACK_IM_ST_FWD_RECV_IVTM:
                sCallback.mtcImCbStFwdRecvIvtM(arg1, arg2);
                break;
            case CALLBACK_IM_ST_FWD_REPLACE:
                sCallback.mtcImCbStFwdReplace(arg1, arg2);
                break;
            case CALLBACK_IM_ST_FWD_REPLACEM:
                sCallback.mtcImCbStFwdReplaceM(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_ST_FWD_CANCELED:
                sCallback.mtcImCbStFwdCanceled(arg1);
                break;
            case CALLBACK_IM_ST_FWD_RELEASED:
                sCallback.mtcImCbStFwdReleased(arg1, arg2);
                break;
            case CALLBACK_IM_ST_FWD_MSG_RECV_MSG:
                sCallback.mtcImCbStFwdMsgRecvMsg(arg1, arg2);
                break;
            case CALLBACK_IMDN_RECV_DELI_NTFY:
                sCallback.mtcImCbImdnRecvDeliNtfy(arg1);
                break;
            case CALLBACK_IMDN_RECV_DELI_FAILED:
                sCallback.mtcImCbImdnRecvDeliFailed(arg1, arg2);
                break;
			case CALLBACK_IMDN_RECV_DELI_FORWARDED:
                sCallback.mtcImCbImdnRecvDeliForwarded(arg1);
                break;
            case CALLBACK_IMDN_RECV_DISP_NTFY:
                sCallback.mtcImCbImdnRecvDispNtfy(arg1);
                break;
            case CALLBACK_IMDN_RECV_BURN_NTFY:
                sCallback.mtcImCbImdnRecvBurnNtfy(arg1);
                break;
			case CALLBACK_IMDN_SEND_DELI_OK:
                sCallback.mtcImCbImdnSendDeliOk(arg1);
                break;
			case CALLBACK_IMDN_SEND_DELI_FAILED:
                sCallback.mtcImCbImdnSendDeliFailed(arg1, arg2);
                break;
			case CALLBACK_IMDN_SEND_DISP_OK:
                sCallback.mtcImCbImdnSendDispOk(arg1);
                break;
			case CALLBACK_IMDN_SEND_DISP_FAILED:
                sCallback.mtcImCbImdnSendDispFailed(arg1, arg2);
                break;
            case CALLBACK_IM_FILE_RECV_IVT:
                sCallback.mtcImCbFileRecvIvt(arg1);
                break;
            case CALLBACK_IM_FILE_RECVING:
                sCallback.mtcImCbFileRecving(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FILE_RECV_DONE:
                sCallback.mtcImCbFileRecvDone(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FILE_ACPTED:
                sCallback.mtcImCbFileAcpted(arg1);
                break;
            case CALLBACK_IM_FILE_REJECTED:
                sCallback.mtcImCbFileRejected(arg1, arg2);
                break;
            case CALLBACK_IM_FILE_CANCELED:
                sCallback.mtcImCbFileCanceled(arg1, arg2);
                break;
            case CALLBACK_IM_FILE_RELEASED:
                sCallback.mtcImCbFileReleased(arg1, arg2);
                break;
            case CALLBACK_IM_FILE_SENDING:
                sCallback.mtcImCbFileSending(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FILE_SEND_LAST:
                sCallback.mtcImCbFileSendLast(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FILE_SEND_OK:
                sCallback.mtcImCbFileSendOk(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FILE_SEND_FAILED:
                sCallback.mtcImCbFileSendFailed(arg1, arg2);
                break;
            case CALLBACK_IM_FRESUME_RECV_IVT_FROM_SENDER:
              sCallback.mtcImCbFResumeRecvIvtFromSender(arg1);
              break;
            case CALLBACK_IM_FRESUME_RECV_IVT_FROM_RECVER:
              sCallback.mtcImCbFResumeRecvIvtFromRecver(arg1);
              break;
            case CALLBACK_IM_FRESUME_RECVING:
              sCallback.mtcImCbFResumeRecving(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FRESUME_RECV_DONE:
              sCallback.mtcImCbFResumeRecvDone(arg1, arg2, arg3);
              break;
            case CALLBACK_IM_FRESUME_ACPTED:
              sCallback.mtcImCbFResumeAcpted(arg1);
              break;
            case CALLBACK_IM_FRESUME_REJECTED:
              sCallback.mtcImCbFResumeRejected(arg1, arg2);
              break;
            case CALLBACK_IM_FRESUME_CANCELED:
              sCallback.mtcImCbFResumeCanceled(arg1);
              break;
            case CALLBACK_IM_FRESUME_RELEASED:
              sCallback.mtcImCbFResumeReleased(arg1, arg2);
              break;
            case CALLBACK_IM_FRESUME_SENDING:
              sCallback.mtcImCbFResumeSending(arg1, arg2, arg3);
              break;
            case CALLBACK_IM_FRESUME_SEND_LAST:
              sCallback.mtcImCbFResumeSendLast(arg1, arg2, arg3);
              break;
            case CALLBACK_IM_FRESUME_SEND_OK:
              sCallback.mtcImCbFResumeSendOk(arg1, arg2, arg3);
              break;
            case CALLBACK_IM_FRESUME_SEND_FAILED:
              sCallback.mtcImCbFResumeSendFailed(arg1, arg2);
              break;
            case CALLBACK_IM_FFETCH_RECVING:
              sCallback.mtcImCbFFetchRecving(arg1, arg2, arg3);
              break;
            case CALLBACK_IM_FFETCH_RECV_DONE:
              sCallback.mtcImCbFFetchRecvDone(arg1, arg2, arg3);
              break;
            case CALLBACK_IM_FFETCH_ACPTED:
              sCallback.mtcImCbFFetchAcpted(arg1);
              break;
            case CALLBACK_IM_FFETCH_REJECTED:
              sCallback.mtcImCbFFetchRejected(arg1, arg2);
              break;
            case CALLBACK_IM_FFETCH_CANCELED:
              sCallback.mtcImCbFFetchCanceled(arg1);
              break;
            case CALLBACK_IM_FFETCH_RELEASED:
              sCallback.mtcImCbFFetchReleased(arg1, arg2);
              break;
            case CALLBACK_IM_FSTFWD_RECV_IVT:
              sCallback.mtcImCbFStfwdRecvIvt(arg1);
              break;
            case CALLBACK_IM_FSTFWD_RECVING:
              sCallback.mtcImCbFStfwdRecving(arg1, arg2, arg3);
              break;
            case CALLBACK_IM_FSTFWD_RECV_DONE:
              sCallback.mtcImCbFStfwdRecvDone(arg1, arg2, arg3);
              break;
            case CALLBACK_IM_FSTFWD_CANCELED:
              sCallback.mtcImCbFStfwdCanceled(arg1, arg2);
              break;
            case CALLBACK_IM_FSTFWD_RELEASED:
              sCallback.mtcImCbFStfwdReleased(arg1, arg2);
              break;
              
            case CALLBACK_IM_FTHTTP_RECVIVT:
                sCallback.mtcImCbFtHttpRecvIvt(arg1);
                break;
            case CALLBACK_IM_FTHTTP_RECVING:
                sCallback.mtcImCbFtHttpRecving(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FTHTTP_RECV_DONE:
                sCallback.mtcImCbFtHttpRecvDone(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FTHTTP_RECV_FAILED:
                sCallback.mtcImCbFtHttpRecvFailed(arg1, arg1);
                break;
            case CALLBACK_IM_FTHTTP_RELEASE:
                sCallback.mtcImCbFtHttpReleased(arg1, arg2);
                break;
            case CALLBACK_IM_FTHTTP_SENDING:
                sCallback.mtcImCbFtHttpSending(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FTHTTP_SEND_LAST:
                sCallback.mtcImCbFtHttpSendLast(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FTHTTP_SEND_OK:
                sCallback.mtcImCbFtHttpSendOk(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_FTHTTP_SEND_FAILED:
                sCallback.mtcImCbFtHttpSendFailed(arg1, arg2);
                break;
            case CALLBACK_IM_FTHTTP_MSG_SEND_OK:
                sCallback.mtcImCbFtHttpMsgSendOk(arg1);
                break;
            case CALLBACK_IM_FTHTTP_MSG_SEND_FAILED:
                sCallback.mtcImCbFtHttpMsgSendFailed(arg1);
                break;
              
            case CALLBACK_IM_ISHARE_RECV_IVT:
                sCallback.mtcImCbIShareRecvIvt(arg1);
                break;
            case CALLBACK_IM_ISHARE_RECVING:
                sCallback.mtcImCbIShareRecving(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_ISHARE_RECV_DONE:
                sCallback.mtcImCbIShareRecvDone(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_ISHARE_ACPTED:
                sCallback.mtcImCbIShareAcpted(arg1);
                break;
            case CALLBACK_IM_ISHARE_REJECTED:
                sCallback.mtcImCbIShareRejected(arg1);
                break;
            case CALLBACK_IM_ISHARE_CANCELED:
                sCallback.mtcImCbIShareCanceled(arg1);
                break;
            case CALLBACK_IM_ISHARE_RELEASED:
                sCallback.mtcImCbIShareReleased(arg1, arg2);
                break;
            case CALLBACK_IM_ISHARE_SENDING:
                sCallback.mtcImCbIShareSending(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_ISHARE_SEND_LAST:
                sCallback.mtcImCbIShareSendLast(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_ISHARE_SEND_OK:
                sCallback.mtcImCbIShareSendOk(arg1, arg2, arg3);
                break;
            case CALLBACK_IM_ISHARE_SEND_FAILED:
                sCallback.mtcImCbIShareSendFailed(arg1, arg2);
                break;
            case CALLBACK_IM_DEFER_RETRIEVE_OK:
                sCallback.mtcImCbDeferRetrieveOk(arg1);
                break;
            case CALLBACK_IM_DEFER_RETRIEVE_FAILED:
                sCallback.mtcImCbDeferRetrieveFailed(arg1, arg2);
                break;
            case CALLBACK_IM_DEFER_RETRIEVE_PAGER_OK:
                sCallback.mtcImCbDeferRetrievePagerOk(arg1);
                break;
            case CALLBACK_IM_DEFER_RETRIEVE_PAGER_FAILED:
                sCallback.mtcImCbDeferRetrievePagerFailed(arg1, arg2);
                break;
            case CALLBACK_IM_DEFER_RETRIEVE_FILE_OK:
                sCallback.mtcImCbDeferRetrieveFileOk(arg1);
                break;
            case CALLBACK_IM_DEFER_RETRIEVE_FILE_FAILED:
                sCallback.mtcImCbDeferRetrieveFileFailed(arg1, arg2);
                break;
            case CALLBACK_IM_DMSG_LOAD_OK:
                sCallback.mtcImCbDmsgLoadOk();
                break;
            case CALLBACK_IM_DMSG_LOAD_FAILED:
                sCallback.mtcImCbDmsgLoadFailed(arg1);
                break;
            case CALLBACK_IM_DMSG_RMV_OK:
                sCallback.mtcImCbDmsgRmvOk();
                break;
            case CALLBACK_IM_DMSG_RMV_FAILED:
                sCallback.mtcImCbDmsgRmvFailed(arg1);
                break;
            case CALLBACK_IM_DMSG_RMV_HIS_OK:
                sCallback.mtcImCbDmsgRmvHisOk(arg1);
                break;
            case CALLBACK_IM_DMSG_RMV_HIS_FAILED:
                sCallback.mtcImCbDmsgRmvHisFailed(arg1, arg2);
                break;
            case CALLBACK_IM_ISHARE_EXIT:
               sCallback.mtcImCbIShareExited(arg1);
               break;
            case CALLBACK_STFWD_ACPTED:
               sCallback.mtcImCbStFwdAcpted(arg1);
               break;
            case CALLBACK_IM_MS_LOGIN_OK:
                sCallback.mtcImCbMsLoginOk(arg1);
                break;
            case CALLBACK_IM_MS_LOGIN_FAILED:
                sCallback.mtcImCbMsLoginFailed(arg1);
                break;
            case CALLBACK_IM_MS_CREATE_OK:
                sCallback.mtcImCbMsCreateOk(arg1);
                break;
            case CALLBACK_IM_MS_CREATE_FAILED:
                sCallback.mtcImCbMsCreateFailed(arg1);
                break;
            case CALLBACK_IM_MS_SELECT_OK:
                sCallback.mtcImCbMsSelectOk(arg1);
                break;
            case CALLBACK_IM_MS_SELECT_FAILED:
                sCallback.mtcImCbMsSelectFailed(arg1);
                break;
            case CALLBACK_IM_MS_APPEND_OK:
                sCallback.mtcImCbMsAppendOk(arg1, arg2);
                break;
            case CALLBACK_IM_MS_APPEND_FAILED:
                sCallback.mtcImCbMsAppendFailed(arg1, arg2);
                break;
            case CALLBACK_IM_MS_LIST_OK:
                sCallback.mtcImCbMsListOk(arg1);
                break;
            case CALLBACK_IM_MS_LIST_FAILED:
                sCallback.mtcImCbMsListFailed(arg1);
                break;
            case CALLBACK_IM_MS_FETCH_OK:
                sCallback.mtcImCbMsFetchOk(arg1);
                break;
            case CALLBACK_IM_MS_FETCH_FAILED:
                sCallback.mtcImCbMsFetchFailed(arg1);
                break;
            case CALLBACK_IM_MS_CLOSE_OK:
                sCallback.mtcImCbMsCloseOk(arg1);
                break;
            case CALLBACK_IM_MS_CLOSE_FAILED:
                sCallback.mtcImCbMsCloseFailed(arg1);
                break;
            case CALLBACK_IM_MS_LOGOUT_OK:
                sCallback.mtcImCbMsLogoutOk(arg1);
                break;
            case CALLBACK_IM_MS_LOGOUT_FAILED:
                sCallback.mtcImCbMsLogoutFailed(arg1);
                break;
            case CALLBACK_IM_MS_OBJECT_RECEIVED:
                sCallback.mtcImCbMsObjectReceived(arg1, arg2);
                break;
           case CALLBACK_IM_CONF_SUBS_LST_OK:
               sCallback.mtcImCbConfSubsLstOk();
               break;
           case CALLBACK_IM_CONF_SUBS_LST_FAILED:
               sCallback.mtcImCbConfSubsLstFailed(arg1);
               break;
           case CALLBACK_IM_CONF_SUBS_INFO_OK:
               sCallback.mtcImCbConfSubsInfoOk(arg1);
               break;
           case CALLBACK_IM_CONF_SUBS_INFO_FAILED:
               sCallback.mtcImCbConfSubsInfoFailed(arg1, arg2);
               break;
           case CALLBACK_IM_CONF_INFO_UPDATED:
               sCallback.mtcImCbConfInfoUpdated(arg1);
               break;
           
           case CALLBACK_IM_OFFLINE_SESS_START:
               sCallback.mtcImCbOfflineMsgStart(arg1);
               break;        
           case CALLBACK_IM_OFFLINE_SESS_END:
               sCallback.mtcImCbOfflineMsgStop(arg1, arg2);
               break;
		   case CALLBACK_IM_OFFLINE_MSGLST_RECV:
               sCallback.mtcImCbOfflineMsgLstRecv(arg1);
               break;
 
        }
    }
}
