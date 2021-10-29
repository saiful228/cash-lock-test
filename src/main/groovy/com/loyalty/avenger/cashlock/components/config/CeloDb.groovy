package com.loyalty.avenger.cashlock.components.config

import com.loyalty.avenger.cashlock.components.util.Encryptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.jdbc.core.JdbcTemplate

import javax.sql.DataSource

@Configuration
class CeloDb {

    @Lazy
    @Bean(name = "jdbcCelo")
    JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(celoDataSource())
    }

    @Value('${spring.datasource.celo.url}') String url
    @Value('${spring.datasource.celo.username}') String username
    @Value('${spring.datasource.celo.password}') String password

    @Bean(name = "celo")
    @ConfigurationProperties(prefix = "datasource.celo")
    DataSource celoDataSource() {
        return DataSourceBuilder.create()
                .username(Encryptor.decrypt(username))
                .password(Encryptor.decrypt(password))
                .url(url)
                .driverClassName('com.mysql.cj.jdbc.Driver')
                .build()
    }
}
