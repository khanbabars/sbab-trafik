package com.sbab.dev.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

@ConfigurationProperties("app-settings")
public record AppSettings(Api api) {

    public record Api(String host,String line, String key, String journeyPatterns, String stopPoints, Integer timeOutMs, DataSize webclientDataBufferLimit) {
    }
}
