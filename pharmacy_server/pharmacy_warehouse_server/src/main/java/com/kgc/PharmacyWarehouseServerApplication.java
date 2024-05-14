package com.kgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kgc.dao")
public class PharmacyWarehouseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacyWarehouseServerApplication.class, args);
    }

}
