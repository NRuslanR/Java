package tat.examples.logging;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(LoggingApplication.class);

	public static void main(String[] args) {
		
		var app = new SpringApplication(LoggingApplication.class);
		
		app.setDefaultProperties(Collections.singletonMap("server.port", "8099"));

		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		final var greeting = "STARTED !";

		logger.info(greeting);
		logger.error(greeting);
		logger.warn(greeting);
		logger.trace(greeting);
		logger.debug(greeting);

	}

}
