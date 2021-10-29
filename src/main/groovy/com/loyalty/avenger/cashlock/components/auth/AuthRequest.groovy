package com.loyalty.avenger.cashlock.components.auth

import com.loyalty.avenger.cashlock.components.constant.Channel
import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import org.apache.http.HttpHeaders
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.message.BasicNameValuePair

import java.nio.charset.StandardCharsets


class AuthRequest {

    String grantType
    String clientId
    String audience
    String userName
    String password
    String clientSecret
    String channel = Channel.WEB.getValue()
    String realm

    @Override
    String toString() {
        toJson()
    }

    String toJson() {
        TestDataUtils.mapToJson(getBodyMap())
    }


    Map getBodyMap () {
        Map bodyMap = [
                grant_type   : grantType,
                client_id    : clientId,
                audience     : audience,
                username     : userName,
                password     : password,
                client_secret: clientSecret
        ]
        if (realm!= null && realm.length() > 0) {
            bodyMap["realm"] = realm
        }
        bodyMap
    }

    HttpPost getPostRequest(String uri) {
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("grant_type", grantType))
        form.add(new BasicNameValuePair("client_id", clientId))
        form.add(new BasicNameValuePair("audience", audience))
        form.add(new BasicNameValuePair("client_secret", clientSecret))
        form.add(new BasicNameValuePair("username", userName))
        form.add(new BasicNameValuePair("password", password))
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, StandardCharsets.UTF_8)

        if (realm!= null && realm.length() > 0) {
            form.add(new BasicNameValuePair("realm", realm))
        }

        HttpPost request = new HttpPost(uri)
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
        request.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8")
        request.addHeader(HttpHeaders.ACCEPT, "*/*")
        request.addHeader("channel", channel)
        request.setEntity(entity)
        request
    }
}