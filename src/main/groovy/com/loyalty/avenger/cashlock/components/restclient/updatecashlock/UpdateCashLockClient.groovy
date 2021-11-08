package com.loyalty.avenger.cashlock.components.restclient.updatecashlock
import com.loyalty.avenger.cashlock.components.util.Logger
import com.loyalty.avenger.cashlock.components.restclient.BaseHttpClient
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class UpdateCashLockClient implements BaseHttpClient{

    @Value ('${loyalty.cashlock-api.uri}')
    String uriConfig

    @Value ('${loyalty.cashlock-api.put_request}')
    String pathConfig

    String token



    UpdateCashLockResponse sendUpdateCashLockRequest(String cashRedemptionLocked, String token) {
        UpdateCashLockRequest request = new UpdateCashLockRequest(cashRedemptionLocked : cashRedemptionLocked)
        this.token = token
        //this.token = "Bearer "+token
        println("ssssssss" + token)
        Logger.logMessage("Access token: $token")
        sendRequest(request)
    }

    UpdateCashLockResponse sendRequest(UpdateCashLockRequest request) {
        UpdateCashLockResponse response = new UpdateCashLockResponse()
        response.setHttpResponse(send(request))
        println(request.toString())
        response
    }

    HttpResponse send(UpdateCashLockRequest request) {
        String fullUri = uriConfig + pathConfig
        HttpPut httpRequest = request.sendPutRequest(fullUri, token)
        Logger.logHttpRequest(httpRequest)
        sendBasicPutRequest(httpRequest)
    }


}


