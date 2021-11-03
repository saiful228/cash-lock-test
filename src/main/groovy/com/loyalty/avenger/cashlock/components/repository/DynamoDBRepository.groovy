package com.loyalty.avenger.cashlock.components.repository

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Repository

@Repository
@EnableAutoConfiguration
class DynamoDBRepository implements DynamoDBBaseRepository {

    @Value ('${spring.datasource.dynamo-db.email-verification}')
    String emailVerificationTable

    @Value ('${cloud.aws.region.static}')
    String region

}
