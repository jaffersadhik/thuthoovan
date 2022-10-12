package com.redisapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MessagePublisherPool {

	private static final String COLAN=":";
	 RedisTemplatePool redistemplatepool;
	 
	 boolean retry;

	private Map<String,Integer> index=new HashMap<String,Integer>();

	Map<String/*queuename*/,Map<String/*redisid*/,MessagePublisher>> poolpublisher=new HashMap<String,Map<String,MessagePublisher>>();
	
	Map<String/*queuename*/,List<MessagePublisher>> availablemessagepublishermap=new HashMap<String,List<MessagePublisher>>();
	
	public MessagePublisherPool( RedisTemplatePool redistemplatepool,boolean retry) {
		
		this.redistemplatepool=redistemplatepool;
		
		this.retry=retry;
	}
	
	public boolean isAvailable(String module,String submodule,String username) {
		
		String queuename=getQueueName(module, submodule, username);
		
		if(!availablemessagepublishermap.containsKey(queuename)) {
			
			addqueue(queuename);
		}
		
		return availablemessagepublishermap.get(queuename).size()>0;
		
	}
	private void addqueue(String queuename) {
		
		if(!redistemplatepool.getQueueinfomap().containsKey(queuename)) {
			
			redistemplatepool.addQueue(queuename);
			

		}
		
		if(!poolpublisher.containsKey(queuename)) {
			
			poolpublisher.put(queuename, getMessagePublisher(queuename));
		}
		
		setAvailability();
	}

	public void setAvailability() {
		
		redistemplatepool.getQueueinfomap().forEach((queuename,list)->{
			
			List<MessagePublisher> messagepublisherlist=new ArrayList<MessagePublisher>();
			
			list.forEach((queuemodel)->{
				
				if(queuemodel.isAvailable(retry)) {
					
					messagepublisherlist.add(poolpublisher.get(queuename).get(queuemodel.getRedisinfo().getRedisid()));
					
				}
			});
			
			availablemessagepublishermap.put(queuename, messagepublisherlist);
		});

	}

	

	private Map<String, MessagePublisher> getMessagePublisher(String queuename) {

		Map<String/*redisid*/,MessagePublisher> publisher=new HashMap<String,MessagePublisher>();
		
		Map<String/*redisid*/,RedisInfo> poolmap=redistemplatepool.getRedispoolinfomap();
		
		poolmap.forEach((redisid,redisinfo)->{
		
			publisher.put(redisid, new RedisMessagePublisher(redisinfo.getRedistemplate(), queuename));

		}
		);
				
		return publisher;
	}
	
	
	public MessagePublisher getPublisher(String module,String submodule,String username) {
		
		
		int seq=0;
		
		String queuename=getQueueName(module,submodule,username);
		try{
			seq=index.get(queuename);
		}catch(Exception e) {
			
		}
		
		if(seq>availablemessagepublishermap.get(queuename).size()-1) {
			
			seq=0;
		}	
		
		int currrent=seq;
		seq++;
		index.put(queuename, seq);
		
		return availablemessagepublishermap.get(queuename).get(currrent);
		
	}

	private String getQueueName(String module, String submodule, String username) {
		StringBuffer sb=new StringBuffer();
		return sb.append(module).append(COLAN).append(submodule).append(COLAN).append(username).toString();
	}

	
}
