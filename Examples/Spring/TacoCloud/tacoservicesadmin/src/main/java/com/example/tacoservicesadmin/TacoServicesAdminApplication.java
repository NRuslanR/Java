package com.example.tacoservicesadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class TacoServicesAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoServicesAdminApplication.class, args);
	}

}
