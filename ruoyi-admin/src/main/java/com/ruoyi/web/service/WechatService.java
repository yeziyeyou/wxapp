package com.ruoyi.web.service;

import com.ruoyi.web.entity.WechatSession;

public interface WechatService {
    WechatSession jscode2session(String jsCode);
}
