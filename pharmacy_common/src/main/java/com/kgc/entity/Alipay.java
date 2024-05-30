package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/19/ 10:31
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@ConfigurationProperties(prefix = "alipay")
public class Alipay {
    private String gateway;
    private String appId;
    private String publicKey;
    private String privateKey;
    private String alipayPublicKey;
    private String returnUrl;
    private String notifyUrl;
}
