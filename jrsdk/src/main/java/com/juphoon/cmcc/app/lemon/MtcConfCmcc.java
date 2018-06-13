/**
 * @file MtcConfCmcc.java
 * @brief MtcConfCmcc interface
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
 * @brief MtcConfCmcc interface
 */
public class MtcConfCmcc implements MtcConfCmccConstants {
/** 
 * @brief MTC conference cmc create.
 *
 * @return The id of this new created conference on succeed, otherwise return ZMAXUINT.
 *
 * Different with the method of session creation, this function only malloc
 * the resource of conference. If you want to setup or join a conference, you need
 * step ahead by calling other functions.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccSetup
 */
  public static int Mtc_ConfCmccCreate() {
    return MtcConfCmccJNI.Mtc_ConfCmccCreate();
  }

/** 
 * @brief MTC setup a new cmcc conference as the creator.
 *
 * If setup a new conference as the creator, GUI will be notified by callback which 
 * was set by @ref mtcConfCmccCbOutgoing, @ref mtcConfCmccCbAlerted,
 *            @ref mtcConfCmccCbConned
 *
 * While receiving conference invitation, GUI will be notified by callback which 
 * was set by @ref mtcConfCmccCbIncoming.
 *
 * @param [in] iConfId The id of conference which you want to setup.
 * @param [in] zCookie Used to correspond conference with UI resource.
 *
 * @retval MtcCommonConstants::ZOK on succeed.
 * @retval MtcCommonConstants::ZFAILED on failure.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccCreate
 */
  public static int Mtc_ConfCmccSetup(int iConfId, Object zCookie) {
    return MtcConfCmccJNI.Mtc_ConfCmccSetup(iConfId, zCookie);
  }

/** 
 * @brief MTC cmcc conference get conference URI and display name.
 *
 * @param [in] iConfId The id of conference which you want to get.
 * @param [out] ppcDispName The display name of conference.
 * @param [out] ppcUri The URI of conference.
 *
 * The caller must copy out parameter, then use.
 *
 * @retval MtcCommonConstants::ZOK on succeed.
 * @retval MtcCommonConstants::ZFAILED on failure.
 *
 */
  public static int Mtc_ConfCmccGetConfUri(int iConfId, MtcString ppcDispName, MtcString ppcUri) {
    return MtcConfCmccJNI.Mtc_ConfCmccGetConfUri(iConfId, ppcDispName, ppcUri);
  }

/** 
 * @brief MTC invite new participant list to a new conference, this interface 
 *        will setup a new conference automatically.
 *
 * @param [in] iConfId The id of conference to which you want invite new
 *                      participant list.
 * @param [in] zCookie Used to correspond conference with UI resource.
 * @param [in] iPartpLstId The participant list id, UI shall use 
 *                      @ref Mtc_PartpLstCreate, @ref Mtc_PartpLstAddPartp 
 *                      to create the id.
 *
 * @retval MtcCommonConstants::ZOK on succeed.
 * @retval MtcCommonConstants::ZFAILED on failure.
 *
 * @see @ref MtcConf::Mtc_ConfKickUser
 */
  public static int Mtc_ConfCmccIvtUserLst(int iConfId, Object zCookie, int iPartpLstId) {
    return MtcConfCmccJNI.Mtc_ConfCmccIvtUserLst(iConfId, zCookie, iPartpLstId);
  }

/**
 * @brief MTC join cmcc conference with subcribe.
 * 
 * The result will be notified by callbacks which were set by 
 * @ref mtcConfCmccCbJoinOk or @ref mtcConfCmccCbJoinFailed.
 *
 * @param [in] pcConfUri The uri of exist conference.
 *
 * @return The id of conference, otherwise return ZMAXUINT.
 *
 */
  public static int Mtc_ConfCmccJoin(String pcConfUri) {
    return MtcConfCmccJNI.Mtc_ConfCmccJoin(pcConfUri);
  }

/**
 * @brief MTC cmcc term conference.
 *
 * @param [in] iConfId The id of the conference.
 *
 * @retval MtcCommonConstants::ZOK term conference successfully.
 * @retval MtcCommonConstants::ZFAILED term conference failed.
 *
 * Actually Mtc_ConfTerm does not free all resource allocated for this
 * conference. It only starts a terminating procedure. All resource will be
 * freed automatically when the procedure ends.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccSetup, @ref MtcConfCmcc::Mtc_ConfCmccJoin,
 */
  public static int Mtc_ConfCmccTerm(int iConfId) {
    return MtcConfCmccJNI.Mtc_ConfCmccTerm(iConfId);
  }

/** 
 * @brief MTC cmcc conference invite an user to conference.
 *
 * @param [in] iConfId The id of conference to which you want invite new
 *                      participant.
 * @param [in] pcUserUri The uri of destination participant.
 *
 * @retval MtcCommonConstants::ZOK invite user successfully.
 * @retval MtcCommonConstants::ZFAILED invite user failed.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccKickOutUser
 */
  public static int Mtc_ConfCmccIvtUser(int iConfId, String pcUserUri) {
    return MtcConfCmccJNI.Mtc_ConfCmccIvtUser(iConfId, pcUserUri);
  }

/** 
 * @brief MTC cmcc kick out an user from conference. 
 *
 * @param [in] iConfId The id of conference to which you want kick participant.
 * @param [in] pcUserUri The uri of destination participant.
 *
 * @retval MtcCommonConstants::ZOK kick out user successfully.
 * @retval MtcCommonConstants::ZFAILED kick out user failed.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccIvtUser
 */
  public static int Mtc_ConfCmccKickOutUser(int iConfId, String pcUserUri) {
    return MtcConfCmccJNI.Mtc_ConfCmccKickOutUser(iConfId, pcUserUri);
  }

/** 
 * @brief MTC cmcc mute a participant. 
 *
 * @param [in] iConfId The id of conference to which you want mute a participant.
 * @param [in] pcUserUri The uri of destination participant.
 *
 * @retval MtcCommonConstants::ZOK mute user successfully.
 * @retval MtcCommonConstants::ZFAILED mute user failed.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccUnmute, Mtc_ConfCmccUnmuteAll
 */
  public static int Mtc_ConfCmccMute(int iConfId, String pcUserUri) {
    return MtcConfCmccJNI.Mtc_ConfCmccMute(iConfId, pcUserUri);
  }

/** 
 * @brief MTC cmcc unmute a participant. 
 *
 * @param [in] iConfId The id of conference to which you want unmute a participant.
 * @param [in] pcUserUri The uri of destination participant.
 *
 * @retval MtcCommonConstants::ZOK unmute user successfully.
 * @retval MtcCommonConstants::ZFAILED unmute user failed.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccMute, Mtc_ConfCmccMuteAll
 */
  public static int Mtc_ConfCmccUnmute(int iConfId, String pcUserUri) {
    return MtcConfCmccJNI.Mtc_ConfCmccUnmute(iConfId, pcUserUri);
  }

/** 
 * @brief MTC cmcc mute all participant. 
 *
 * @param [in] iConfId The id of conference to which you want mute all participant.
 *
 * @retval MtcCommonConstants::ZOK mute user successfully.
 * @retval MtcCommonConstants::ZFAILED mute user failed.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccUnmuteAll, Mtc_ConfCmccUnmute
 */
  public static int Mtc_ConfCmccMuteAll(int iConfId) {
    return MtcConfCmccJNI.Mtc_ConfCmccMuteAll(iConfId);
  }

/** 
 * @brief MTC cmcc unmute all participant. 
 *
 * @param [in] iConfId The id of conference to which you want unmute all participant.
 *
 * @retval MtcCommonConstants::ZOK unmute user successfully.
 * @retval MtcCommonConstants::ZFAILED unmute user failed.
 *
 * @see @ref MtcConfCmcc::Mtc_ConfCmccMuteAll, Mtc_ConfCmccMute
 */
  public static int Mtc_ConfCmccUnmuteAll(int iConfId) {
    return MtcConfCmccJNI.Mtc_ConfCmccUnmuteAll(iConfId);
  }

}
