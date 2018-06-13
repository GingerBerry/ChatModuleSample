/**
 * @file MtcImDbConstants.java
 * @brief MtcImDbConstants constants
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
 * @brief MtcImDbConstants constants
 */
public interface MtcImDbConstants {
  // EN_MTC_IM_FT_MECH_TYPE 
  public final static int EN_MTC_IM_FT_MECH_UNKNOWN = 0; /**< @brief unknown */
  public final static int EN_MTC_IM_FT_MECH_MSRP = EN_MTC_IM_FT_MECH_UNKNOWN + 1; /**< @brief use msrp */
  public final static int EN_MTC_IM_FT_MECH_HTTP = EN_MTC_IM_FT_MECH_MSRP + 1; /**< @brief use http */

  // EN_MTC_IM_PROTO_TYPE 
  public final static int EN_MTC_IM_PROTO_MSRP_TCP = 0; /**< @brief msrp over tcp */
  public final static int EN_MTC_IM_PROTO_MSRP_TLS = EN_MTC_IM_PROTO_MSRP_TCP + 1; /**< @brief msrp over tls */

  // EN_MTC_PMSG_ALLOWDTER_TYPE 
  public final static int EN_MTC_PMSG_ALLOWDTER_PC_NONE = 0; /* pc none*/
  public final static int EN_MTC_PMSG_ALLOWDTER_PC_CALLER = EN_MTC_PMSG_ALLOWDTER_PC_NONE + 1; /* pc caller*/
  public final static int EN_MTC_PMSG_ALLOWDTER_PC_CALLED = EN_MTC_PMSG_ALLOWDTER_PC_CALLER + 1; /* pc called */
  public final static int EN_MTC_PMSG_ALLOWDTER_PC_ENTIRE = EN_MTC_PMSG_ALLOWDTER_PC_CALLED + 1; /* entire pc */
  public final static int EN_MTC_PMSG_ALLOWDTER_BOX_NONE = EN_MTC_PMSG_ALLOWDTER_PC_ENTIRE + 1; /* box none */
  public final static int EN_MTC_PMSG_ALLOWDTER_BOX_CALLER = EN_MTC_PMSG_ALLOWDTER_BOX_NONE + 1; /* box caller*/
  public final static int EN_MTC_PMSG_ALLOWDTER_BOX_CALLED = EN_MTC_PMSG_ALLOWDTER_BOX_CALLER + 1; /* box called */
  public final static int EN_MTC_PMSG_ALLOWDTER_BOX_ENTIRE = EN_MTC_PMSG_ALLOWDTER_BOX_CALLED + 1; /* entire box */
  public final static int EN_MTC_PMSG_ALLOWDTER_WEB_NONE = EN_MTC_PMSG_ALLOWDTER_BOX_ENTIRE + 1; /* web none */
  public final static int EN_MTC_PMSG_ALLOWDTER_WEB_CALLER = EN_MTC_PMSG_ALLOWDTER_WEB_NONE + 1; /* web caller*/
  public final static int EN_MTC_PMSG_ALLOWDTER_WEB_CALLED = EN_MTC_PMSG_ALLOWDTER_WEB_CALLER + 1; /* web called */
  public final static int EN_MTC_PMSG_ALLOWDTER_WEB_ENTIRE = EN_MTC_PMSG_ALLOWDTER_WEB_CALLED + 1; /* entire web */
  public final static int EN_MTC_PMSG_ALLOWDTER_ALL_NONE = EN_MTC_PMSG_ALLOWDTER_WEB_ENTIRE + 1; /* all none*/
  public final static int EN_MTC_PMSG_ALLOWDTER_ALL_CALLER = EN_MTC_PMSG_ALLOWDTER_ALL_NONE + 1; /* all caller*/
  public final static int EN_MTC_PMSG_ALLOWDTER_ALL_CALLED = EN_MTC_PMSG_ALLOWDTER_ALL_CALLER + 1; /* all called */
  public final static int EN_MTC_PMSG_ALLOWDTER_ALL_ENTIRE = EN_MTC_PMSG_ALLOWDTER_ALL_CALLED + 1; /* entire all */
  public final static int EN_MTC_PMSG_ALLOWDTER_UNKOWN = EN_MTC_PMSG_ALLOWDTER_ALL_ENTIRE + 1; /* unkown option */

  // EN_MTC_ALLOWED_SMS_TYPE 
  public final static int EN_MTC_IM_ALLOWED_SMS_SERVER = 0; /* server allowed SMS */
  public final static int EN_MTC_IM_ALLOWED_SMS_YES = EN_MTC_IM_ALLOWED_SMS_SERVER + 1; /* terminal allowed SMS */
  public final static int EN_MTC_IM_ALLOWED_SMS_NO = EN_MTC_IM_ALLOWED_SMS_YES + 1; /* terminal not allowed SMS */

}
