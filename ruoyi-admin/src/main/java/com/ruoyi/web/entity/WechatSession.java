package com.ruoyi.web.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WechatSession {

    private String openid;      //用户唯一标识
    private String session_key;     //会话密钥
    private String unionid;         //用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
    private int errcode;        //错误码
    private String errmsg;      //错误信息

}
