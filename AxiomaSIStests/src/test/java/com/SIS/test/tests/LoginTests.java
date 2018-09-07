package com.SIS.test.tests;

import com.SIS.test.common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void VerifyLoginValidUser_test(){
        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();
        requestParams.put("username","hx_arolon_user_2018824_000");
        requestParams.put("password","Cochola+3");
        requestParams.put("grant_type","password");
        requestParams.put("client_id","706FD03F4E3544479076C231E12BB963");

        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post(EndPoints.CONNECT);

        response.then().statusCode(200).log().all();
        String accessToken = response.then().extract().path("accessToken");
        System.out.println("User successfully logged in with access token "+accessToken);
    }

    @Test
    public void VerifyLoginInvalidUser_test(){
        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();
        requestParams.put("username","hx_arolon_user_2018824_000_invalid");
        requestParams.put("password","Chocolate_12");
        requestParams.put("grant_type","password");
        requestParams.put("client_id","706FD03F4E3544479076C231E12BB963");

        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post(EndPoints.CONNECT);

        response.then().statusCode(500).log().all();
        String responseError = response.then().extract().path("Error.name");
        Assert.assertEquals(responseError, "INVALID_CREDENTIALS");

        System.out.println("User login failed, reason: "+responseError);
    }


}
