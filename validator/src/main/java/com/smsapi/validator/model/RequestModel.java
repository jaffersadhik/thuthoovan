package com.smsapi.validator.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.google.gson.Gson;
import com.smsapi.validator.util.MessageStatus;
import com.smsapi.validator.util.StatusCode;
import com.smsapi.validator.validation.Activation;
import com.smsapi.validator.validation.Balance;
import com.smsapi.validator.validation.Credential;
import com.smsapi.validator.validation.RequestID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Credential
@RequestID
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel implements Serializable {
	
	private static final long serialVersionUID = 4L;

	private String requestID;
	
	@Activation
	@Balance
	private String username;
	
	private String password;
	
	private Date rdate=new Date();
	
	@NotBlank(message= "fullmessage not blank")
	@Size(min = 1, max = 1000, message= "password must be between 1 and 1000 characters")
	private String fullmessage;
	
	@Digits(fraction = 0, integer = 15,message="mobile must be digit")
	@NotBlank(message= "mobile not blank")
	@Size(min = 10, max = 21, message= "mobile must be between 10 and 21 characters")
	private String mobile;
	
	@NotBlank(message= "senderid not blank")
	@Size(min = 4, max = 21, message= "senderid must be between 4 and 21 characters")
	private String dltid;
	
	
	private String statuscode=StatusCode.QUEUING_ERROR;
	
	public String toString() {
		
		Gson gson=new Gson();
		
		return gson.toJson(this, RequestModel.class);
	}

	@SuppressWarnings("deprecation")
	public String getResponse() {
		
		Gson gson=new Gson();
		
		return gson.toJson(ResponseModel.builder().requestid(this.requestID).statuscode(this.statuscode).statusdescription(MessageStatus.getInstance().getStatusDescription(this.statuscode)).time(rdate.toLocaleString()).build(), ResponseModel.class);
	}
	
	
	public String getJSON() {
		
    	Gson gson =new Gson();

    	return gson.toJson(this, RequestModel.class);
	}
	
}
