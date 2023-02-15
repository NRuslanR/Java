package org.example.spring_aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.example.spring_aop.domain.Reducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@ImportResource("META-INF/beans.xml")
public class SpringAOPApplication implements CommandLineRunner {

	@Autowired
	private Reducer reducer;

	public static void main(String[] args) {
		SpringApplication.run(SpringAOPApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		reducer.sum(1, 2, 3, 4, 5);
		
	}


}
