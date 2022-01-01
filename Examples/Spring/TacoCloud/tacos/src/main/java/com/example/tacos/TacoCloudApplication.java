package com.example.tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class TacoCloudApplication {

	public static void main(String[] args) {

		SpringApplication.run(TacoCloudApplication.class, args);

	}
}
