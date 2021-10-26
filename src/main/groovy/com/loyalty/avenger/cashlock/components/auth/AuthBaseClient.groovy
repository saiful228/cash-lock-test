package com.loyalty.avenger.cashlock.components.auth
import com.loyalty.avenger.cashlock.components.restclient.BaseHttpClient
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost

trait AuthBaseClient implements BaseHttpClient {


    AuthResponse sendRequest (AuthRequest request) {
        AuthResponse authResponse = new AuthResponse()
        authResponse.setHttpResponse(send(request))
        authResponse
    }


    HttpResponse send(AuthRequest request) {
        HttpPost postRequest = request.getPostRequest(uri)
        Logger.logHttpRequest(postRequest)
        sendBasicPostRequest(postRequest)
    }
    //org.apache.http.util.EntityUtils.toString(sendBasicPostRequest(postRequest).getEntity())

    AuthRequest getActiveCollectorAuthorizeRequest(String username, String password) {
        AuthRequest request = new AuthRequest()
        request.grantType = grantType
        request.clientId = Encryptor.decrypt(clientId)
        request.clientSecret = Encryptor.decrypt(clientSecret)
        request.userName = username
        request.password = password
        request.audience = audience
        request
    }

    AuthRequest getGhostAuthorizeRequest(String username, String password) {
        AuthRequest request = new AuthRequest()
        request.grantType = AuthGrantTypes.GHOST_COLLECTOR_GRANT.getValue()
        request.audience = AuthAudienceTypes.LOYALTY.getValue()
        request.clientId = Encryptor.decrypt(clientId)
        request.clientSecret = Encryptor.decrypt(clientSecret)
        request.userName = username
        request.password = password
        request.realm = AuthRealmTypes.GHOST_MEMBERS_REALM.getValue()
        request
    }

}
