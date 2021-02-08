package com.greenwich.comp1640.config.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Validated
public class DataSourceConfig {
    public static final String BASE_PACKAGES = "com.greenwich.comp1640";
    @NotEmpty(message = "Missing database connection username")
    private String username;
    @NotEmpty(message = "Missing database connection password")
    private String password;
    @NotEmpty(message = "Missing database connection driver class name")
    private String driverClassName;
    @NotEmpty(message = "Missing database connection url")
    private String url;
    @NotEmpty(message = "Missing database connection read url")
    private String readUrl;
    private boolean showSql;
    @NotNull(message = "Missing hikari config")
    private HikariConfig hikari;

    @Getter
    @Setter
    public static class HikariConfig {
        private Integer minimumIdle = 5;
        private Integer maximumPoolSize = 500;
        private Integer idleTimeout = 30000;
        private Integer maxLifetime = 2000000;
        private Integer connectionTimeout = 30000;
        private String poolName = "HikariPoolPaymentGateway";
    }
}
