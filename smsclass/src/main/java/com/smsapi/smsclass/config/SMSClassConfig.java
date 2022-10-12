package com.smsapi.smsclass.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.smsapi.smsclass.model.SMSClassMemory;
import com.smsapi.smsclass.model.SMSClassModel;

@Configurable
public class SMSClassConfig {

	@Bean("smsclassconfig")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public SMSClassMemory smsclassconfig() {

		System.out.println("smsclassconfig()");
		
		return SMSClassMemory.builder().smsclassmemorybyid(new HashMap<Integer,SMSClassModel>()).smsclassmemorybykey(new HashMap<String,SMSClassModel>()).build();

	}

}
