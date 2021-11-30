package com.example.filtertesting.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "maintenance")
@Setter @Getter
@RefreshScope
public class MaintenanceConfig {
    private String code;
    private String message;
    private List<String> urls;
}