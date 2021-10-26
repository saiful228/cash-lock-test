package com.loyalty.avenger.cashlock.components.restclient

import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import groovy.json.JsonSlurper
import org.apache.http.HttpResponse


class GetCashLockResponse {

    Boolean cashRedemptionLocked


    HttpResponse httpResponse
    Integer statusCode
    String responseBody


    @Override
    String toString() {
        toJson()
    }

    String toJson() {
        Map bodyMap = [
                cashRedemptionLocked: cashRedemptionLocked,
                statusCode: statusCode
        ]
        TestDataUtils.mapToJson(bodyMap)
    }

    def setHttpResponse(HttpResponse response) {
        this.httpResponse = response
        this.responseBody = TestDataUtils.getResponseBody(httpResponse)
        this.statusCode = httpResponse.getStatusLine().getStatusCode()
        parseRequest(responseBody)

        }
    def parseRequest(String jsonString) {
        JsonSlurper parser = new JsonSlurper()
        def result = parser.parseText(jsonString)
        cashRedemptionLocked = result.getAt("cashRedemptionLocked") ?: true

    }

}
