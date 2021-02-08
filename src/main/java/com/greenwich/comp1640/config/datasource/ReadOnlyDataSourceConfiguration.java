package com.greenwich.comp1640.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = DataSourceConfig.BASE_PACKAGES,
        includeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "readOnlyEntityManagerFactory"
)
public class ReadOnlyDataSourceConfiguration {
    @Autowired
    DataSourceConfig dataSourceConfig;

    @Bean
    public HikariDataSource readDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
        dataSource.setJdbcUrl(dataSourceConfig.getReadUrl());
        dataSource.setUsername(dataSourceConfig.getUsername());
        dataSource.setPassword(dataSourceConfig.getPassword());
        dataSource.setMinimumIdle(dataSourceConfig.getHikari().getMinimumIdle());
        dataSource.setMaximumPoolSize(dataSourceConfig.getHikari().getMaximumPoolSize());
        dataSource.setIdleTimeout(dataSourceConfig.getHikari().getIdleTimeout());
        dataSource.setMaxLifetime(dataSourceConfig.getHikari().getMaxLifetime());
        dataSource.setConnectionTimeout(dataSourceConfig.getHikari().getConnectionTimeout());
        dataSource.setPoolName(dataSourceConfig.getHikari().getPoolName());
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean readOnlyEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        Map<String, String> properties = new HashMap<>();
        properties.put(AvailableSettings.SHOW_SQL, String.valueOf(dataSourceConfig.isShowSql()));
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(readDataSource());
        factoryBean.setPackagesToScan(DataSourceConfig.BASE_PACKAGES);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.getJpaPropertyMap().putAll(properties);
        return factoryBean;
    }
}
