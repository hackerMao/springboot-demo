package com.hacker.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "duo")
@PropertySource(value = "classpath:config/exception-code.properties")
public class ExceptionCodeConfiguration {

    Map<Integer, String> codes = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }

    public String getMessage(Integer code) {
        return codes.get(code);
    }
}
