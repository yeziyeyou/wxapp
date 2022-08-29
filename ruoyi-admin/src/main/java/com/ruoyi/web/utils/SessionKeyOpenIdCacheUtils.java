package com.ruoyi.web.utils;

import com.ruoyi.web.entity.WechatSession;

import java.util.HashMap;
import java.util.Map;

public class SessionKeyOpenIdCacheUtils {

    private static Map<String, WechatSession> map = new HashMap<>();

    public static void putSession(String key, WechatSession session){
        map.put(key, session);
    }
    public static WechatSession getSession(String key){
        return map.get(key);
    }
}
