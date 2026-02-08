package com.flowdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.flowdesk.auth.repo")
public class FlowDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowDeskApplication.class, args);
	}

}
