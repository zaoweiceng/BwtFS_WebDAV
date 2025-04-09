package com.bwtfs.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    public static String getCommon(){
        return "common";
    }
    @Bean
    public CommonTest getTest(){
        return new CommonTest();
    }
}
