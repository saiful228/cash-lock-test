package com.loyalty.avenger.cashlock.components.auth

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component



@Slf4j
@Lazy
@Component
@EnableAutoConfiguration
class AuthClient implements AuthBaseClient {

    @Value ('${loyalty.auth-api.uri}')
    String uri

    @Value ('${loyalty.auth-api.client_id}')
    String clientId

    @Value ('${loyalty.auth-api.client_secret}')
    String clientSecret

    @Value ('${loyalty.auth-api.grant_type}')
    String grantType

    @Value ('${loyalty.auth-api.audience}')
    String audience
}
