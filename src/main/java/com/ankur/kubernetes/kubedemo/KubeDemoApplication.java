package com.ankur.kubernetes.kubedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KubeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubeDemoApplication.class, args);
	}

}
