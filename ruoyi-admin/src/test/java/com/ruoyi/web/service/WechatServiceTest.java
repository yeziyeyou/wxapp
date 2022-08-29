package com.ruoyi.web.service;


import com.ruoyi.web.entity.WechatSession;
import com.ruoyi.web.utils.WXCore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatServiceTest {

    @Resource
    private WechatService wechatService;

    @Test
    public void login(){
        WechatSession wechatSession = wechatService.jscode2session("023JbYll2G7jw942SJol2niTyy1JbYlD");

        String sessionKey = wechatSession.getSession_key();
        System.out.println("sessionKey = " + sessionKey);
//        微信加密
        String encryptedData = "HcUQkiSswX9kAa5w5UOi3Veim/Dca+Ai9jnRwcu99bZaskRRqZgCEm1TuldATCebObsaUEsia/b3jIB4maLx4hYqB0uBM1nA5c3rWte1TmPkXrnXVSvmFURfSGSucsmQz6MIFv2erVLaq912Kdyck6/E9c0rUD/LrF94SF9HvCJEb/VKoLWP3OQc7U1cE8CqqyWxGWcnHFs7iPLOJlOB9R6wTaAxrp7vi6yddkNeb2WQAsd8UVtE4oZKbvrx+uVxu2ThTwnIdYBcJRfAG0RWIiCSPH08YnsYsxAOjFY8ZXwfJ9w+KWXzLe6vZIYsdzaE43dQuIi+O2Kpmet4Nq1sWOVrYs83LVZf9UyV5esqFbrIimF6Z106+h/cZQg52++0AQsJLhWugHEZfDOMxPcguQ==";
        String iv = "5ZU5oxdZ9SdTmEsxHFDROw==";

        String appId = "wx353ba8466e75073b";
        String decrypt = WXCore.decrypt(appId, encryptedData, sessionKey, iv);
        System.out.println("decrypt = " + decrypt);
    }
}
