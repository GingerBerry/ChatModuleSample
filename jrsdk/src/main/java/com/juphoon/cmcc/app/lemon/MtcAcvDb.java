/**
 * @file MtcAcvDb.java
 * @brief MtcAcvDb interface
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
 * @brief MtcAcvDb interface
 */
public class MtcAcvDb {
/** 
 * @brief MTC archive db set archive server address.
 *
 * @param [in] pcServAddr The archive server address.
 *
 * @retval MtcCommonConstants::ZOK Set archive server address provision successfully.
 * @retval MtcCommonConstants::ZFAILED et archive server address provision failed.
 */
  public static int Mtc_AcvDbSetSrvAddr(String pcServAddr) {
    return MtcAcvDbJNI.Mtc_AcvDbSetSrvAddr(pcServAddr);
  }

/** 
 * @brief MTC archive db get archive server address.
 *
 * @retval The archive server address.
 *
 */
  public static String Mtc_AcvDbGetSrvAddr() {
    return MtcAcvDbJNI.Mtc_AcvDbGetSrvAddr();
  }

/** 
 * @brief MTC archive db set archive server port.
 *
 * @param [in] wPort The archive server port.
 *
 * @retval MtcCommonConstants::ZOK Set archive server port provision successfully.
 * @retval MtcCommonConstants::ZFAILED et archive server port provision failed.
 */
  public static int Mtc_AcvDbSetSrvPort(int wPort) {
    return MtcAcvDbJNI.Mtc_AcvDbSetSrvPort(wPort);
  }

/** 
 * @brief MTC archive db get archive server port.
 *
 * @retval The archive server port.
 *
 */
  public static int Mtc_AcvDbGetSrvPort() {
    return MtcAcvDbJNI.Mtc_AcvDbGetSrvPort();
  }

/** 
 * @brief MTC archive db set archive url in the archive server.
 *
 * @param [in] pcServUrl The archive url in the archive server.
 *
 * @retval MtcCommonConstants::ZOK Set archive url provision successfully.
 * @retval MtcCommonConstants::ZFAILED et archive url provision failed.
 */
  public static int Mtc_AcvDbSetUrl(String pcUrl) {
    return MtcAcvDbJNI.Mtc_AcvDbSetUrl(pcUrl);
  }

/** 
 * @brief MTC archive db get archive url in the archive server.
 *
 * @retval The archive url.
 *
 */
  public static String Mtc_AcvDbGetUrl() {
    return MtcAcvDbJNI.Mtc_AcvDbGetUrl();
  }

}
