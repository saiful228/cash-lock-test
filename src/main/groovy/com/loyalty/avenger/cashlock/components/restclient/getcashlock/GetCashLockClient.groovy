package com.loyalty.avenger.cashlock.components.restclient.getcashlock

import com.loyalty.avenger.cashlock.components.restclient.BaseHttpClient
import com.loyalty.avenger.cashlock.components.util.Logger
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GetCashLockClient implements BaseHttpClient {

    @Value ('${loyalty.cashlock-api.uri}')
    String uriConfig

    @Value ('${loyalty.cashlock-api.path}')
    String pathConfig

    String token

    GetCashLockResponse getCashLock(String token) {
        GetCashLockRequest request = new GetCashLockRequest()
        request.token = token
        sendRequest(request)
    }

    GetCashLockResponse sendRequest(GetCashLockRequest request) {
        GetCashLockResponse response = new GetCashLockResponse()
        response.setHttpResponse(send(request))
        response
    }

    HttpResponse send(GetCashLockRequest request) {
        String fullUri = uriConfig + pathConfig
        HttpGet httpRequest = request.getRequest(fullUri)
        Logger.logHttpRequest(httpRequest)
        sendBasicHttpRequest(httpRequest)
    }

}
