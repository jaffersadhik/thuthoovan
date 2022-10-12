package com.smsapi.dlt.service;

import static io.restassured.RestAssured.given;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.smsapi.dlt.model.DLTModel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Service
public class DLTService {

	public DLTModel getDLTModel(int dltid) {
		
		
		DLTModel dltrequest=DLTModel.builder().dltid(dltid).build();
		
		boolean retry=true;
		while(retry) {
			
				
			try {
    	Gson gson =new Gson();
        Response response = given()
        					.baseUri("http://masterapilb:8080")
        					.contentType(ContentType.JSON)
        					.body(gson.toJson(dltrequest, DLTModel.class))
                            .when()
                            .post("/sms-api/dlt/getdltbyid");

        if(response.getStatusCode()==200) {
        	
        	retry=false;
        	
        	
        	return gson.fromJson(response.then().extract().asString(), DLTModel.class);
        }
				}catch(Exception e) {
				
					gotosleep();
			}
		}
                            
        
        
        return null;
	
	
	}
	
	
	public DLTModel getDLTModel(String senderid,String entityid,String templateid) {
		
		
		DLTModel dltrequest=DLTModel.builder().senderid(senderid).templateid(templateid).entityid(entityid).build();
		
		boolean retry=true;
		while(retry) {
			
				
			try {
    	Gson gson =new Gson();
        Response response = given()
        					.baseUri("http://masterapilb:8080")
        					.contentType(ContentType.JSON)
        					.body(gson.toJson(dltrequest, DLTModel.class))
                            .when()
                            .post("/sms-api/dlt/getdltbykey");

        if(response.getStatusCode()==200) {
        	
        	retry=false;
        	
        	
        	return gson.fromJson(response.then().extract().asString(), DLTModel.class);
        }
				}catch(Exception e) {
				
					gotosleep();
			}
		}
                            
        
        
        return null;
	
	
	}
	
	private void gotosleep() {
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
