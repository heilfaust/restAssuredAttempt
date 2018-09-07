package com.SIS.test.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class ValidLoginDo {

    private String accessToken;
    private String clientGroup;
    private Long expiresIn;
    private String refreshToken;
    private String tokenType;
    private String signature;
    private String user;

    public ValidLoginDo() {
        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "hx_arolon_user_2018824_000");
        requestParams.put("password", "Cochola+3");
        requestParams.put("grant_type", "password");
        requestParams.put("client_id", "706FD03F4E3544479076C231E12BB963");

        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post(EndPoints.CONNECT);

        this.accessToken = response.then().extract().path("accessToken");
        this.clientGroup = response.then().extract().path("clientGroup");
        this.expiresIn = response.then().extract().path("expiresIn");
        this.refreshToken = response.then().extract().path("refreshToken");
        this.tokenType = response.then().extract().path("tokenType");
        this.signature = response.then().extract().path("signature");
        this.user = response.then().extract().path("user");
    }

    public String getAccessToken() {
            return this.accessToken;
        }

    public String getClientGroup() {
            return this.clientGroup;
        }

    public Long getExpiresIn() {
            return this.expiresIn;
        }

    public String getRefreshToken() {
            return this.refreshToken;
        }

    public String getTokenType() {
            return this.tokenType;
        }

    public String getSignature() {
            return this.signature;
        }

    public String getUser() {
            return this.user;
        }

}
