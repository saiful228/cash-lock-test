package com.loyalty.avenger.cashlock.components.config

import groovy.Security
import groovy.SecurityService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityServiceClient {

    @Value ('${loyalty.security-service-soap.protocol}')
    String protocol

    @Value ('${loyalty.security-service-soap.host}')
    String host

    @Value ('${loyalty.security-service-soap.path}')
    String path

    @Bean
    Security securityService() {
        new SecurityService(new URL(protocol, host, path)).getSecurity()
    }
}
