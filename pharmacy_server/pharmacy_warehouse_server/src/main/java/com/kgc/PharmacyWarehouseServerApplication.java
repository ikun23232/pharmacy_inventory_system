package com.kgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("com.kgc.dao")
@EnableDiscoveryClient
public class PharmacyWarehouseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacyWarehouseServerApplication.class, args);
    }

}
