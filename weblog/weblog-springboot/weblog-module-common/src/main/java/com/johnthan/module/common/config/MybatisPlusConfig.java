package com.johnthan.module.common.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Mybatis Plus 配置文件
 **/
@Configuration
@MapperScan("com.johnthan.module.common.domain.mapper")
public class MybatisPlusConfig {
}
