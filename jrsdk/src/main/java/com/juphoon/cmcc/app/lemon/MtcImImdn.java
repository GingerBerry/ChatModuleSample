/**
 * @file MtcImImdn.java
 * @brief MtcImImdn interface
 */
/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.juphoon.cmcc.app.lemon;

/**
 * @brief MtcImImdn interface
 */
public class MtcImImdn {
/**
 * @brief MTC Sending a display notification through imdn.
 *
 * @param [in] iSessId The session id.
 * @param [in] pcIMsgId The The imdn message id.
 * @param [in] pcUri The message receiver URI.
 * @param [in] pcDeviceId The message receiver device id.
 * @param [in] pcConvId The message conversation id.
 *
 * @retval MtcCommonConstants::ZOK Send a display notification successfully.
 * @retval MtcCommonConstants::ZFAILED Send a display notification failed.
 */
  public static int Mtc_ImdnSendDisp(int iSessId, String pcIMsgId, String pcUri, String pcDeviceId, String pcConvId) {
    return MtcImImdnJNI.Mtc_ImdnSendDisp(iSessId, pcIMsgId, pcUri, pcDeviceId, pcConvId);
  }

/**
 * @brief MTC Sending a delivery notification through imdn.
 *
 * @param [in] iSessId The session id.
 * @param [in] pcIMsgId The The imdn message id.
 * @param [in] pcUri The message receiver URI.
 * @param [in] pcDeviceId The message receiver device id.
 * @param [in] pcConvId The message conversation id.
 *
 * @retval MtcCommonConstants::ZOK Send a delivery notification successfully.
 * @retval MtcCommonConstants::ZFAILED Send a delivery notification failed.
 */
  public static int Mtc_ImdnSendDeli(int iSessId, String pcIMsgId, String pcUri, String pcDeviceId, String pcConvId) {
    return MtcImImdnJNI.Mtc_ImdnSendDeli(iSessId, pcIMsgId, pcUri, pcDeviceId, pcConvId);
  }

/**
 * @brief MTC Sending a burned notification through imdn.
 *
 * @param [in] pcIMsgId The The imdn message id.
 * @param [in] pcUri The message receiver URI.
 * @param [in] pcDeviceId The message receiver device id.
 * @param [in] pcConvId The message conversation id.
 *
 * @retval MtcCommonConstants::ZOK Send a burned notification successfully.
 * @retval MtcCommonConstants::ZFAILED Send a burned notification failed.
 */
  public static int Mtc_ImdnSendBurn(String pcIMsgId, String pcUri, String pcDeviceId, String pcConvId) {
    return MtcImImdnJNI.Mtc_ImdnSendBurn(pcIMsgId, pcUri, pcDeviceId, pcConvId);
  }

/**
 * @brief Get imdn message id from imdn.
 *
 * @param [in] iImdnId The imdn id.
 *
 * @return Imdn message id.
 * The caller must copy it, then use.
 */
  public static String Mtc_ImdnGetIMsgId(int iImdnId) {
    return MtcImImdnJNI.Mtc_ImdnGetIMsgId(iImdnId);
  }

/**
 * @brief MTC Get participant information from imdn.
 *
 * The participant is the remote client who send a imdn notification message.
 *
 * @param [in] iImdnId The imdn id.
 * @param [in,out] ppcName The participant name.
 * @param [in,out] ppcUri The participant URI.
 *
 * The caller must copy out parameter, then use.
 *
 * @retval MtcCommonConstants::ZOK Get participant information successfully.
 * @retval MtcCommonConstants::ZFAILED Get participant information failed.
 */
  public static int Mtc_ImdnGetPartp(int iImdnId, MtcString ppcName, MtcString ppcUri) {
    return MtcImImdnJNI.Mtc_ImdnGetPartp(iImdnId, ppcName, ppcUri);
  }

}
