package com.loyalty.avenger.cashlock.components.restclient.getcashlock

import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpGet

class GetCashLockRequest {

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
                token    : token

        ]
        bodyMap
    }

    HttpGet getRequest(String uri) {
        String fullUri = uri
        HttpGet request = new HttpGet(fullUri)
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        request.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8")
        request.addHeader(HttpHeaders.ACCEPT, "*/*")
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        request
    }
}
