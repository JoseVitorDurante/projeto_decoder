package com.ead.notificationhexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NotificationHexagonalApplication {


    public static void main(String[] args) {
        SpringApplication.run(NotificationHexagonalApplication.class, args);
    }

}
