package com.example.k8s.springbootmongo.springbootk8smongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootK8sMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootK8sMongoApplication.class, args);
	}

}
