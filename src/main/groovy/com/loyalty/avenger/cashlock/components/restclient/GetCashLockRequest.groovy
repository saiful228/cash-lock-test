package com.loyalty.avenger.cashlock.components.restclient

import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpGet

class GetCashLockRequest {
    String token

    HttpGet getRequest(String uri) {
        HttpGet request = new HttpGet(uri)
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        request.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8")
        request.addHeader(HttpHeaders.ACCEPT, "*/*")
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+token)
        request
    }
}
