package com.example.filtertesting.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class MaintenanceEndpoint {
    private String code;
    private String message;
    private List<String> urls;
}