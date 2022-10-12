package com.redisapi.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import com.redisapi.model.MessagePublisherPool;
import com.redisapi.model.QueueModel;
import com.redisapi.model.RedisPoolModel;
import com.redisapi.model.RedisTemplatePool;


@Configuration
public class RedisAPIConfig {
	
		
	@Bean("retyrindicator")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public boolean indicator() {
		
		String model=System.getenv("model");
				
		if(model.equals("")) {
			
			return true;
		}else {
			 return false;
		}
	}
	
	@Bean("redisqueueinfo")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RedisPoolModel redisPoolModel() {
		
		System.out.println("redisPoolModel()");
		
		return RedisPoolModel.builder().indexmap(new HashMap<String,Integer>()).redisinfomap(new HashMap<String,List<QueueModel>>()).build();
	}
	
	
	
	@Bean("messagepublisherpool")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn({"redistemplatepool","retyrindicator"})
	public MessagePublisherPool messagepublisherpool(@Autowired RedisTemplatePool redistemplatepool,@Autowired @Qualifier("retyrindicator") boolean retryindicator) {

		System.out.println("messagepublisherpool()");
		return new MessagePublisherPool(redistemplatepool,retryindicator);

	}
	
	
}
