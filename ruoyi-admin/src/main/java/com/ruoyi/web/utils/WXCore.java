package com.ruoyi.web.utils;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 封装对外访问方法
 */
public class WXCore {

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";

    /**
     * 解密数据
     *
     * @return
     * @throws Exception
     */
    public static String decrypt(String appId, String encryptedData,
                                 String sessionKey, String iv) {
        String result = "";
        try {
            WxAES aes = new WxAES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if (null != resultByte && resultByte.length > 0) {
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSON.parseObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK)
                        .getString(APPID);
                if (!appId.equals(decryptAppid)) {
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

        String appId = "wx4f4bc4dec97d474b";
        String encryptedData = "HcUQkiSswX9kAa5w5UOi3Veim/Dca+Ai9jnRwcu99bZaskRRqZgCEm1TuldATCebObsaUEsia/b3jIB4maLx4hYqB0uBM1nA5c3rWte1TmPkXrnXVSvmFURfSGSucsmQz6MIFv2erVLaq912Kdyck6/E9c0rUD/LrF94SF9HvCJEb/VKoLWP3OQc7U1cE8CqqyWxGWcnHFs7iPLOJlOB9R6wTaAxrp7vi6yddkNeb2WQAsd8UVtE4oZKbvrx+uVxu2ThTwnIdYBcJRfAG0RWIiCSPH08YnsYsxAOjFY8ZXwfJ9w+KWXzLe6vZIYsdzaE43dQuIi+O2Kpmet4Nq1sWOVrYs83LVZf9UyV5esqFbrIimF6Z106+h/cZQg52++0AQsJLhWugHEZfDOMxPcguQ==";
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        String iv = "5ZU5oxdZ9SdTmEsxHFDROw==";
        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
    }
}