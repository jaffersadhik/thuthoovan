package com.redisapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisPoolModel {
	
	private Map<String/*redisname*/,List<QueueModel>> redisinfomap=new HashMap<String,List<QueueModel>>();
	
	private Map<String,Integer> indexmap=new HashMap<String,Integer>();
	
	public String getQueueName(String redisname) {
		
		
		List<QueueModel> qlist=redisinfomap.get(redisname);
		
		String queuename=null;
		
		if(qlist!=null&&qlist.size()>0) {
			
			int index=0;
			
			try {
				index=indexmap.get(redisname);
			}catch(Exception e) {
			
			}
			
			if(qlist.size()>index) {
				
			}else {
				
				index=0;
			}
			
			queuename=qlist.get(index).getQueuename();
			
			index++;
			
			indexmap.put(redisname, index);

		}
		
		return queuename;
	}
	
}
