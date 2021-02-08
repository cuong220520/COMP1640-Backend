package com.greenwich.comp1640.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = DataSourceConfig.BASE_PACKAGES,
        excludeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "primaryEntityManagerFactory"
)
public class PrimaryDataSourceConfiguration {
    @Autowired
    DataSourceConfig dataSourceConfig;

    @Bean
    @Primary
    public HikariDataSource primaryDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
        hikariDataSource.setJdbcUrl(dataSourceConfig.getUrl());
        hikariDataSource.setUsername(dataSourceConfig.getUsername());
        hikariDataSource.setPassword(dataSourceConfig.getPassword());
        hikariDataSource.setMinimumIdle(dataSourceConfig.getHikari().getMinimumIdle());
        hikariDataSource.setMaximumPoolSize(dataSourceConfig.getHikari().getMaximumPoolSize());
        hikariDataSource.setIdleTimeout(dataSourceConfig.getHikari().getIdleTimeout());
        hikariDataSource.setMaxLifetime(dataSourceConfig.getHikari().getMaxLifetime());
        hikariDataSource.setConnectionTimeout(dataSourceConfig.getHikari().getConnectionTimeout());
        hikariDataSource.setPoolName(dataSourceConfig.getHikari().getPoolName());
        return hikariDataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        Map<String, String> properties = new HashMap<>();
        properties.put(AvailableSettings.SHOW_SQL, String.valueOf(dataSourceConfig.isShowSql()));
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(primaryDataSource());
        factoryBean.setPackagesToScan(DataSourceConfig.BASE_PACKAGES);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.getJpaPropertyMap().putAll(properties);
        return factoryBean;
    }
}
