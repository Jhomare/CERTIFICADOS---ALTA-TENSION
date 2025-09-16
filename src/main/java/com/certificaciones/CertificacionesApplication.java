package com.certificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CertificacionesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CertificacionesApplication.class, args);
    }
}