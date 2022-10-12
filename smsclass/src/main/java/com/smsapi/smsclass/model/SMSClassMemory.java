package com.smsapi.smsclass.model;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMSClassMemory {

	private Map<String/* SMSClass Key */,SMSClassModel> smsclassmemorybykey;
	
	private Map<Integer/* SMSClass ID */,SMSClassModel> smsclassmemorybyid;

}
