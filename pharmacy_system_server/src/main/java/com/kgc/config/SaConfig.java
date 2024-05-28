package com.kgc.config;

import cn.dev33.satoken.stp.StpInterface;
import com.kgc.auth.StpInterfaceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daidai
 */
@Configuration
public class SaConfig {

    @Bean
    public StpInterface stpInterface() {
        return new StpInterfaceImpl();
    }

}
