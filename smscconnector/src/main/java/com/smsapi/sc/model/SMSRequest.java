package com.smsapi.sc.model;

import lombok.Data;

@Data
public class SMSRequest {

	private String userid;
	
	private String ackid;
	
	private String carrierackid;
	
	
	private String smsclass;
	
	private String dltid;
	
	private String mobilenumber;	
	
	private String sms;
	
	private String udh;
	
}
