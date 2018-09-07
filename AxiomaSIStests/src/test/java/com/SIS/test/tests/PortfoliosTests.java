package com.SIS.test.tests;

import com.SIS.test.common.AuthHeadersFiller;
import com.SIS.test.common.EndPoints;
import com.SIS.test.common.GeneratePortfolioName;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class PortfoliosTests {
    String PortfolioId;
    //private AuthHeadersFiller httpRequest;
    GeneratePortfolioName PortfolioName = new GeneratePortfolioName();
    RequestSpecification httpRequest = AuthHeadersFiller.SetHeaders();

   // @Test
    public void VerifyGETPortfolios_test(){

        Response response = httpRequest.get(EndPoints.PORTFOLIOS+"?$top=50");

        response.then().statusCode(200).log().all();
    }

 //        @Test
    public void VerifyCreateNewPortfolio_test(){

        httpRequest.header("Content-Type", "application/json");

        HashMap<String, String> defaultRiskModelMap = new HashMap<String, String>();
        defaultRiskModelMap.put("type","Axioma");
        defaultRiskModelMap.put("name","WW21AxiomaSH-S");

        JSONObject requestParams = new JSONObject();
        requestParams.put("name",PortfolioName.genName());
        requestParams.put("longName",PortfolioName.genName());
        requestParams.put("description",PortfolioName.genName());
        requestParams.put("defaultCurrency","USD");
        requestParams.put("defaultDataPartition","Axioma");
        requestParams.put("riskDataSource","Default");
        requestParams.put("defaultRiskModel",defaultRiskModelMap);

        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post(EndPoints.PORTFOLIOS);

        response.then().statusCode(200).log().all();

        String PortfolioPath = response.then().extract().path("headers.location");
        System.out.println(PortfolioPath);
        PortfolioId = PortfolioPath.substring(PortfolioPath.lastIndexOf("/ ") + 20);

    }
}