package com.loyalty.avenger.cashlock.components.repository


import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Repository

@Repository
@EnableAutoConfiguration
class DynamoDBIntRepository implements DynamoDBBaseRepository{

    @Value ('${spring.datasource.dynamo-db.end-to-end}')
    String emailVerificationTable

    @Value ('${cloud.aws.region.static}')
    String region

}
