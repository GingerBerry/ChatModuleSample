/**
 * @file MtcCprofCb.java
 * @brief MTC CMCC profile callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

/**
 * @brief Class of MTC CMCC profile callbacks
 */
public class MtcCprofCb {
    /**
     * @brief MTC CMCC profile callbacks
     *
     * In order to receive MTC CMCC profile callbacks, user should implement this 
     * interface, then use @ref MtcCprofCb.setCallback to register callbacks.
     */    
    public interface Callback {

        /**
         * @brief Set the CMCC profile load all pcc successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadAll results
         *
         */
        void mtcCprofCbPccAllLoadOk();

        /**
         * @brief Set the CMCC profile load all pcc failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadAll results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccAllLoadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile upload all pcc successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadAll results
         *
         */
        void mtcCprofCbPccAllUploadOk();

        /**
         * @brief Set the CMCC profile upload all pcc failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadAll results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccAllUploadFailed(int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load pcc name successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadName results
         *
         */
        void mtcCprofCbPccNameLoadOk();

        /**
         * @brief Set the CMCC profile load pcc name failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadName results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccNameLoadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile upload pcc name successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadName results
         *
         */
        void mtcCprofCbPccNameUploadOk();

        /**
         * @brief Set the CMCC profile upload pcc name failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadName results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccNameUploadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile load pcc address successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadAddr results
         *
         */
        void mtcCprofCbPccAddrLoadOk();

        /**
         * @brief Set the CMCC profile load pcc address failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadAddr results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccAddrLoadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile upload pcc address successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadAddr results
         *
         */
        void mtcCprofCbPccAddrUploadOk();

        /**
         * @brief Set the CMCC profile upload pcc address failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadAddr results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccAddrUploadFailed(int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load pcc comm-addr successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadCommAddr results
         *
         */
        void mtcCprofCbPccCommAddrLoadOk();

        /**
         * @brief Set the CMCC profile load pcc comm-addr failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadCommAddr results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccCommAddrLoadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile upload pcc comm-addr successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadCommAddr results
         *
         */
        void mtcCprofCbPccCommAddrUploadOk();

        /**
         * @brief Set the CMCC profile upload pcc comm-addr failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadCommAddr results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccCommAddrUploadFailed(int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load pcc birth successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadBirth results
         *
         */
        void mtcCprofCbPccBirthLoadOk();

        /**
         * @brief Set the CMCC profile load pcc birth failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadBirth results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccBirthLoadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile upload pcc birth successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadBirth results
         *
         */
        void mtcCprofCbPccBirthUploadOk();

        /**
         * @brief Set the CMCC profile upload pcc birth failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadBirth results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccBirthUploadFailed(int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load pcc career successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadCareer results
         *
         */
        void mtcCprofCbPccCareerLoadOk();

        /**
         * @brief Set the CMCC profile load pcc career failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadCareer results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccCareerLoadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile upload pcc career successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadCareer results
         *
         */
        void mtcCprofCbPccCareerUploadOk();

        /**
         * @brief Set the CMCC profile upload pcc career failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadCareer results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccCareerUploadFailed(int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load pcc icon successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadIcon results
         *
         */
        void mtcCprofCbPccIconLoadOk();

        /**
         * @brief Set the CMCC profile load pcc icon failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccLoadIcon results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccIconLoadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile upload pcc icon successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadIcon results
         *
         */
        void mtcCprofCbPccIconUploadOk();

        /**
         * @brief Set the CMCC profile upload pcc icon failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofPccUploadIcon results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbPccIconUploadFailed(int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load all qr card successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardLoadAll results
         *
         */
        void mtcCprofCbQrCardAllLoadOk();

        /**
         * @brief Set the CMCC profile load all qr card failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardLoadAll results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbQrCardAllLoadFailed(int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load qr card flag successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardLoadFlag results
         *
         */
        void mtcCprofCbQrCardFlagLoadOk();

        /**
         * @brief Set the CMCC profile load qr card flag failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardLoadFlag results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbQrCardFlagLoadFailed(int dwStatCode);

        /**
         * @brief Set the CMCC profile upload qr card flag successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardUploadFlag results
         *
         */
        void mtcCprofCbQrCardFlagUploadOk();

        /**
         * @brief Set the CMCC profile upload qr card flag failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardUploadFlag results
         *
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbQrCardFlagUploadFailed(int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load qr card pcc successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardLoadPcc results
         *
         * @param [in] dwPccId The id of person contact card.
         */
        void mtcCprofCbQrCardPccLoadOk(int dwPccId);

        /**
         * @brief Set the CMCC profile load qr card pcc failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardLoadPcc results
         *
         * @param [in] dwPccId The id of person contact card.
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbQrCardPccLoadFailed(int dwPccId, int dwStatCode);
        
        /**
         * @brief Set the CMCC profile load qr card pcc icon successfully callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardLoadPccIcon results
         *
         * @param [in] dwIconId The id of icon.
         */
        void mtcCprofCbQrCardPccIconLoadOk(int dwIconId);

        /**
         * @brief Set the CMCC profile load qr card pcc icon failed callback.
         * The callback is one of @ref MtcCprof::Mtc_CprofQrCardLoadPccIcon results
         *
         * @param [in] dwIconId The id of icon.
         * @param [in] dwStatCode status code.
         */
        void mtcCprofCbQrCardPccIconLoadFailed(int dwIconId, int dwStatCode);
    }

    /**
     * @brief MTC CMCC profile callback init callbacks.
     *
     * This interface will call the native method to register client 
     * provisioning callback to MTC.
     */
    private static native void initCallback();

    /**
     * @brief MTC CMCC profile callback destory callbacks.
     *
     * This interface will call the native method to deregister client 
     * provisioning callback to MTC.
     */
    private static native void destroyCallback();

    private static Callback sCallback;

    /**
     * @brief MTC CMCC profile callback register callbacks.
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

    private static final int CALLBACK_CPROF_PCC_ALL_LOAD_OK = 0;
    private static final int CALLBACK_CPROF_PCC_ALL_LOAD_FAILED = CALLBACK_CPROF_PCC_ALL_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_ALL_UPLOAD_OK = CALLBACK_CPROF_PCC_ALL_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_ALL_UPLOAD_FAILED = CALLBACK_CPROF_PCC_ALL_UPLOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_NAME_LOAD_OK = CALLBACK_CPROF_PCC_ALL_UPLOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_NAME_LOAD_FAILED = CALLBACK_CPROF_PCC_NAME_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_NAME_UPLOAD_OK = CALLBACK_CPROF_PCC_NAME_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_NAME_UPLOAD_FAILED = CALLBACK_CPROF_PCC_NAME_UPLOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_ADDR_LOAD_OK = CALLBACK_CPROF_PCC_NAME_UPLOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_ADDR_LOAD_FAILED = CALLBACK_CPROF_PCC_ADDR_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_ADDR_UPLOAD_OK = CALLBACK_CPROF_PCC_ADDR_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_ADDR_UPLOAD_FAILED = CALLBACK_CPROF_PCC_ADDR_UPLOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_COMM_ADDR_LOAD_OK = CALLBACK_CPROF_PCC_ADDR_UPLOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_COMM_ADDR_LOAD_FAILED = CALLBACK_CPROF_PCC_COMM_ADDR_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_COMM_ADDR_UPLOAD_OK = CALLBACK_CPROF_PCC_COMM_ADDR_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_COMM_ADDR_UPLOAD_FAILED = CALLBACK_CPROF_PCC_COMM_ADDR_UPLOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_BIRTH_LOAD_OK = CALLBACK_CPROF_PCC_COMM_ADDR_UPLOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_BIRTH_LOAD_FAILED = CALLBACK_CPROF_PCC_BIRTH_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_BIRTH_UPLOAD_OK = CALLBACK_CPROF_PCC_BIRTH_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_BIRTH_UPLOAD_FAILED = CALLBACK_CPROF_PCC_BIRTH_UPLOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_CAREER_LOAD_OK = CALLBACK_CPROF_PCC_BIRTH_UPLOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_CAREER_LOAD_FAILED = CALLBACK_CPROF_PCC_CAREER_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_CAREER_UPLOAD_OK = CALLBACK_CPROF_PCC_CAREER_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_CAREER_UPLOAD_FAILED = CALLBACK_CPROF_PCC_CAREER_UPLOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_ICON_LOAD_OK = CALLBACK_CPROF_PCC_CAREER_UPLOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_ICON_LOAD_FAILED = CALLBACK_CPROF_PCC_ICON_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_PCC_ICON_UPLOAD_OK = CALLBACK_CPROF_PCC_ICON_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_PCC_ICON_UPLOAD_FAILED = CALLBACK_CPROF_PCC_ICON_UPLOAD_OK + 1;
    private static final int CALLBACK_CPROF_QRCARD_ALL_LOAD_OK = CALLBACK_CPROF_PCC_ICON_UPLOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_QRCARD_ALL_LOAD_FAILED = CALLBACK_CPROF_QRCARD_ALL_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_QRCARD_FLAG_LOAD_OK = CALLBACK_CPROF_QRCARD_ALL_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_QRCARD_FLAG_LOAD_FAILED = CALLBACK_CPROF_QRCARD_FLAG_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_QRCARD_FLAG_UPLOAD_OK = CALLBACK_CPROF_QRCARD_FLAG_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_QRCARD_FLAG_UPLOAD_FAILED = CALLBACK_CPROF_QRCARD_FLAG_UPLOAD_OK + 1;
    private static final int CALLBACK_CPROF_QRCARD_PCC_LOAD_OK = CALLBACK_CPROF_QRCARD_FLAG_UPLOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_QRCARD_PCC_LOAD_FAILED = CALLBACK_CPROF_QRCARD_PCC_LOAD_OK + 1;
    private static final int CALLBACK_CPROF_QRCARD_PCC_ICON_LOAD_OK = CALLBACK_CPROF_QRCARD_PCC_LOAD_FAILED + 1;
    private static final int CALLBACK_CPROF_QRCARD_PCC_ICON_LOAD_FAILED = CALLBACK_CPROF_QRCARD_PCC_ICON_LOAD_OK + 1;

    /**
     * @brief Distribute call callbacks
     *
     * Distribute call callbacks
     */
    private static void mtcCprofCbCallback(int function, int type, int dwId, String str, int dwStatCode) {
        switch (function) {
            case CALLBACK_CPROF_PCC_ALL_LOAD_OK:
                sCallback.mtcCprofCbPccAllLoadOk();
                break;
            case CALLBACK_CPROF_PCC_ALL_LOAD_FAILED:
                sCallback.mtcCprofCbPccAllLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_ALL_UPLOAD_OK:
                sCallback.mtcCprofCbPccAllUploadOk();
                break;
            case CALLBACK_CPROF_PCC_ALL_UPLOAD_FAILED:
                sCallback.mtcCprofCbPccAllUploadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_NAME_LOAD_OK:
                sCallback.mtcCprofCbPccNameLoadOk();
                break;
            case CALLBACK_CPROF_PCC_NAME_LOAD_FAILED:
                sCallback.mtcCprofCbPccNameLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_NAME_UPLOAD_OK:
                sCallback.mtcCprofCbPccNameUploadOk();
                break;
            case CALLBACK_CPROF_PCC_NAME_UPLOAD_FAILED:
                sCallback.mtcCprofCbPccNameUploadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_ADDR_LOAD_OK:
                sCallback.mtcCprofCbPccAddrLoadOk();
                break;
            case CALLBACK_CPROF_PCC_ADDR_LOAD_FAILED:
                sCallback.mtcCprofCbPccAddrLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_ADDR_UPLOAD_OK:
                sCallback.mtcCprofCbPccAddrUploadOk();
                break;
            case CALLBACK_CPROF_PCC_ADDR_UPLOAD_FAILED:
                sCallback.mtcCprofCbPccAddrUploadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_COMM_ADDR_LOAD_OK:
                sCallback.mtcCprofCbPccCommAddrLoadOk();
                break;
            case CALLBACK_CPROF_PCC_COMM_ADDR_LOAD_FAILED:
                sCallback.mtcCprofCbPccCommAddrLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_COMM_ADDR_UPLOAD_OK:
                sCallback.mtcCprofCbPccCommAddrUploadOk();
                break;
            case CALLBACK_CPROF_PCC_COMM_ADDR_UPLOAD_FAILED:
                sCallback.mtcCprofCbPccCommAddrUploadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_BIRTH_LOAD_OK:
                sCallback.mtcCprofCbPccBirthLoadOk();
                break;
            case CALLBACK_CPROF_PCC_BIRTH_LOAD_FAILED:
                sCallback.mtcCprofCbPccBirthLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_BIRTH_UPLOAD_OK:
                sCallback.mtcCprofCbPccBirthUploadOk();
                break;
            case CALLBACK_CPROF_PCC_BIRTH_UPLOAD_FAILED:
                sCallback.mtcCprofCbPccBirthUploadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_CAREER_LOAD_OK:
                sCallback.mtcCprofCbPccCareerLoadOk();
                break;
            case CALLBACK_CPROF_PCC_CAREER_LOAD_FAILED:
                sCallback.mtcCprofCbPccCareerLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_CAREER_UPLOAD_OK:
                sCallback.mtcCprofCbPccCareerUploadOk();
                break;
            case CALLBACK_CPROF_PCC_CAREER_UPLOAD_FAILED:
                sCallback.mtcCprofCbPccCareerUploadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_ICON_LOAD_OK:
                sCallback.mtcCprofCbPccIconLoadOk();
                break;
            case CALLBACK_CPROF_PCC_ICON_LOAD_FAILED:
                sCallback.mtcCprofCbPccIconLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_PCC_ICON_UPLOAD_OK:
                sCallback.mtcCprofCbPccIconUploadOk();
                break;
            case CALLBACK_CPROF_PCC_ICON_UPLOAD_FAILED:
                sCallback.mtcCprofCbPccIconUploadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_QRCARD_ALL_LOAD_OK:
                sCallback.mtcCprofCbQrCardAllLoadOk();
                break;
            case CALLBACK_CPROF_QRCARD_ALL_LOAD_FAILED:
                sCallback.mtcCprofCbQrCardAllLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_QRCARD_FLAG_LOAD_OK:
                sCallback.mtcCprofCbQrCardFlagLoadOk();
                break;
            case CALLBACK_CPROF_QRCARD_FLAG_LOAD_FAILED:
                sCallback.mtcCprofCbQrCardFlagLoadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_QRCARD_FLAG_UPLOAD_OK:
                sCallback.mtcCprofCbQrCardFlagUploadOk();
                break;
            case CALLBACK_CPROF_QRCARD_FLAG_UPLOAD_FAILED:
                sCallback.mtcCprofCbQrCardFlagUploadFailed(dwStatCode);
                break;
            case CALLBACK_CPROF_QRCARD_PCC_LOAD_OK:
                sCallback.mtcCprofCbQrCardPccLoadOk(dwId);
                break;
            case CALLBACK_CPROF_QRCARD_PCC_LOAD_FAILED:
                sCallback.mtcCprofCbQrCardPccLoadFailed(dwId, dwStatCode);
                break;
            case CALLBACK_CPROF_QRCARD_PCC_ICON_LOAD_OK:
                sCallback.mtcCprofCbQrCardPccIconLoadOk(dwId);
                break;
            case CALLBACK_CPROF_QRCARD_PCC_ICON_LOAD_FAILED:
                sCallback.mtcCprofCbQrCardPccIconLoadFailed(dwId, dwStatCode);
                break;
        }
    }
}
