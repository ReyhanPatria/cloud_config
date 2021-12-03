package com.example.filtertesting.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "maintenance")
@Setter @Getter
@RefreshScope
public class MaintenanceConfig {
    private List<MaintenanceEndpoint> endpoints;   
}