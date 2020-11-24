package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JobEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobEurekaApplication.class, args);
        System.out.println("eureka...");
    }

}
