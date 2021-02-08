package com.greenwich.comp1640.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JWTVerifierConfigProperties {
    private String privateKey = "";
    private String publicKey = "";
}
