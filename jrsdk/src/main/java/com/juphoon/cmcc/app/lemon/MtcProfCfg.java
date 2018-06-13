/**
 * @file MtcProfCfg.java
 * @brief MtcProfCfg interface
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
 * @brief MtcProfCfg interface
 */
public class MtcProfCfg {
/**
 * @brief Get using default profile management.
 *
 * MTC provide default profile management. Each account profile
 * is stored in different directory under "profiles" directory.
 * Every Account profile have provision.xml etc configuration files.
 *
 * If client using another profile management, client should close 
 * it by @ref MtcProfCfg::Mtc_ProfCfgSetUseDft. After @ref MtcCli::Mtc_CliOpen, client should
 * assign database configuration by @ref MtcCliDb::Mtc_CliDbSetLocalIp etc interfaces.
 *
 * @retval true MTC provide profile management.
 * @retval false MTC don't have profile management.
 *
 * @see @ref MtcProfCfg::Mtc_ProfCfgSetUseDft
 */
  public static boolean Mtc_ProfCfgGetUseDft() {
    return MtcProfCfgJNI.Mtc_ProfCfgGetUseDft();
  }

/**
 * @brief Set using default profile management.
 *
 * @param [in] bUse Use default profile management.
 *
 * @retval MtcCommonConstants::ZOK Set use status successfully.
 * @retval MtcCommonConstants::ZFAILED Set use status failed.
 *
 * @see @ref MtcProfCfg::Mtc_ProfCfgGetUseDft
 */
  public static int Mtc_ProfCfgSetUseDft(boolean bUse) {
    return MtcProfCfgJNI.Mtc_ProfCfgSetUseDft(bUse);
  }

}
