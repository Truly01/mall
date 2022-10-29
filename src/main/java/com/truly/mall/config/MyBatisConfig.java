package com.truly.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author truly
 * @date 2022/10/21 21:53
 */
@Configuration
@MapperScan(basePackages = {"com.truly.mall.mbg.mapper","com.truly.mall.dao"})
public class MyBatisConfig {
}
