package com.barun.ff4j.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariDataSource {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String jdbcUserName;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Bean
    public com.zaxxer.hikari.HikariDataSource dataSource() {
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(jdbcUserName)
                .password(jdbcPassword)
                .type(com.zaxxer.hikari.HikariDataSource.class)
                .build();
    }
}
