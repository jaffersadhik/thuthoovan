package com.smsapi.redisqmonitor.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.redisapi.model.MainModuleQueueCount;
import com.redisapi.model.QueueModel;
import com.redisapi.model.QueuePoolModel;
import com.redisapi.model.RedisPoolModel;
import com.redisapi.model.SubModuleQueueCount;

@Configurable
public class RedisQMonitorConfig {
	
	
	@Bean("queuepoolmodelmaster")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public QueuePoolModel queuepoolmodel() {

		System.out.println("queuepoolmodel()");
		
		return QueuePoolModel.builder().queueinfomap(new HashMap<String,List<QueueModel>>()).build();

	}

	@Bean("redispoolmodelmaster")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public RedisPoolModel redispoolmodel() {

		System.out.println("redispoolmodelmaster()");
		
		return RedisPoolModel.builder().redisinfomap(new HashMap<String,List<QueueModel>>()).build();

	}

	
	@Bean("submodulequeue")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public SubModuleQueueCount submodulequeue() {

		System.out.println("submodulequeue()");
		
		return  SubModuleQueueCount.builder().submodulemap(new HashMap<String,Map<String,Integer>>()).build();

	}
	
	
	@Bean("mainmodulequeue")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public MainModuleQueueCount mainmodulequeue() {

		System.out.println("mainmodulequeue()");
		
		return  MainModuleQueueCount.builder().mainmodulemap(new HashMap<String,Map<String,Integer>>()).build();

	}
}
