package com.juphoon.jrsdk;

import android.text.TextUtils;

import com.juphoon.cmcc.app.lemon.MtcCall;
import com.juphoon.cmcc.app.lemon.MtcCallDb;
import com.juphoon.cmcc.app.lemon.MtcCli;
import com.juphoon.cmcc.app.lemon.MtcCliCfg;
import com.juphoon.cmcc.app.lemon.MtcCliDb;
import com.juphoon.cmcc.app.lemon.MtcCliDbConstants;
import com.juphoon.cmcc.app.lemon.MtcCommon;
import com.juphoon.cmcc.app.lemon.MtcCommonConstants;
import com.juphoon.cmcc.app.lemon.MtcConf;
import com.juphoon.cmcc.app.lemon.MtcConfCmcc;
import com.juphoon.cmcc.app.lemon.MtcConfDb;
import com.juphoon.cmcc.app.lemon.MtcCp;
import com.juphoon.cmcc.app.lemon.MtcCpDb;
import com.juphoon.cmcc.app.lemon.MtcNumber;
import com.juphoon.cmcc.app.lemon.MtcPartp;
import com.juphoon.cmcc.app.lemon.MtcPartpConstants;
import com.juphoon.cmcc.app.lemon.MtcProf;
import com.juphoon.cmcc.app.lemon.MtcProfDb;
import com.juphoon.cmcc.app.lemon.MtcProvDb;
import com.juphoon.cmcc.app.lemon.MtcUri;
import com.juphoon.cmcc.app.lemon.MtcUtil;

import java.util.ArrayList;
import java.util.List;

interface MtcNotifyListener {
    void onNotify(JRBaseNotify notify);
}

class MtcEngine {
    private static MtcEngine sMtcEngine;

    static MtcEngine getInstance() {
        if (sMtcEngine == null) {
            sMtcEngine = new MtcEngine();
        }
        return sMtcEngine;
    }

    private ArrayList<MtcNotifyListener> mMtcNotifyListeners = new ArrayList<>();

    void dealNotify(JRBaseNotify notify) {
        if (sMtcEngine == null) {
            sMtcEngine = getInstance();
        }
        for (MtcNotifyListener listener : mMtcNotifyListeners) {
            listener.onNotify(notify);
        }
    }

    public void addCallback(MtcNotifyListener listener) {
        if (!mMtcNotifyListeners.contains(listener)) {
            mMtcNotifyListeners.add(listener);
        }
    }

    public void removeCallback(MtcNotifyListener listener) {
        mMtcNotifyListeners.remove(listener);
    }

    /**
     * Client
     */
    public JRClientResult.InitSDK startInitSDK(JRClientParam.InitSDK param) {
        JRClientResult.InitSDK result = new JRClientResult.InitSDK();
        return result;
    }

    public JRClientResult.Login login(JRClientParam.Login param) {
        MtcCliCfg.Mtc_CliCfgSetRegSrvType(MtcCliCfg.EN_MTC_REG_SRV_CMCC_RCS);

        JRClientResult.Login result = new JRClientResult.Login();
        int net = MtcUtil.Mtc_GetAccessNetType();
        if (net == MtcCli.MTC_ANET_UNAVAILABLE) {
            result.isSucceed = false;
            JRLog.printf("没有网络");
            return result;
        }
        int ret = MtcCli.Mtc_CliOpen(param.accountName);
        if (ret == MtcCommonConstants.ZOK) {
            ret = MtcCli.Mtc_CliStart();
            ret = MtcCli.Mtc_CliLogin(net, null);
            if (ret == MtcCommonConstants.ZOK) {
                result.isSucceed = true;
                JRLog.printf("注册接口调用成功");
            } else {
                result.isSucceed = false;
                JRLog.printf("注册失败 调用接口失败");
            }
        } else {
            result.isSucceed = false;
            JRLog.printf("打开账号失败");
        }
        return result;
    }

    public JRClientResult.Logout logout() {
        int ret = MtcCli.Mtc_CliLogout();
        JRClientResult.Logout result = new JRClientResult.Logout();
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRClientResult.GetUser getCurrentUser() {
        JRClientResult.GetUser result = new JRClientResult.GetUser();
        result.user = MtcProf.Mtc_ProfGetCurUser();
        return result;
    }

    public JRClientResult.GetNumber getCurrentNumber() {
        JRClientResult.GetNumber result = new JRClientResult.GetNumber();
        result.number = MtcCliDb.Mtc_CliDbGetUserName();
        return result;
    }

    public JRClientResult.GetState getState() {
        JRClientResult.GetState result = new JRClientResult.GetState();
        result.state = MtcCli.Mtc_CliGetRegState();
        return result;
    }

    public JRClientResult.SetToken setToken(JRClientParam.SetToken param) {
        JRClientResult.SetToken result = new JRClientResult.SetToken();
        switch (param.type) {
            case JRClientDefine.MTC_CLIENT_SET_TOKEN_TYPE_MULTI_VIDEO:
                result.isSucceed = MtcConfDb.Mtc_ConfDbSetHttpConfToken(param.token) == MtcCommonConstants.ZOK;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * Account
     */
    public JRAccountResult.Create createAccount(JRAccountParam.Create param) {
        int ret = MtcCli.Mtc_CliOpen(param.accountName);
        JRAccountResult.Create result = new JRAccountResult.Create();
        if (ret == MtcCommonConstants.ZFAILED) {
            JRLog.printf("打开账号失败 %s", param.accountName);
        } else {
            MtcCallDb.Mtc_CallDbSetCwc(true);
            MtcCallDb.Mtc_CallDbSetVideoH264Profile((short) MtcCallDb.EN_MTC_DB_H264_PROFILE_BASELINE);
            MtcCliDb.Mtc_CliDbSetRoamType(MtcCliDb.EN_MTC_ROAM_NONE);
            MtcCliDb.Mtc_CliDbSetStgUseType(MtcCliDb.EN_MTC_STG_USETYPE_CLOSE);
            MtcCallDb.Mtc_CallDbSetVideoImgAttrEnable(false);
        }
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRAccountResult.Delete deleteAccount(JRAccountParam.Delete param) {
        int ret = MtcProf.Mtc_ProfDeleteUser(param.accountName);
        JRAccountResult.Delete result = new JRAccountResult.Delete();
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRAccountResult.GetConfig getAccountConfig(JRAccountParam.GetConfig param) {
        int ret = MtcCli.Mtc_CliOpen(param.accountName);
        JRAccountResult.GetConfig result = new JRAccountResult.GetConfig();
        if (ret == MtcCommonConstants.ZFAILED) {
            JRLog.printf("打开账号失败 %s", param.accountName);
            return null;
        }
        String value = "";
        int sesstmrInfoType = new MtcNumber().getValue();
        int sesstmrInfoLen = new MtcNumber().getValue();
        int sesstmrInfoMinLen = new MtcNumber().getValue();
        switch (param.key) {
            case UserName:
                value = MtcCliDb.Mtc_CliDbGetUserName();
                break;
            case Password:
                value = MtcCliDb.Mtc_CliDbGetAuthPass();
                break;
            case RegIp:
                value = MtcCliDb.Mtc_CliDbGetSipRegIp();
                break;
            case AuthName:
                value = MtcCliDb.Mtc_CliDbGetAuthName();
                break;
            case RegRealm:
                value = MtcCliDb.Mtc_CliDbGetSipRegRealm();
                break;
            case SipDscpValue:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetSipDscpValue());
                break;
            case AudioDscpValue:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVoiceDscpValue());
                break;
            case VideoDscpValue:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoDscpValue());
                break;
            case TlsType:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetTlsCliVeryType());
                break;
            case SipTransportType:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetSipRegTpt());
                break;
            case SipPort:
                short tpt = MtcCliDb.Mtc_CliDbGetSipRegTpt();
                String port = String.valueOf(MtcCliDb.Mtc_CliDbGetSipRegUdpPort());
                if (tpt == MtcCliDbConstants.EN_MTC_TPT_TCP) {
                    port = String.valueOf(MtcCliDb.Mtc_CliDbGetSipRegTcpPort());
                } else if (tpt == MtcCliDbConstants.EN_MTC_TPT_TLS) {
                    port = String.valueOf(MtcCliDb.Mtc_CliDbGetSipRegTlsPort());
                }
                value = port;
                break;
            case SipInstanceEnable:
                break;
            case RegType:
                value = String.valueOf(MtcCliCfg.Mtc_CliCfgGetRegSrvType());
                break;
            case RegNoDigest:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetRegNoDigest());
                break;
            case RegExpireTime:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetRegExpire());
                break;
            case OpenSubs:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetSubsRegEvnt());
                break;
            case SubsExpireTime:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetSubsRegExpire());
                break;
            case PsHeartBeat:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetHeartbeatTmr());
                break;
            case WifiHeartBeat:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetWifiHeartbeatTmr());
                break;
            case VideoWAndH:
                MtcNumber piVideoresolutionW = new MtcNumber();
                MtcNumber piVideoresolutionH = new MtcNumber();
                MtcCallDb.Mtc_CallDbGetVideoResolution(piVideoresolutionW, piVideoresolutionH);
                int videoResolutionW = piVideoresolutionW.getValue();
                int videoResolutionH = piVideoresolutionH.getValue();
                value = videoResolutionW + "X" + videoResolutionH;
                break;
            case Heartbeat:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetKeepAliveType());
                break;
            case SessType:
                value = String.valueOf(sesstmrInfoType);
                break;
            case SessLen:
                value = String.valueOf(sesstmrInfoLen);
                break;
            case SessMinLen:
                value = String.valueOf(sesstmrInfoMinLen);
                break;
            case UseTelUri:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetUseTelUri());
                break;
            case AudioCode:
                break;
            case AudioPtime:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetPtime());
                break;
            case DtmfType:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetDtmfType());
                break;
            case DtmfPayload:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetDtmfPayload());
                break;
            case DtmfNotify:
                break;
            case SendAgcMode:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAgcMode());
                break;
            case SendAgc:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAgcTarget());
                break;
            case RecvAgc:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetRxAgcTarget());
                break;
            case RecvAgcMode:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetRxAgcMode());
                break;
            case SendAgcEnable:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAgcEnable());
                break;
            case RecvAgcEnable:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetRxAgcEnable());
                break;
            case SendAnrMode:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAnrMode());
                break;
            case SendAnr:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAnrEnable());
                break;
            case RecvAnrMode:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetRxAnrMode());
                break;
            case RecvAnr:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetRxAnrEnable());
                break;
            case BufferMinDelay:
                MtcNumber minDelay = new MtcNumber();
                MtcNumber maxPacket = new MtcNumber();
                MtcCallDb.Mtc_CallDbGetAudioJitterParms(minDelay, maxPacket);
                value = String.valueOf(minDelay.getValue());
                break;
            case BufferMaxPacket:
                MtcNumber minDelay1 = new MtcNumber();
                MtcNumber maxPacket1 = new MtcNumber();
                MtcCallDb.Mtc_CallDbGetAudioJitterParms(minDelay1, maxPacket1);
                value = String.valueOf(maxPacket1.getValue());
                break;
            case VadMode:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVadMode());
                break;
            case Vad:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVadEnable());
                break;
            case Aec:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAecEnable());
                break;
            case AecMode:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAecMode());
                break;
            case Fec:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAudioRed());
                break;
            case VideoCode:
                break;
            case H264PacketMode:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoH264Packetmode());
                break;
            case H264Payload:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetH264Payload());
                break;
            case BitrateValue:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoBitrate());
                break;
            case AudioBitrate:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAmrBitrate());
                break;
            case BitrateControl:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoArs());
                break;
            case ResolutionControl:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetResolutionControl());
                break;
            case VideoArs:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoArs());
                break;
            case FramerateMax:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoFramerate());
                break;
            case VideoFramerateControl:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetFramerateControl());
                break;
            case VideoFramerate:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoFramerate());
                break;
            case KeyPeroid:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetKeyPeriod() / 1000);
                break;
            case FirByInfo:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetFirByInfo());
                break;
            case Fir:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetFir());
                break;
            case Rpsi:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetRpsiEnable());
                break;
            case VideoFec:
//                    return String.valueOf(MtcCallDb.Mtc_CallDbGetVideoRedFec());
            case VoiceFec:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAudioRed());
                break;
            case Nack:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetNackEnable());
                break;
            case SrtpType:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetSrtpCryptoType());
                break;
            case AudioRtcpmux:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetAudioRtcpMuxEnable());
                break;
            case VideoRtcpmux:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoRtcpMuxEnable());
                break;
            case NatType:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetNatTraversalMode());
                break;
            case StunServer:
                value = MtcCliDb.Mtc_CliDbGetStunServerName();
                break;
            case StunServerPort:
                value = String.valueOf(MtcCliDb.Mtc_CliDbGetStunServerPort());
                break;
            case Rtx:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetRtxEnable());
                break;
            case Bem:
                value = String.valueOf(MtcCallDb.Mtc_CallDbGetVideoBem());
                break;
            default:
                break;
        }
        result.value = value;
        return result;
    }

    public JRAccountResult.SetConfig setAccountConfig(JRAccountParam.SetConfig param) {
        int ret = MtcCli.Mtc_CliOpen(param.accountName);
        JRAccountResult.SetConfig result = new JRAccountResult.SetConfig();
        if (ret == MtcCommonConstants.ZFAILED) {
            result.isSucceed = false;
            JRLog.printf("打开账号失败 %s", param.accountName);
            return result;
        }
        for (JRAccountDefine.ConfigKey key : param.extraParams.keySet()) {
            String value;
            int sesstmrInfoType = new MtcNumber().getValue();
            int sesstmrInfoLen = new MtcNumber().getValue();
            int sesstmrInfoMinLen = new MtcNumber().getValue();
            switch (key) {
                case UserName:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.UserName);
                    MtcCliDb.Mtc_CliDbSetUserName(value);
                    MtcCliDb.Mtc_CliDbSetImpu("sip:" + value + "@" + MtcCliDb.Mtc_CliDbGetSipRegRealm());
                    break;
                case Password:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Password);
                    MtcCliDb.Mtc_CliDbSetAuthPass(value);
                    break;
                case AuthName:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.AuthName);
                    MtcCliDb.Mtc_CliDbSetAuthName(value);
                    break;
                case RegIp:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RegIp);
                    MtcCliDb.Mtc_CliDbSetSipRegIp(value);
                    break;
                case LogLevel:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.LogLevel);
                    MtcCliCfg.Mtc_CliCfgSetLogLevel(Integer.parseInt(value));
                    break;
                case SipDscpValue:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SipDscpValue);
                    MtcCliDb.Mtc_CliDbSetSipDscpValue(Integer.parseInt(value));
                    break;
                case AudioDscpValue:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.AudioDscpValue);
                    MtcCallDb.Mtc_CallDbSetVoiceDscpValue(Integer.parseInt(value));
                    break;
                case VideoDscpValue:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VideoDscpValue);
                    MtcCallDb.Mtc_CallDbSetVideoDscpValue(Integer.parseInt(value));
                    break;
                case TlsType:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.TlsType);
                    MtcCliDb.Mtc_CliDbSetTlsCliVeryType(Integer.parseInt(value));
                    break;
                case RegRealm:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RegRealm);
                    MtcCliDb.Mtc_CliDbSetSipRegRealm(value);
                    MtcCliDb.Mtc_CliDbSetImpu("sip:" + MtcCliDb.Mtc_CliDbGetUserName() + "@" + value);
                    break;
                case SipTransportType:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SipTransportType);
                    MtcCliDb.Mtc_CliDbSetSipRegTpt(Short.parseShort(value));
                    break;
                case SipPort:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SipPort);
                    MtcCliDb.Mtc_CliDbSetSipRegTcpPort(Integer.parseInt(value));
                    MtcCliDb.Mtc_CliDbSetSipRegUdpPort(Integer.parseInt(value));
                    MtcCliDb.Mtc_CliDbSetSipRegTlsPort(Integer.parseInt(value));
                    break;
                case SipInstanceEnable:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SipInstanceEnable);
                    MtcCliDb.Mtc_CliDbSetSipInstanceEnable(Boolean.parseBoolean(value));
                    break;
                case RegType:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RegType);
                    MtcCliCfg.Mtc_CliCfgSetRegSrvType(Integer.parseInt(value));
                    break;
                case RegNoDigest:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RegNoDigest);
                    MtcCliDb.Mtc_CliDbSetRegNoDigest(Boolean.parseBoolean(value));
                    break;
                case RegExpireTime:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RegExpireTime);
                    MtcCliDb.Mtc_CliDbSetRegExpire(Integer.parseInt(value));
                    break;
                case OpenSubs:
                    String openSubs = param.extraParams.get(JRAccountDefine.ConfigKey.OpenSubs);
                    MtcCliDb.Mtc_CliDbSetSubsRegEvnt(Boolean.parseBoolean(openSubs));
                    break;
                case SubsExpireTime:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SubsExpireTime);
                    MtcCliDb.Mtc_CliDbSetSubsRegExpire(Integer.parseInt(value));
                    break;
                case PsHeartBeat:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.PsHeartBeat);
                    MtcCliDb.Mtc_CliDbSetHeartbeatTmr(Integer.parseInt(value));
                    break;
                case WifiHeartBeat:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.WifiHeartBeat);
                    MtcCliDb.Mtc_CliDbSetWifiHeartbeatTmr(Integer.parseInt(value));
                    break;
                case VideoWAndH:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VideoWAndH);
                    int width = Integer.parseInt(value.split("X")[0]);
                    int height = Integer.parseInt(value.split("X")[1]);
                    MtcCallDb.Mtc_CallDbSetVideoResolution(width, height);
                    break;
                case Heartbeat:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Heartbeat);
                    MtcCliDb.Mtc_CliDbSetKeepAliveType(Integer.parseInt(value));
                    break;
                case SessType:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SessType);
                    sesstmrInfoType = Integer.parseInt(value);
                    MtcCallDb.Mtc_CallDbSetSessTmrInfo(sesstmrInfoType, sesstmrInfoLen, sesstmrInfoMinLen);
                    break;
                case SessLen:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SessLen);
                    sesstmrInfoLen = Integer.parseInt(value);
                    MtcCallDb.Mtc_CallDbSetSessTmrInfo(sesstmrInfoType, sesstmrInfoLen, sesstmrInfoMinLen);
                    break;
                case SessMinLen:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SessMinLen);
                    sesstmrInfoMinLen = Integer.parseInt(value);
                    MtcCallDb.Mtc_CallDbSetSessTmrInfo(sesstmrInfoType, sesstmrInfoLen, sesstmrInfoMinLen);
                    break;
                case UseTelUri:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.UseTelUri);
                    MtcCliDb.Mtc_CliDbSetUseTelUri(Boolean.parseBoolean(value));
                    break;
                case AudioCode:
                    String audioCode = param.extraParams.get(JRAccountDefine.ConfigKey.AudioCode);
                    break;
                case AudioBitrate:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.AudioBitrate);
                    MtcCallDb.Mtc_CallDbSetAmrBitrate(Integer.parseInt(value));
                    break;
                case AudioPtime:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.AudioPtime);
                    MtcCallDb.Mtc_CallDbSetPtime(Integer.parseInt(value));
                    break;
                case DtmfType:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.DtmfType);
                    MtcCallDb.Mtc_CallDbSetDtmfType(Integer.parseInt(value));
                    break;
                case DtmfPayload:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.DtmfPayload);
                    MtcCallDb.Mtc_CallDbSetDtmfPayload(Integer.parseInt(value));
                    break;
                case DtmfNotify:
                    String dtmfNotify = param.extraParams.get(JRAccountDefine.ConfigKey.DtmfNotify);
                    break;
                case SendAgcMode:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SendAgcMode);
                    int mode = Integer.parseInt(value);
                    if (mode >= 0) {
                        MtcCallDb.Mtc_CallDbSetAgcEnable(true);
                        MtcCallDb.Mtc_CallDbSetAgcMode(Short.parseShort(value));
                    } else {
                        MtcCallDb.Mtc_CallDbSetAgcEnable(false);
                    }
                    break;
                case SendAgc:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SendAgc);
                    MtcCallDb.Mtc_CallDbSetAgcTarget(Short.parseShort(value));
                    break;
                case RecvAgc:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RecvAgc);
                    MtcCallDb.Mtc_CallDbSetRxAgcTarget(Short.parseShort(value));
                    break;
                case RecvAgcMode:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RecvAgcMode);
                    int mode1 = Integer.parseInt(value);
                    if (mode1 >= 0) {
                        MtcCallDb.Mtc_CallDbSetRxAgcEnable(true);
                        MtcCallDb.Mtc_CallDbSetRxAgcMode(Short.parseShort(value));
                    } else {
                        MtcCallDb.Mtc_CallDbSetRxAgcEnable(false);
                    }
                    break;
                case SendAnrMode:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SendAnrMode);
                    MtcCallDb.Mtc_CallDbSetAnrMode((short) Integer.parseInt(value));
                    break;
                case SendAnr:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SendAnr);
                    MtcCallDb.Mtc_CallDbSetAnrEnable(Boolean.parseBoolean(value));
                    break;
                case RecvAnrMode:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RecvAnrMode);
                    MtcCallDb.Mtc_CallDbSetRxAnrMode((short) Integer.parseInt(value));
                    break;
                case RecvAnr:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.RecvAnr);
                    MtcCallDb.Mtc_CallDbSetRxAnrEnable(Boolean.parseBoolean(value));
                    break;
                case BufferMinDelay:
                    MtcNumber minDelay = new MtcNumber();
                    MtcNumber maxPacket = new MtcNumber();
                    MtcCallDb.Mtc_CallDbGetAudioJitterParms(minDelay, maxPacket);
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.BufferMinDelay);
                    int max = maxPacket.getValue();
                    MtcCallDb.Mtc_CallDbSetAudioJitterParms(Integer.parseInt(value), max);
                    break;
                case BufferMaxPacket:
                    MtcNumber minDelay1 = new MtcNumber();
                    MtcNumber maxPacket1 = new MtcNumber();
                    MtcCallDb.Mtc_CallDbGetAudioJitterParms(minDelay1, maxPacket1);
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.BufferMaxPacket);
                    int max1 = minDelay1.getValue();
                    MtcCallDb.Mtc_CallDbSetAudioJitterParms(max1, Integer.parseInt(value));
                    break;
                case VadMode:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VadMode);
                    int index = Integer.parseInt(value);
                    if (index >= 0) {
                        MtcCallDb.Mtc_CallDbSetVadEnable(true);
                        MtcCallDb.Mtc_CallDbSetVadMode((short) Integer.parseInt(value));
                    } else {
                        MtcCallDb.Mtc_CallDbSetVadEnable(false);
                    }
                    break;
                case Vad:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Vad);
                    MtcCallDb.Mtc_CallDbSetVadEnable(Boolean.parseBoolean(value));
                    break;
                case Aec:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Aec);
                    MtcCallDb.Mtc_CallDbSetAecEnable(Boolean.parseBoolean(value));
                    break;
                case AecMode:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.AecMode);
                    int aecMode = Integer.parseInt(value) + 1;
                    MtcCallDb.Mtc_CallDbSetAecMode((short) aecMode);
                    break;
                case Fec:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Fec);
                    MtcCallDb.Mtc_CallDbSetAudioRed(Boolean.parseBoolean(value));
                    break;
                case VideoCode:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VideoCode);
                    break;
                case H264PacketMode:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.H264PacketMode);
                    MtcCallDb.Mtc_CallDbSetVideoH264Packetmode(Short.parseShort(value));
                    break;
                case H264Payload:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.H264Payload);
                    MtcCallDb.Mtc_CallDbSetH264Payload((Integer.parseInt(value)));
                    break;
                case BitrateValue:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.BitrateValue);
                    MtcCallDb.Mtc_CallDbSetVideoBitrate(Integer.parseInt(value));
                    break;
                case BitrateControl:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.BitrateControl);
                    String.valueOf(MtcCallDb.Mtc_CallDbSetVideoArs(Boolean.parseBoolean(value)));
                    break;
                case ResolutionControl:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.ResolutionControl);
                    MtcCallDb.Mtc_CallDbSetResolutionControl(Boolean.parseBoolean(value));
                    break;
                case VideoArs:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VideoArs);
                    MtcCallDb.Mtc_CallDbSetVideoArs(Boolean.parseBoolean(value));
                    break;
                case FramerateMax:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.FramerateMax);
                    MtcCallDb.Mtc_CallDbSetVideoFramerate(Integer.parseInt(value));
                    break;
                case VideoFramerateControl:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VideoFramerateControl);
                    MtcCallDb.Mtc_CallDbSetFramerateControl(Boolean.parseBoolean(value));
                    break;
                case VideoFramerate:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VideoFramerate);
                    MtcCallDb.Mtc_CallDbSetVideoFramerate(Integer.parseInt(value));
                    break;
                case KeyPeroid:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.KeyPeroid);
                    MtcCallDb.Mtc_CallDbSetKeyPeriod(Integer.valueOf(value) * 1000);
                    break;
                case Fir:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Fir);
                    MtcCallDb.Mtc_CallDbSetFir(Boolean.parseBoolean(value));
                    break;
                case FirByInfo:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.FirByInfo);
                    MtcCallDb.Mtc_CallDbSetFirByInfo(Boolean.parseBoolean(value));
                    break;
                case Rpsi:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Rpsi);
                    MtcCallDb.Mtc_CallDbSetRpsiEnable(Boolean.parseBoolean(value));
                    break;
                case VideoFec:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VideoFec);
//                        MtcCallDb.Mtc_CallDbSetVideoRedFec(Boolean.parseBoolean(value));
                    break;
                case VoiceFec:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VoiceFec);
                    MtcCallDb.Mtc_CallDbSetAudioRed(Boolean.parseBoolean(value));
                    break;
                case Nack:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Nack);
                    MtcCallDb.Mtc_CallDbSetNackEnable(Boolean.parseBoolean(value));
                    break;
                case SrtpType:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.SrtpType);
                    MtcCallDb.Mtc_CallDbSetSrtpCryptoType(Integer.parseInt(value));
                    break;
                case AudioRtcpmux:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.AudioRtcpmux);
                    MtcCallDb.Mtc_CallDbSetAudioRtcpMuxEnable(Boolean.parseBoolean(value));
                    break;
                case VideoRtcpmux:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.VideoRtcpmux);
                    MtcCallDb.Mtc_CallDbSetVideoRtcpMuxEnable(Boolean.parseBoolean(value));
                    break;
                case NatType:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.NatType);
                    MtcCliDb.Mtc_CliDbSetNatTraversalMode(Integer.parseInt(value));
                    break;
                case StunServer:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.StunServer);
                    MtcCliDb.Mtc_CliDbSetStunServerName(value);
                    break;
                case StunServerPort:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.StunServerPort);
                    MtcCliDb.Mtc_CliDbSetStunServerPort(Integer.parseInt(value));
                    break;
                case Rtx:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Rtx);
                    MtcCallDb.Mtc_CallDbSetRtxEnable(Boolean.parseBoolean(value));
                    break;
                case Bem:
                    value = param.extraParams.get(JRAccountDefine.ConfigKey.Bem);
                    MtcCallDb.Mtc_CallDbSetVideoBem(Boolean.parseBoolean(value));
                    break;
                default:
                    break;
            }
        }
        MtcCliDb.Mtc_CliDbApplyAll();
        MtcProf.Mtc_ProfSaveProvision();
        result.isSucceed = true;
        return result;
    }

    public JRAccountResult.GetList getAccountList() {
        List<String> accounts = new ArrayList<>();
        for (int i = 0; i < MtcProf.Mtc_ProfGetUserSize(); i++) {
            accounts.add(MtcProf.Mtc_ProfGetUser(i));
        }
        JRAccountResult.GetList result = new JRAccountResult.GetList();
        result.accountList = accounts;
        return result;
    }

    /**
     * CP
     */
    public JRAutoConfigResult.Start startAutoConfig(JRAutoConfigParam.Start param) {
        JRAutoConfigResult.Start result = new JRAutoConfigResult.Start();
        if (MtcCli.Mtc_CliOpen(param.account) == MtcCommonConstants.ZOK) {
            MtcCliDb.Mtc_CliDbSetUserName(param.account);
            MtcCpDb.Mtc_CpDbSetMsisdn(param.account);
            MtcCliDb.Mtc_CliDbSetAuthPass(param.password);
            MtcCpDb.Mtc_CpDbSetToken(param.token);
            MtcCpDb.Mtc_CpDbSetSrvAddr("config.rcs.chinamobile.com");
            MtcProfDb.Mtc_ProfDbSetCpEnable(true);
            MtcCliDb.Mtc_CliDbSetLocalIp("0.0.0.0");
            MtcCliDb.Mtc_CliDbSetRoamType(MtcCliDb.EN_MTC_ROAM_NONE);
            MtcCliCfg.Mtc_CliCfgSetRegSrvType(MtcCliCfg.EN_MTC_REG_SRV_CMCC_RCS);
            MtcProvDb.Mtc_ProvDbSetTmnlType(MtcProvDb.EN_MTC_PROV_TMNL_APP);
            MtcCliDb.Mtc_CliDbApplyAll();
            MtcProf.Mtc_ProfSaveProvision();
            if (MtcCli.Mtc_CliStart() == MtcCommonConstants.ZOK) {
                result.cpId = MtcCp.Mtc_Cp(MtcCp.EN_MTC_CP_NET_WIFI, param.token);
                result.isSucceed = true;
            }
        }
        return result;
    }

    public JRAutoConfigResult.SetToken setCpToken(JRAutoConfigParam.SetToken param) {
        JRAutoConfigResult.SetToken result = new JRAutoConfigResult.SetToken();
        if (TextUtils.isEmpty(param.token)) {
            JRLog.printf("token 为空");
            result.isSucceed = false;
        } else {
            result.isSucceed = MtcCp.Mtc_CpPromptToken(param.cpId, param.token) == MtcCommonConstants.ZOK;
        }
        return result;
    }

    /**
     * Call
     */
    public JRCallResult.Start call(JRCallParam.Start param) {
        JRCallResult.Start result = new JRCallResult.Start();
        if (param.isConf) {
            int confId;
            if (param.isVideo) {
                confId = MtcConf.Mtc_ConfCreate();
            } else {
                confId = MtcConfCmcc.Mtc_ConfCmccCreate();
            }
            if (confId == MtcCommon.INVALIDID) {
                JRLog.printf("创建多方通话失败");
                result.isSucceed = false;
                return result;
            }
            if (param.peerNumber.size() > 0) {
                int partpListId = MtcPartp.Mtc_PartpLstCreate(MtcPartpConstants.EN_MTC_PARTP_LST_URI_LST);
                for (String number : param.peerNumber) {
                    MtcPartp.Mtc_PartpLstAddPartp(partpListId, number, formatNumber(number));
                }
                int ret;
                if (param.isVideo) {
                    ret = MtcConf.Mtc_ConfIvtUserLst(confId, null, partpListId, true);
                } else {
                    ret = MtcConfCmcc.Mtc_ConfCmccIvtUserLst(confId, null, partpListId);
                }
                if (ret == MtcCommon.ZOK) {
                    JRLog.printf("多方视频发起成功");
                    result.isSucceed = true;
                    result.callId = confId;
                    result.reason = JRCallDefine.MTC_CALL_TERM_REASON_NONE;
                } else {
                    JRLog.printf("多方视频发起失败 调用接口失败");
                    result.isSucceed = false;
                }
            } else {
                int ret;
                if (param.isVideo) {
                    ret = MtcConf.Mtc_ConfSetup(confId, null);
                } else {
                    ret = MtcConfCmcc.Mtc_ConfCmccSetup(confId, null);
                }
                if (ret == MtcCommon.ZOK) {
                    JRLog.printf("多方通话发起成功");
                    result.isSucceed = true;
                    result.callId = confId;
                    result.reason = JRCallDefine.MTC_CALL_TERM_REASON_NONE;
                } else {
                    JRLog.printf("多方通话发起失败 调用接口失败");
                    result.isSucceed = false;
                }
            }
        } else {
            if (param.peerNumber.size() < 1) {
                JRLog.printf("呼叫失败 无号码");
                result.isSucceed = false;
                return result;
            }
            int sessId = MtcCall.Mtc_SessCall(formatNumber(param.peerNumber.get(0)), null, true, param.isVideo);
            if (sessId == MtcCommon.INVALIDID) {
                JRLog.printf("一对一通话发起失败 调用接口失败");
                result.isSucceed = false;
            } else {
                JRLog.printf("一对一通话发起成功");
                result.isSucceed = true;
                result.callId = sessId;
                result.renderId = MtcCall.Mtc_SessGetName(sessId);
                result.reason = JRCallDefine.MTC_CALL_TERM_REASON_NONE;
            }
        }
        return result;
    }

    public JRCallResult.Answer answer(JRCallParam.Answer param) {
        JRCallResult.Answer result = new JRCallResult.Answer();
        if (param.callId == MtcCommon.INVALIDID) {
            JRLog.printf("接听电话失败 无效的callId");
            result.isSucceed = false;
        }
        if (param.isConf) {
            int ret = MtcConf.Mtc_ConfAcpt(param.callId, null);
            if (ret == MtcCommon.ZOK) {
                JRLog.printf("接听多方通话成功");
                result.isSucceed = true;
            } else {
                JRLog.printf("接听多方通话失败");
                result.isSucceed = false;
            }
        } else {
            int ret = MtcCall.Mtc_SessAnswer(param.callId, 0, true, param.isVideo);
            if (ret == MtcCommon.ZOK) {
                JRLog.printf("接听一对一通话成功");
                result.isSucceed = true;
            } else {
                JRLog.printf("接听一对一通话失败");
                result.isSucceed = false;
            }
        }
        return result;
    }

    public JRCallResult.End end(JRCallParam.End param) {
        JRCallResult.End result = new JRCallResult.End();
        if (param.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("结束通话失败 无效的callId");
            result.isSucceed = true;
            return result;
        }
        int reason;
        switch (param.reason) {
            case JRCallDefine.MTC_CALL_TERM_REASON_NORMAL:
                reason = MtcCall.EN_MTC_CALL_TERM_REASON_NORMAL;
                break;
            case JRCallDefine.MTC_CALL_TERM_REASON_BUSY:
                reason = MtcCall.EN_MTC_CALL_TERM_REASON_BUSY;
                break;
            case JRCallDefine.MTC_CALL_TERM_REASON_DECLINE:
                reason = MtcCall.EN_MTC_CALL_TERM_REASON_DECLINE;
                break;
            case JRCallDefine.MTC_CALL_TERM_REASON_NOT_AVAILABLE:
                reason = MtcCall.EN_MTC_CALL_TERM_REASON_NOT_AVAILABLE;
                break;
            case JRCallDefine.MTC_CALL_TERM_REASON_INTERRUPT:
                reason = MtcCall.EN_MTC_CALL_TERM_REASON_INTERRUPT;
                break;
            default:
                reason = MtcCall.EN_MTC_CALL_TERM_REASON_NORMAL;
                break;
        }
        if (param.isConf) {
            if (param.isVideo) {
                int ret = MtcConf.Mtc_ConfTerm(param.callId, reason, false);
                if (ret == MtcCommonConstants.ZOK) {
                    JRLog.printf("结束多方视频成功 Mtc_ConfTerm");
                    result.isSucceed = true;
                } else {
                    JRLog.printf("结束多方视频失败 Mtc_ConfTerm");
                    result.isSucceed = false;
                }
            } else {
                int ret = MtcConfCmcc.Mtc_ConfCmccKickOutUser(param.callId, MtcUri.Mtc_UriFormatX(MtcCliDb.Mtc_CliDbGetUserName(), false));
                if (ret == MtcCommonConstants.ZOK) {
                    JRLog.printf("结束多方通话成功 Mtc_ConfCmccKickOutUser");
                    result.isSucceed = true;
                } else {
                    JRLog.printf("结束多方通话失败 Mtc_ConfCmccKickOutUser");
                    result.isSucceed = false;
                }
            }
        } else {
            int ret = MtcCall.Mtc_SessTerm(param.callId, reason);
            if (ret == MtcCommonConstants.ZOK) {
                JRLog.printf("结束一对一通话成功");
                result.isSucceed = true;
            } else {
                JRLog.printf("结束一对一通话失败");
                result.isSucceed = false;
            }
        }
        return result;
    }

    public JRCallResult.Mute mute(JRCallParam.Mute param) {
        JRCallResult.Mute result = new JRCallResult.Mute();
        if (param.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("静音失败 无效的callId");
            result.isSucceed = false;
            return result;
        }
        if (param.isConf) {
            int ret = MtcConf.Mtc_ConfSetMicMute(param.callId, param.toMute);
            if (ret == MtcCommonConstants.ZOK) {
                JRLog.printf("多方通话静音成功");
                result.isSucceed = true;
            } else {
                JRLog.printf("多方通话静音失败");
                result.isSucceed = false;
            }
        } else {
            int ret = MtcCall.Mtc_SessSetMicMute(param.callId, param.toMute);
            if (ret == MtcCommonConstants.ZOK) {
                JRLog.printf("一对一通话静音成功");
                result.isSucceed = true;
            } else {
                JRLog.printf("一对一通话静音失败");
                result.isSucceed = false;
            }
        }
        return result;
    }

    public JRCallResult.Hold hold(JRCallParam.Hold param) {
        JRCallResult.Hold result = new JRCallResult.Hold();
        if (param.callId == MtcCommonConstants.INVALIDID) {
            JRLog.printf("保持通话失败 无效的callId");
            result.isSucceed = false;
            return result;
        }
        if (param.isConf) {
            int ret = MtcConf.Mtc_ConfHold(param.callId, param.toHold);
            if (ret == MtcCommonConstants.ZOK) {
                JRLog.printf("多方通话保持或解除保持成功");
                result.isSucceed = true;
            } else {
                JRLog.printf("多方通话保持或解除保持失败");
                result.isSucceed = false;
            }
        } else {
            if (param.toHold) {
                int ret = MtcCall.Mtc_SessHold(param.callId);
                if (ret == MtcCommonConstants.ZOK) {
                    JRLog.printf("一对一通话保持成功");
                    result.isSucceed = true;
                } else {
                    JRLog.printf("一对一通话保持失败");
                    result.isSucceed = false;
                }
            } else {
                int ret = MtcCall.Mtc_SessUnhold(param.callId);
                if (ret == MtcCommonConstants.ZOK) {
                    JRLog.printf("一对一通话解除保持成功");
                    result.isSucceed = true;
                } else {
                    JRLog.printf("一对一通话解除保持失败");
                    result.isSucceed = false;
                }
            }
        }
        return result;
    }

    public JRCallResult.SendDtmf sendDtmf(JRCallParam.SendDtmf param) {
        JRCallResult.SendDtmf result = new JRCallResult.SendDtmf();
        int ret = MtcCall.Mtc_SessDtmf(param.callId, param.dtmfType);
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRCallResult.Update update(JRCallParam.Update param) {
        JRCallResult.Update result = new JRCallResult.Update();
        int ret = MtcCall.Mtc_SessUpdate(param.callId, true, param.toVideo);
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRCallResult.AnswerUpdate answerUpdate(JRCallParam.AnswerUpdate param) {
        JRCallResult.AnswerUpdate result = new JRCallResult.AnswerUpdate();
        int ret = MtcCall.Mtc_SessUpdateRsp(param.callId, true, param.toVideo);
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRCallResult.AddMember addMember(JRCallParam.AddMember param) {
        JRCallResult.AddMember result = new JRCallResult.AddMember();
        if (param.isVideo) {
            int ret = MtcConf.Mtc_ConfIvtUser(param.callId, formatNumber(param.peerNumber));
            if (ret == MtcCommonConstants.ZOK) {
                JRLog.printf("多方视频添加成员接口调用成功 Mtc_ConfIvtUser");
                result.isSucceed = true;
            } else {
                JRLog.printf("多方视频添加成员接口调用失败 Mtc_ConfIvtUser");
                result.isSucceed = false;
            }
        } else {
            int ret = MtcConfCmcc.Mtc_ConfCmccIvtUser(param.callId, formatNumber(param.peerNumber));
            if (ret == MtcCommonConstants.ZOK) {
                JRLog.printf("多方通话添加成员接口调用成功 Mtc_ConfCmccIvtUser");
                result.isSucceed = true;
            } else {
                JRLog.printf("多方通话添加成员接口调用失败 Mtc_ConfCmccIvtUser");
                result.isSucceed = false;
            }
        }
        return result;
    }

    public JRCallResult.RemoveMember removeMember(JRCallParam.RemoveMember param) {
        JRCallResult.RemoveMember result = new JRCallResult.RemoveMember();
        if (param.isVideo) {
            int ret = MtcConf.Mtc_ConfKickUser(param.callId, formatNumber(param.peerNumber));
            if (ret == MtcCommonConstants.ZOK) {
                JRLog.printf("多方视频踢出成员接口调用成功 Mtc_ConfKickUser");
                result.isSucceed = true;
            } else {
                JRLog.printf("多方视频踢出成员接口调用成功 Mtc_ConfKickUser");
                result.isSucceed = false;
            }
        } else {
            int ret = MtcConfCmcc.Mtc_ConfCmccKickOutUser(param.callId, formatNumber(param.peerNumber));
            if (ret == MtcCommonConstants.ZOK) {
                JRLog.printf("多方通话踢出成员接口调用成功 Mtc_ConfKickUser");
                result.isSucceed = true;
            } else {
                JRLog.printf("多方通话踢出成员接口调用成功 Mtc_ConfKickUser");
                result.isSucceed = false;
            }
        }
        return result;
    }

    public JRCallResult.GetStatistics getStatistics(JRCallParam.GetStatistics param) {
        JRCallResult.GetStatistics result = new JRCallResult.GetStatistics();
        String statistics = "";
        if (param.isConf) {
            if (param.isVideo) {
                //statistics = (ZCHAR *)Mtc_ConfGetVideoStat(param.callId)
            } else {
                statistics = MtcConf.Mtc_ConfGetAudioStat(param.callId);
            }
        } else {
            if (param.isVideo) {
                statistics = MtcCall.Mtc_SessGetVideoStat(param.callId);
            } else {
                statistics = MtcCall.Mtc_SessGetAudioStat(param.callId);
            }
        }
        if (!statistics.isEmpty()) {
            result.statistics = statistics;
        }
        return result;
    }

    public JRCallResult.GetFlow getFlow(JRCallParam.GetFlow param) {
        JRCallResult.GetFlow result = new JRCallResult.GetFlow();
        MtcNumber sentBps = new MtcNumber();
        MtcNumber recvBps = new MtcNumber();
        MtcNumber sentBytes = new MtcNumber();
        MtcNumber recvBytes = new MtcNumber();
        if (param.isVideo) {
            MtcCall.Mtc_SessVideoTrafficData(param.callId, sentBps, recvBps, sentBytes, recvBytes);
        } else {
            MtcCall.Mtc_SessAudioTrafficData(param.callId, sentBps, recvBps, sentBytes, recvBytes);
        }
        result.sentBps = sentBps.getValue();
        result.recvBps = recvBps.getValue();
        result.sentBytes = sentBytes.getValue();
        result.recvBytes = recvBytes.getValue();
        return result;
    }

    public JRCallResult.Attach attach(JRCallParam.Attach param) {
        JRCallResult.Attach result = new JRCallResult.Attach();
        int ret = MtcCommonConstants.ZFAILED;
        if (param.isConf) {
            ret = MtcConf.Mtc_ConfCameraAttach(param.callId, MtcConf.Mtc_ConfGetVideoStrmIdByIndex(param.callId, 0), param.camera);
        } else {
            ret = MtcCall.Mtc_SessCameraAttach(param.callId, param.camera);
        }
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRCallResult.Detach detach(JRCallParam.Detach param) {
        JRCallResult.Detach result = new JRCallResult.Detach();
        int ret = MtcCommonConstants.ZFAILED;
        if (param.isConf) {
            ret = MtcConf.Mtc_ConfCameraDetach(param.callId, MtcConf.Mtc_ConfGetVideoStrmIdByIndex(param.callId, 0));
        } else {
            ret = MtcCall.Mtc_SessCameraDetach(param.callId);
        }
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRCallResult.StartVideo startVideo(JRCallParam.StartVideo param) {
        JRCallResult.StartVideo result = new JRCallResult.StartVideo();
        int ret = MtcCall.Mtc_SessVideoStart(param.callId);
        result.isSucceed = ret == MtcCommonConstants.ZOK;
        return result;
    }

    public JRCallResult.ConfMute muteMultiCallMember(JRCallParam.ConfMute param) {
        JRCallResult.ConfMute result = new JRCallResult.ConfMute();
        int ret = MtcCommonConstants.ZFAILED;
        if (param.isAll) {
            if (param.toMute) {
                ret = MtcConfCmcc.Mtc_ConfCmccMuteAll(param.callId);
            } else {
                ret = MtcConfCmcc.Mtc_ConfCmccUnmuteAll(param.callId);
            }
        } else {
            if (param.toMute) {
                ret = MtcConfCmcc.Mtc_ConfCmccMute(param.callId, formatNumber(param.peerNumber));
            } else {
                ret = MtcConfCmcc.Mtc_ConfCmccUnmute(param.callId, formatNumber(param.peerNumber));
            }
        }
        return result;
    }

    public JRCallResult.ConfJoin confJoin(JRCallParam.ConfJoin param) {
        JRCallResult.ConfJoin result = new JRCallResult.ConfJoin();
        result.callId = MtcConfCmcc.Mtc_ConfCmccJoin(param.pcConfUri);
        result.isSucceed = result.callId == MtcCommonConstants.INVALIDID;
        return result;
    }

    /**
     * Group
     */

    public JRGroupResult.Create create(JRGroupParam.Create param) {
        JRGroupResult.Create result = new JRGroupResult.Create();
        return result;
    }

    public JRGroupResult.Leave leave(JRGroupParam.Leave param) {
        JRGroupResult.Leave result = new JRGroupResult.Leave();
        return result;
    }

    public JRGroupResult.ModifyGroupName modifyGroupName(JRGroupParam.ModifyGroupName param) {
        JRGroupResult.ModifyGroupName result = new JRGroupResult.ModifyGroupName();
        return result;
    }

    public JRGroupResult.ModifyNickName modifyNickName(JRGroupParam.ModifyNickName param) {
        JRGroupResult.ModifyNickName result = new JRGroupResult.ModifyNickName();
        return result;
    }

    public JRGroupResult.Dissolve dissolve(JRGroupParam.Dissolve param) {
        JRGroupResult.Dissolve result = new JRGroupResult.Dissolve();
        return result;
    }

    public JRGroupResult.Reject rejectInvite(JRGroupParam.Reject param) {
        JRGroupResult.Reject result = new JRGroupResult.Reject();
        return result;
    }

    public JRGroupResult.Accept acceptInvite(JRGroupParam.Accept param) {
        JRGroupResult.Accept result = new JRGroupResult.Accept();
        return result;
    }

    public JRGroupResult.Invite invite(JRGroupParam.Invite param) {
        JRGroupResult.Invite result = new JRGroupResult.Invite();
        return result;
    }

    public JRGroupResult.Kick kick(JRGroupParam.Kick param) {
        JRGroupResult.Kick result = new JRGroupResult.Kick();
        return result;
    }

    public JRGroupResult.ModifyChairman modifyChairman(JRGroupParam.ModifyChairman param) {
        JRGroupResult.ModifyChairman result = new JRGroupResult.ModifyChairman();
        return result;
    }

    public JRGroupResult.SubscribeList subscribeGroupList() {
        JRGroupResult.SubscribeList result = new JRGroupResult.SubscribeList();
        return result;
    }

    public JRGroupResult.SubscribeInfo subscribeGroupInfo(JRGroupParam.SubscribeInfo param) {
        JRGroupResult.SubscribeInfo result = new JRGroupResult.SubscribeInfo();
        return result;
    }

    public JRGroupResult.Rejoin rejoinGroup(JRGroupParam.Rejoin param) {
        JRGroupResult.Rejoin result = new JRGroupResult.Rejoin();
        return result;
    }


    private static String formatNumber(String number) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (; i < number.length(); ++i) {
            char c = number.charAt(i);
            if (contactsIsDialable(c)) {
                sb.append(c);
            }
        }
        return MtcUri.Mtc_UriFormatX(sb.toString(), false);
    }

    private static boolean contactsIsDialable(char c) {
        return c == '+' || c == '*' || c == '#' || c == ',' || (c >= '0' && c <= '9');
    }
}
