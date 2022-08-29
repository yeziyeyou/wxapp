package com.ruoyi.web.controller.wechat;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.web.entity.WechatSession;
import com.ruoyi.web.service.WechatService;
import com.ruoyi.web.utils.SessionKeyOpenIdCacheUtils;
import com.ruoyi.web.utils.WXCore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private WechatService wechatService;

    @RequestMapping("/test2/login")
    public String login(String encryptedData, String iv, String jsCode){

        WechatSession wechatSession = wechatService.jscode2session(jsCode);

        String sessionKey = wechatSession.getSession_key();
        System.out.println("sessionKey = " + sessionKey);
//        微信加密
        String appId = "wx353ba8466e75073b";
        String decrypt = WXCore.decrypt(appId, encryptedData, sessionKey, iv);
        System.out.println("decrypt = " + decrypt);
        return decrypt;
    }

    @ResponseBody
    @RequestMapping("/test2/login2")
    public String login2(String jsCode){
        System.out.println("------------------login2----------------------- = ");
        WechatSession wechatSession = wechatService.jscode2session(jsCode);
        String id = UUID.randomUUID().toString().replace("-", "");
        SessionKeyOpenIdCacheUtils.putSession(id, wechatSession);
        return id;
    }

    @ResponseBody
    @RequestMapping("/test2/login3")
    public String login2(String encryptedData, String iv, String key){
        System.out.println("------------------login3----------------------- = ");
        WechatSession wechatSession = SessionKeyOpenIdCacheUtils.getSession(key);

        String sessionKey = wechatSession.getSession_key();
        System.out.println("sessionKey = " + sessionKey);
//        微信加密
        String appId = "wx353ba8466e75073b";
        String decrypt = WXCore.decrypt(appId, encryptedData, sessionKey, iv);
        System.out.println("decrypt = " + decrypt);
        return decrypt;
    }
}
