package com.smsapi.smsclass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;


@Data
@Entity(name = "smsclass")
@Builder
public class SMSClassModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int smsclassid;
	
	private String username;
	
	private byte datacoding;
	
	private byte esmclass;
	
	private byte smscount;
	
	private byte smssequence;
	
	private String countrytype;
	
	private int dltid;
	
	private String smscid;
	
	
}
