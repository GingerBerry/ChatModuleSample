package com.juphoon.jrsdk;

/**
 * 如果Mtc层和JR层的枚举分开则过于复杂，所以公开Mtc层枚举供UI使用
 */
public class JRAccountDefine {
    public enum ConfigKey {
        //帐号
        UserName,
        Password,
        AuthName,
        RegIp,
        RegRealm,
        SipTransportType,
        SipPort,
        SipInstanceEnable,
        LogLevel,//日志打印等级
        //注册
        RegType,//注册类型
        RegNoDigest,//注册无鉴权头
        RegExpireTime,//注册刷新时间
        OpenSubs,//开启注册订阅
        SubsExpireTime,//订阅刷新时间
        SipDscpValue,//sip dscp值
        TlsType,//tls验证类型
        //heartbeat
        PsHeartBeat,//心跳类型
        WifiHeartBeat,//数据网络心跳间隔时长
        Heartbeat,//WiFi心跳间隔时长
        //高级配置
        SessType,//定时器类型
        SessLen,//定时器时长
        SessMinLen,//最小定时器时长(s)
        UseTelUri,//使用 Tel URI
        //音频编解码
        AudioCode,//音频编解码
        AudioDscpValue,//音频dscp
        AudioBitrate,//码率
        AudioPtime,//打包时长（ms）
        //DTMF
        DtmfType,//DTMF类型
        DtmfPayload,//DTMF Payload （96-127）
        DtmfNotify,//DTMF通知
        //增益控制
        SendAgcMode,//发送端增益模式
        SendAgcEnable,//发送端增益
        SendAgc,//发送端增益（0-30）
        RecvAgcEnable,//接收端增益
        RecvAgc,//接收端增益（0-30）
        RecvAgcMode,//接收端增益模式
        //噪音消除
        SendAnr,//发送端降噪
        RecvAnr,//接收端降噪
        SendAnrMode,//发送端降噪等级
        RecvAnrMode,//接收端降噪等级
        //抗抖动
        BufferMinDelay,//抗抖动缓存最小延迟
        BufferMaxPacket,//抗抖动缓存最大包数
        //音频Qos配置
        Vad,//非连续传输
        VadMode,//非连续传输模式
        Aec,//回音消除
        AecMode,//回音消除模式
        Fec,//音频冗余
        //视频编解码
        VideoCode,//视频编解码
        VideoDscpValue,//视频dscp
        H264PacketMode,//H264 打包模式
        H264Payload,//H264 Payload值
        VideoWAndH,//分辨率Width
        //码率配置
        BitrateValue,//码率（kbps）
        BitrateControl,//自适应码率控制
        //分辨率配置
        ResolutionControl,//自适应分辨率
        //帧率配置
        FramerateMax,//最大帧速率(fps)(1~30)
        VideoFramerateControl,//自适应帧速率
        VideoFramerate,//视频证速率
        VideoArs,//视频动态码率调整
        //视频QoS配置
        KeyPeroid,//关键帧间隔(s)(0~600)
        Fir,//关键帧请求
        FirByInfo,//关键帧请求方式
        Rpsi,//可靠引用帧
        VideoFec,//视频冗余
        VoiceFec,//音频冗余
        Nack,//丢包重传
        Rtx,//重传.冗余格式支持
        Bem,//带宽高效模式
        //媒体传输配置
        SrtpType,//SRTP类型加密
        AudioRtcpmux,//音频RTP/RTCP端口复用
        VideoRtcpmux,//视频RTP/RTCP端口复用
        //NAT穿越配置
        NatType,//NAT穿越类型
        StunServer,//STUN服务器地址
        StunServerPort,//STUN服务器端口
    }
}
