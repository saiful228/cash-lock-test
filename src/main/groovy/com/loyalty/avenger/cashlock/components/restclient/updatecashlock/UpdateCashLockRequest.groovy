package com.loyalty.avenger.cashlock.components.restclient.updatecashlock

import org.apache.http.HttpEntity
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.entity.ByteArrayEntity
import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import org.apache.http.entity.StringEntity

class UpdateCashLockRequest {

   String cashRedemptionLocked
    String token

    @Override
    String toString() {
        toJson()
    }

    String toJson() {
        TestDataUtils.mapToJson(getBodyMap())
    }

    Map getBodyMap () {
        Map bodyMap = [
                cashRedemptionLocked: cashRedemptionLocked
        ]
        bodyMap
    }

    HttpPut sendPutRequest(String uri, String token) {
        String fullUri = uri
        StringEntity payload = new StringEntity(toString())
        HttpPut request = new HttpPut(fullUri)
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        request.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8")
        request.addHeader(HttpHeaders.ACCEPT, "*/*")
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        request.setHeader("Channel-Code", "WEB")
        request.setEntity(payload)
        request
    }
}
