package com.setquestions;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan
public class Application {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		//SetQuestionsService questionsService = applicationContext.getBean(SetQuestionsService.class);
		//questionsService.createHelloWorld();
	}

}
