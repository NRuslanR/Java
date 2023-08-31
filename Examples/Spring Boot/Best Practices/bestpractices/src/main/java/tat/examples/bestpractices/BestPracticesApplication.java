package tat.examples.bestpractices;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BestPracticesApplication implements ApplicationContextAware, CommandLineRunner {

	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		
		SpringApplication.run(BestPracticesApplication.class, args);

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.applicationContext = applicationContext;

	}

	@Override
	public void run(String... args) throws Exception {
		
		Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(log::info);
	
	}

}
