/**
 * @file MtcCpCb.java
 * @brief MTC provisioning callbacks Interface Functions
 */
 package com.juphoon.cmcc.app.lemon.callback;

import java.util.ArrayList;

/**
 * @brief Class of MTC client provisioning callbacks
 */
public class MtcCpCb {

    /**
     * @brief MTC client provision callbacks
     *
     * In order to receive MTC client provisioning callbacks, user should 
     * implement this interface, then use @ref MtcCpCb.registerCallback to register 
     * callbacks.
     */
    public interface Callback {
        /** 
         * @brief MTC client provisioning ok callback.
         * 
         * @param dwCpId The ID of client provisioning.
         */
        void mtcCpCbCpOk(int dwCpId);

        /** 
         * @brief MTC client provisioning failed callback.
         * 
         * @param dwCpId The ID of client provisioning.
         * @param pcRspCode The response code of forbidden.
         * @param failedCode The failed code of client provisioning.
         */
        void mtcCpCbCpFailed(int dwCpId, String pcRspCode, int failedCode);
        
        /** 
         * @brief MTC client provisioning configure indication callback.
         */
        void mtcCpCbCpCfgInd();
        
        /** 
         * @brief MTC client provisioning reconfigure indication callback.
         */
        void mtcCpCbCpReCfgInd();
        
        /** 
         * @brief MTC client provisioning authorizaiton indication callback.
         * 
         * @param dwCpId The ID of client provisioning.
         */
        void  mtcCpCbCpAuthInd(int dwCpId);

        /** 
         * @brief MTC client provisioning auto authorizaiton indication callback.
         * 
         * @param dwCpId The ID of client provisioning.
         */
        void  mtcCpCbCpAutoAuthInd(int dwCpId);

        /** 
         * @brief MTC client provisioning receive message callback.
         * 
         * @param dwCpId The ID of client provisioning.
         * @param title The title of client provisioning.
         * @param msg The message of client provisioning.
         */
        void mtcCpCbCpRecvMsg(int dwCpId, String title, String msg);

        /** 
         * @brief MTC client provisioning prompt for OTP callback by primary 
         * device.
         * 
         * @param dwCpId The ID of client provisioning.
         */
        void mtcCpCbCpPromptMSISDN(int dwCpId);

        /** 
         * @brief MTC client provisioning prompt for OTP callback over SMS by 
         * primary device.
         * 
         * @param dwCpId The ID of client provisioning.
         */
        void mtcCpCbCpPromptOTP(int dwCpId);

        /** 
         * @brief MTC client provisioning prompt for OTP callback over SMS by 
         * additional device.
         * 
         * @param dwCpId The ID of client provisioning.
         */
        void mtcCpCbCpPromptOTPSMS(int dwCpId);

        /** 
         * @brief MTC client provisioning prompt for OTP callback over PIN by 
         * additional device.
         * 
         * @param dwCpId The ID of client provisioning.
         */
        void mtcCpCbCpPromptOTPPIN(int dwCpId);

        /** 
         * @brief MTC client provisioning receive end user message confirmation 
         * request.
         * 
         * @param dwMsgId The ID of the user message.
         * @param subject The subject of the user message.
         * @param txt The text of the user message.
         * @param acptBtnTxt The accept button text of the user message.
         * @param declBtnTxt The reject button text of the user message
         * @param needPin The use PIN flag of the user message
         */
        void mtcCpCbEMsgReq(int dwMsgId, String subject, String txt, String acptBtnTxt, String declBtnTxt, boolean needPin);

        /** 
         * @brief MTC client provisioning receive end user message confirmation 
         * ack.
         * 
         * @param dwMsgId The ID of the user message.
         * @param subject The subject of the user message.
         * @param txt The text of the user message.
         */
        void mtcCpCbEMsgAck(int dwMsgId, String subject, String txt);

        /** 
         * @brief MTC client provisioning receive end user message notification.
         * 
         * @param dwMsgId The ID of the user message.
         * @param subject The subject of the user message.
         * @param txt The text of the user message.
         * @param okBtnTxt The OK button text of the user message.
         */
        void mtcCpCbEMsgNtfy(int dwMsgId, String subject, String txt, String okBtnTxt);

        /** 
         * @brief MTC client provisioning expire callback.
         * @param iExpireType The expire type(see @ref MtcCpConstants::EN_MTC_CP_EXPIRE_TYPE).
         */
        void mtcCpCbCpExpire(int iExpireType);
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

  private static ArrayList<Callback> sCallbacks;
  
    /**
     * @brief MTC client provisioning callback register callbacks.
     *
     * Set the active client provisioning callback instance which to receive 
     * client provisioning callbacks.
     * Use unregisterCallback to deregister provisioning callbacks.
     *
     * @param c The client provisioning callback instance.
     */
    public static void registerCallback(final Callback c) {
        if (sCallbacks == null) {
            sCallbacks = new ArrayList<Callback>();
            initCallback();
        }
        MtcCliCb.sHandler.post(new Runnable() {
            @Override
            public void run() {
                sCallbacks.add(c);
            } 
        });
    }

    /**
     * @brief MTC client provisioning callback unregister callbacks.
     *
     * @param c The client provisioning callback instance.
     */
    public static void unregisterCallback(final Callback c) {
        if (sCallbacks == null) {
            return;
        }
        MtcCliCb.sHandler.post(new Runnable() {
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

    private static final int CALLBACK_CPCB_CPOK = 0;
    private static final int CALLBACK_CPCB_CPFAILED = CALLBACK_CPCB_CPOK + 1;
    private static final int CALLBACK_CPCB_CP_CFGIND = CALLBACK_CPCB_CPFAILED + 1;
    private static final int CALLBACK_CPCB_CP_RECFGIND = CALLBACK_CPCB_CP_CFGIND + 1;
    private static final int CALLBACK_CPCB_CP_AUTHIND = CALLBACK_CPCB_CP_RECFGIND + 1;
    private static final int CALLBACK_CPCB_CP_AUTO_AUTHIND = CALLBACK_CPCB_CP_AUTHIND + 1;
    private static final int CALLBACK_CPCB_CPRECVMSG = CALLBACK_CPCB_CP_AUTO_AUTHIND + 1;
    private static final int CALLBACK_CPCB_CPPROMPTMSISDN = CALLBACK_CPCB_CPRECVMSG + 1;
    private static final int CALLBACK_CPCB_CPPROMPTOTP = CALLBACK_CPCB_CPPROMPTMSISDN + 1;
    private static final int CALLBACK_CPCB_CPPROMPTOTPSMS = CALLBACK_CPCB_CPPROMPTOTP + 1;
    private static final int CALLBACK_CPCB_CPPROMPTOTPPIN = CALLBACK_CPCB_CPPROMPTOTPSMS + 1;
    private static final int CALLBACK_CPCB_EMSGREQ = CALLBACK_CPCB_CPPROMPTOTPPIN + 1;
    private static final int CALLBACK_CPCB_EMSGACK = CALLBACK_CPCB_EMSGREQ + 1;
    private static final int CALLBACK_CPCB_EMSGNTF = CALLBACK_CPCB_EMSGACK + 1;
    private static final int CALLBACK_CPCB_CPEXPIRE = CALLBACK_CPCB_EMSGNTF + 1;

  private static void mtcCpCbCallback(int function, int id, int errCode,
      String s1, String s2, String s3, String s4, boolean needPin) {
    switch (function) {
    case CALLBACK_CPCB_CPOK:
            for (Callback c : sCallbacks) {
                  c.mtcCpCbCpOk(id);
            }
      break;
    case CALLBACK_CPCB_CPFAILED:
            for (Callback c : sCallbacks) {
                  c.mtcCpCbCpFailed(id, s1, errCode);
            }
      break;
    case CALLBACK_CPCB_CP_CFGIND:
        for (Callback c : sCallbacks) {
                c.mtcCpCbCpCfgInd();
            }
        break;
    case CALLBACK_CPCB_CP_RECFGIND:
        for (Callback c : sCallbacks) {
                c.mtcCpCbCpReCfgInd();
            }
        break;
    case CALLBACK_CPCB_CP_AUTHIND:
        for (Callback c : sCallbacks) {
                c.mtcCpCbCpAuthInd(id);
            }
        break;
    case CALLBACK_CPCB_CP_AUTO_AUTHIND:
        for (Callback c : sCallbacks) {
                c.mtcCpCbCpAutoAuthInd(id);
            }
        break;        
    case CALLBACK_CPCB_CPRECVMSG:
            for (Callback c : sCallbacks) {
              c.mtcCpCbCpRecvMsg(id, s1, s2);
            }
      break;
    case CALLBACK_CPCB_CPPROMPTMSISDN:
            for (Callback c : sCallbacks) {
              c.mtcCpCbCpPromptMSISDN(id);
            }
      break;
    case CALLBACK_CPCB_CPPROMPTOTP:
            for (Callback c : sCallbacks) {
              c.mtcCpCbCpPromptOTP(id);
            }
      break;
    case CALLBACK_CPCB_CPPROMPTOTPSMS:
            for (Callback c : sCallbacks) {
              c.mtcCpCbCpPromptOTPSMS(id);
            }
      break;
    case CALLBACK_CPCB_CPPROMPTOTPPIN:
            for (Callback c : sCallbacks) {
              c.mtcCpCbCpPromptOTPPIN(id);
            }
      break;
    case CALLBACK_CPCB_EMSGREQ:
            for (Callback c : sCallbacks) {
              c.mtcCpCbEMsgReq(id, s1, s2, s3, s4, needPin);
            }
      break;
    case CALLBACK_CPCB_EMSGACK:
            for (Callback c : sCallbacks) {
              c.mtcCpCbEMsgAck(id, s1, s2);
            }
      break;
    case CALLBACK_CPCB_EMSGNTF:
            for (Callback c : sCallbacks) {
              c.mtcCpCbEMsgNtfy(id, s1, s2, s3);
            }
      break;
      case CALLBACK_CPCB_CPEXPIRE:
          for (Callback c : sCallbacks) {
              c.mtcCpCbCpExpire(errCode);
            }
          break;
    }
  }
}
