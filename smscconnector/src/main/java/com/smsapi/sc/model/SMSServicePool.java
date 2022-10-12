package com.smsapi.sc.model;

import java.util.List;

import com.smsapi.sc.service.SMSService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder	
@AllArgsConstructor
@NoArgsConstructor
public class SMSServicePool {
	
	private List<SMSService> smsservicelist;

}
