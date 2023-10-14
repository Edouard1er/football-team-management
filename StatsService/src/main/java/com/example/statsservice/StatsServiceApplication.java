package com.example.statsservice;

import com.example.statsservice.configuration.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@RibbonClient(name = "stats-service", configuration = RibbonConfiguration.class)
public class StatsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatsServiceApplication.class, args);
	}

}
