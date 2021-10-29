package com.loyalty.avenger.cashlock.components.auth

import com.loyalty.avenger.cashlock.components.constant.GrantType
import com.loyalty.avenger.cashlock.components.restclient.BaseHttpClient
import com.loyalty.avenger.cashlock.components.util.Encryptor
import com.loyalty.avenger.cashlock.components.util.Logger
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GetTokenClient implements BaseHttpClient {

    @Value ('${loyalty.auth-api.uri}')
    String uriConfig

    @Value ('${loyalty.auth-api.client_id}')
    String clientIdConfig

    @Value ('${loyalty.auth-api.client_secret}')
    String clientSecretConfig

    @Value ('${loyalty.auth-api.audience}')
    String clientAudienceConfig



    AuthResponse sendRequest(AuthRequest request) {
        AuthResponse authResponse = new AuthResponse()
        authResponse.setHttpResponse(send(request))
        authResponse
    }

    AuthResponse getAccessToken() {
        AuthRequest request = new AuthRequest()
        request.clientId = Encryptor.decrypt(clientIdConfig)
        request.clientSecret = Encryptor.decrypt(clientSecretConfig)
        request.audience = clientAudienceConfig
        request.grantType = GrantType.CLIENT_CREDENTIALS.getValue()
        sendRequest(request)
    }



    HttpResponse send(AuthRequest request) {
        HttpPost postRequest = request.getPostRequest(uriConfig)
        Logger.logHttpRequest(postRequest)
        sendBasicPostRequest(postRequest)
    }
}
