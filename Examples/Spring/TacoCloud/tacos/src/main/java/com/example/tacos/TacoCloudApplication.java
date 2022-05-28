package com.example.tacos;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class TacoCloudApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(TacoCloudApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		log.info(Arrays.asList(args).toString());
		
	}
}
