/**
 * @file MtcImOmsgConstants.java
 * @brief MtcImOmsgConstants constants
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
 * @brief MtcImOmsgConstants constants
 */
public interface MtcImOmsgConstants {
  public final static int MTC_EBASE_CP = 0xE000; /**< @brief CP error base */
  public final static int MTC_EBASE_REG = 0xE100; /**< @brief REG error base */
  public final static int MTC_EBASE_CALL = 0xE200; /**< @brief CALL error base */
  public final static int MTC_EBASE_VSHARE = 0xE300; /**< @brief VSHARE error base */
  public final static int MTC_EBASE_CAP = 0xE400; /**< @brief CAP error base */
  public final static int MTC_EBASE_BUDDY = 0xE500; /**< @brief BUDDY error base */
  public final static int MTC_EBASE_GRP = 0xE600; /**< @brief GRP error base */
  public final static int MTC_EBASE_CONF = 0xE700; /**< @brief CONF error base */
  public final static int MTC_EBASE_GS = 0xE800; /**< @brief GS error base */
  public final static int MTC_EBASE_PRES = 0xE900; /**< @brief PRES error base */
  public final static int MTC_EBASE_IM = 0xEA00; /**< @brief IM error base */
  public final static int MTC_EBASE_LCS = 0xEB00; /**< @brief LCS error base */
  public final static int MTC_EBASE_PA = 0xEC00; /**< @brief PA error base */
  public final static int MTC_EBASE_CPROF = 0xED00; /**< @brief CRPOF error base */
  public final static int MTC_EBASE_GBA = 0xEE00; /**< @brief GBA error base */
  // EN_MTC_OMSG_MSG_TYPE 
  public final static int EN_MTC_OMSG_MSG_PAGE = 0; /* page offline message */
  public final static int EN_MTC_OMSG_MSG_LARGE = EN_MTC_OMSG_MSG_PAGE + 1; /* large offline message */
  public final static int EN_MTC_OMSG_MSG_GINFO_IVTED = EN_MTC_OMSG_MSG_LARGE + 1; /* geo-info invited offline message */
  public final static int EN_MTC_OMSG_MSG_FILE_IVTED = EN_MTC_OMSG_MSG_GINFO_IVTED + 1; /* file invited offline message */
  public final static int EN_MTC_OMSG_MSG_GRP_IVTED = EN_MTC_OMSG_MSG_FILE_IVTED + 1; /* group invited offline message */
  public final static int EN_MTC_OMSG_MSG_GRP_MSG = EN_MTC_OMSG_MSG_GRP_IVTED + 1; /* group message offline message */
  public final static int EN_MTC_OMSG_MSG_UNKOWN = EN_MTC_OMSG_MSG_GRP_MSG + 1; /* unkown*/

}
