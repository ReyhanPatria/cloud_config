package com.example.filtertesting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.filtertesting.config.MaintenanceConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class FilterTestingApplication {
	@Autowired
	private MaintenanceConfig maintenanceConfig;

	public static void main(String[] args) {
		SpringApplication.run(FilterTestingApplication.class, args);
	}

	@GetMapping({"/", "url-in-config"})
	public Collection<String> getHome() {
		return maintenanceConfig.getUrls();
	}

	@GetMapping("/not-in-maintenance")
	public String notInMaintenance() {
		return "Not in maintenance";
	}

	@GetMapping("/not-in-maintenance/in-maintenance")
	public String inMaintenance() {
		return "Should be redirected";
	}
	
	@GetMapping("/maintenance")
	public Map<String, String> getMaintenance() {
		HashMap<String, String> maintenanceSchema = new HashMap<>();
		maintenanceSchema.put("code", maintenanceConfig.getCode());
		maintenanceSchema.put("message", maintenanceConfig.getMessage());
		return maintenanceSchema;
	}
}
