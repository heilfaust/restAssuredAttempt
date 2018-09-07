package com.SIS.test.common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class AuthHeadersFiller {

    public static RequestSpecification SetHeaders(){

        RequestSpecification request = RestAssured.given();
        ValidLoginDo loginObj = new ValidLoginDo();

        request.header("access_token", loginObj.getAccessToken());
        request.header("client_group",loginObj.getClientGroup());
        request.header("expires_in",loginObj.getExpiresIn());
        request.header("refresh_token",loginObj.getRefreshToken());
        request.header("signature",loginObj.getSignature());
        request.header("token_type",loginObj.getTokenType());
        request.header("user_id",loginObj.getUser());

        return request;
    }

}
