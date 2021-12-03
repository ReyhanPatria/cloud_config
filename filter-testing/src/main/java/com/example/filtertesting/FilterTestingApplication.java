package com.example.filtertesting;

import com.example.filtertesting.config.MaintenanceConfig;
import com.example.filtertesting.config.MaintenanceEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class FilterTestingApplication {
	@Autowired
	private MaintenanceConfig maintenanceConfig;

	public static void main(String[] args) {
		SpringApplication.run(FilterTestingApplication.class, args);
	}

	@GetMapping({"/", "url-in-config"})
	public List<MaintenanceEndpoint> getHome() {
		return maintenanceConfig.getEndpoints();
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
	public Map<String, Object> getMaintenance(HttpServletRequest request) {
		HashMap<String, Object> maintenanceSchema = new HashMap<>();
		maintenanceSchema.put("code", request.getAttribute("maintenance-code"));
		maintenanceSchema.put("message", request.getAttribute("maintenance-message"));
		return maintenanceSchema;
	}
}
