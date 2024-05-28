package com.kgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "com.kgc.dao")
@EnableDiscoveryClient
public class PharmacySaleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacySaleServerApplication.class, args);
    }

}