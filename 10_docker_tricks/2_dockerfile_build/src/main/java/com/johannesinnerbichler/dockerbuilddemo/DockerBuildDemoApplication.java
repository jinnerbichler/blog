package com.johannesinnerbichler.dockerbuilddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerBuildDemoApplication {

	public static void main(String[] args) {
	    System.out.println("It's working. Starting Spring Application...");
		SpringApplication.run(DockerBuildDemoApplication.class, args);
	}
}
