package com.truly.mall.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author truly
 * @date 2022/10/29 0:47
 * 解决druid日志报错
 */
@Configuration
public class DruidConfig {
    @PostConstruct
    public void setProperties(){
        System.setProperty("druid.mysql.usePingMethod","false");
    }
}
