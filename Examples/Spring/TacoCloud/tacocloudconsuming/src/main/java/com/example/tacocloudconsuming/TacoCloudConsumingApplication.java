package com.example.tacocloudconsuming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
    excludeFilters = {
        @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE, 
                        value = TacoCloudApiConsumeController.class
        )
    }
)
public class TacoCloudConsumingApplication {

	public static void main(String[] args) {
		SpringApplication
				.run(TacoCloudConsumingApplication.class, args);
	}
}
