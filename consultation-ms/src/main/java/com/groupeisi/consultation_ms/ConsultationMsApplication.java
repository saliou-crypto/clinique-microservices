package com.groupeisi.consultation_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsultationMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsultationMsApplication.class, args);
    }
}