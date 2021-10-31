package com.loyalty.avenger.cashlock.components.restclient
import org.apache.http.HttpRequest
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.impl.client.HttpClientBuilder

trait BaseHttpClient {

    final static int RETRY_TIMEOUT_MILLISECONDS = 1000

    String uri
    int port
    String path

    HttpResponse httpResponse
    HttpRequest httpRequest

    final static int NUMBER_OF_RETRIES = 10
    final static int TIMEOUT = 1000

    HttpResponse sendBasicGetRequest(HttpRequest request) {
        this.httpRequest = request
        HttpClient client = HttpClientBuilder.create()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build()
        httpResponse = client.execute(httpRequest)
        httpResponse
    }

    HttpResponse sendBasicPostRequest(HttpPost request){
        HttpClient client = HttpClientBuilder.create()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build()
        httpResponse = client.execute(request)
        httpResponse
    }

    HttpResponse sendBasicHttpRequest(HttpGet request){
        HttpClient client = HttpClientBuilder.create()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build()
        httpResponse = client.execute(request)
        httpResponse
    }

    HttpResponse sendBasicPutRequest(HttpPut request){
        HttpClient client = HttpClientBuilder.create()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build()
        httpResponse = client.execute(request)
        httpResponse
    }
}

