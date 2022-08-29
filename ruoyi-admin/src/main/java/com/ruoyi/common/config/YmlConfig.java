package com.ruoyi.common.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class YmlConfig {
        // 加载YML格式自定义配置文件
        @Bean
        public static PropertySourcesPlaceholderConfigurer properties() {
            PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(new ClassPathResource("application-wechat.yml"));//File引入
            configurer.setProperties(yaml.getObject());
            return configurer;
        }
}
