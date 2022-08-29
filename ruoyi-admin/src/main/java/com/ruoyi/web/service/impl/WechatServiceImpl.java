package com.ruoyi.web.service.impl;

import com.ruoyi.web.entity.WechatSession;
import com.ruoyi.web.service.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class WechatServiceImpl implements WechatService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Value("${wechat.app.id}")
    private String appId;
    @Value("${wechat.app.secret}")
    private String appSecret;
    @Value("${wechat.api.jscode2session}")
    private String jscode2sessionUrl;

    @Resource
    private RestTemplate restTemplate;

    /**
     *
     * @param jsCode wx.login()获取
     *
     * @response
     * session_key    string 	会话密钥
     * unionid 	string 	用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
     * errmsg 	string 	错误信息
     * openid 	string 	用户唯一标识
     * errcode 	int32 	错误码
     */
    @Override
    public WechatSession jscode2session(String jsCode){
        WechatSession wechatSession = null;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<WechatSession> responseEntity = restTemplate.exchange(jscode2sessionUrl, HttpMethod.GET, entity, WechatSession.class, appId, appSecret, jsCode);
        if(responseEntity.hasBody()){
            wechatSession = responseEntity.getBody();
        }
        return wechatSession;
    }


}
