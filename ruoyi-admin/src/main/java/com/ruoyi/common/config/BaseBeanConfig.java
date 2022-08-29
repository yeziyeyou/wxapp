package com.ruoyi.common.config;

import com.ruoyi.common.converter.CustomJacksonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

@Configuration
public class BaseBeanConfig {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        // 替换默认的 Jackson 消息转换器实现
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> next = iterator.next();
            if (next instanceof MappingJackson2HttpMessageConverter) {
                iterator.remove();
                break;
            }
        }
        messageConverters.add(new CustomJacksonHttpMessageConverter());
        return restTemplate;
    }
}
