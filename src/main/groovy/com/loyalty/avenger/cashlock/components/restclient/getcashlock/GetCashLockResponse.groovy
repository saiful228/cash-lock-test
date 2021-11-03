package com.loyalty.avenger.cashlock.components.restclient.getcashlock

import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import groovy.json.JsonSlurper
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus

class GetCashLockResponse {

    Boolean cashRedemptionLocked

    HttpResponse httpResponse
    Integer statusCode
    String responseBody

    String timestamp
    String faultCode
    String message
    String path
    String error

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
        if(statusCode == HttpStatus.SC_OK) {
            parseResponse(responseBody)
        }
        else {
            parseError(responseBody)
            this.statusCode = httpResponse.getStatusLine().getStatusCode()
        }
    }

    def parseResponse(String jsonString) {
        JsonSlurper parser = new JsonSlurper()
        def result = parser.parseText(jsonString)
        this.cashRedemptionLocked = result.getAt("cashRedemptionLocked")

    }

    def parseError(String jsonString) {
        JsonSlurper parser = new JsonSlurper()
        def result = parser.parseText(jsonString)
        this.timestamp = result.getAt("timestamp")
        this.statusCode = result.getAt("status")
        this.faultCode = result.getAt("faultCode")
        if(result.getAt("message") != null){
            this.message = result.getAt("message")
        } else if(result.getAt("Message") != null){
            this.message = result.getAt("Message")
        }
        this.path = result.getAt("path")
        this.error = result.getAt("error")
    }

    String getError() {
        Map bodyMap = [
                timestamp: timestamp,
                statusCode: statusCode,
                faultCode: faultCode,
                message: message,
                path: path,
                erro: error
        ]
        TestDataUtils.mapToJson(bodyMap)
    }


}


