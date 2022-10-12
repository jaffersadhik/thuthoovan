package com.smsapi.dlt.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.smsapi.dlt.model.DLTMemory;
import com.smsapi.dlt.model.DLTModel;

@Configurable
public class DLTConfig {
	
	
	@Bean("dltconfig")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public DLTMemory dltconfig() {

		System.out.println("dltconfig()");
		
		return DLTMemory.builder().dltmemorybyid(new HashMap<Integer,DLTModel>()).dltmemorybykey(new HashMap<String,DLTModel>()).build();

	}


}
