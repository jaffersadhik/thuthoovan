package com.smsapi.redisqmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redisapi.model.MainModuleQueueCount;
import com.redisapi.model.QueueModel;
import com.redisapi.model.QueuePoolModel;
import com.redisapi.model.RedisInfo;
import com.redisapi.model.RedisPoolModel;
import com.redisapi.model.RedisTemplatePool;
import com.redisapi.model.SubModuleQueueCount;

@Service
@Transactional
public class MonitorService {
	
	@Autowired RedisTemplatePool redistemlatepool;
	
	@Autowired QueuePoolModel queuepoolmodel;
	
	@Autowired RedisPoolModel redispoolmodel;
	
	@Autowired MainModuleQueueCount mainmodulequeue;
	

	@Autowired SubModuleQueueCount submodulequeue;
	
	byte [] QUEUENAME_SEARCH_PATTERN="*".getBytes();
	
	public void doMonitorRedisQueue() {
		
		addQueue();
		
		refreshAvailability();
		
		redisPoolBuild();
		
		submodulequeuebuild();
		
		mainmodulequeuebuild();
		
	}
	
	
	private void mainmodulequeuebuild() {
		


		Map<String,Map<String,Integer>> mainmodulequeue=new HashMap<String,Map<String,Integer>>();
		
			
		queuepoolmodel.getQueueinfomap().forEach((queuename,queuelistmodel)->{
			
			queuelistmodel.forEach((queuemodel)->{
				
				if(queuemodel.getQueuecount()>0) {
					
					Map<String,Integer> mainmodulequeuecount=mainmodulequeue.get(queuemodel.getRedisinfo().getRedisid());
					
					if(mainmodulequeuecount==null) {
						
						mainmodulequeuecount=new HashMap<String,Integer>();
				
						mainmodulequeue.put(queuemodel.getRedisinfo().getRedisid(), mainmodulequeuecount);

					}
					String submodule=getMainmodule(queuemodel.getQueuename());
					int count=0;
					try {
						count=mainmodulequeuecount.get(submodule);
						mainmodulequeuecount.put(submodule, (int) (count+queuemodel.getQueuecount()));
					}catch(Exception e) {
						
					}
				}
			});
		});
	
	
		this.mainmodulequeue.setMainmodulemap(mainmodulequeue);
	
		
	}


	private String getMainmodule(String queuename) {

		return queuename.split(":")[0];
	
	}


	private void submodulequeuebuild() {

		Map<String,Map<String,Integer>> submodulequeue=new HashMap<String,Map<String,Integer>>();
		
			
		queuepoolmodel.getQueueinfomap().forEach((queuename,queuelistmodel)->{
			
			queuelistmodel.forEach((queuemodel)->{
				
				if(queuemodel.getQueuecount()>0) {
					
					Map<String,Integer> submodulequeuecount=submodulequeue.get(queuemodel.getRedisinfo().getRedisid());
					
					if(submodulequeuecount==null) {
						
						submodulequeuecount=new HashMap<String,Integer>();
				
						submodulequeue.put(queuemodel.getRedisinfo().getRedisid(), submodulequeuecount);

					}
					String submodule=getSubmodule(queuemodel.getQueuename());
					int count=0;
					try {
						count=submodulequeuecount.get(submodule);
						submodulequeuecount.put(submodule, (int) (count+queuemodel.getQueuecount()));
					}catch(Exception e) {
						
					}
				}
			});
		});
	
		this.submodulequeue.setSubmodulemap(submodulequeue);
	}


	private String getSubmodule(String queuename) {

		return queuename.split(":")[1];
		
	}


	private void redisPoolBuild() {
		
		Map<String/*redisname*/,List<QueueModel>> redisinfomap=new HashMap<String,List<QueueModel>>();
		
		queuepoolmodel.getQueueinfomap().forEach((queuename,queuelistmodel)->{
			
			queuelistmodel.forEach((queuemodel)->{
				
				if(queuemodel.getQueuecount()>0) {
					
					List<QueueModel> qlist=redisinfomap.get(queuemodel.getRedisinfo().getRedisid());
					
					if(qlist==null) {
						
						qlist=new ArrayList<QueueModel>();
				
						redisinfomap.put(queuemodel.getRedisinfo().getRedisid(), qlist);

					}

					qlist.add(queuemodel);
					
				}
			});
		});
	
		redispoolmodel.setRedisinfomap(redisinfomap);
	}


	private void addQueue() {
		

		
		Set<byte[]> queueset =new HashSet<byte[]>();
		
		redistemlatepool.getRedispoolinfomap().forEach((redisid,redisinfo)->{
			
			queueset.addAll(getQueueNameSet(redisinfo));
		});
		
		
		queueset.forEach((queuename)->{
			
			addQueue(new String(queuename));
		});
	
	}


	private Set<byte[]> getQueueNameSet(RedisInfo redisinfo) {
		

		
		RedisConnection connection=null;
		
		long result=0;
		
		Set<byte[]> queueset=null;
		
		try {
			
			connection=redisinfo.getRedistemplate().getConnectionFactory().getConnection();
			
			queueset=connection.keys(QUEUENAME_SEARCH_PATTERN);
			
		
			
		}finally {
			
			close(connection);
		}
		
		return queueset;
	
	}


	private void close(RedisConnection connection) {
		
		try {
			
			connection.close();
			
		}catch(Exception e) {
			
		}
		
	}
	
	

	
	public void refreshAvailability() {
		
		
		redistemlatepool.getQueueinfomap().forEach((queuename,queuelist)->{
			
			queuelist.forEach((queuemodel)->{
				
				if(isConnected(queuemodel.getRedisinfo().getRedistemplate())){
					
					setQueueCount(queuename,queuemodel);
				}else {
					
					queuemodel.setConnectionavailable(false);
				}
				});
			
			
			});
		
	}
	
	
	


	

	private boolean isConnected(RedisTemplate<String, Object> redisTemplate) {
		
		RedisConnection connection=null;
		
		try {
			connection=redisTemplate.getConnectionFactory().getConnection();
			
			if(!connection.isClosed()) {
				
				return true;
			}
		}finally {
			
			close(connection);
		}
		return false;
	}
	
	
	private void setQueueCount(String queuename, QueueModel queuemodel) {
		
		long count=getCount(queuename, queuemodel.getRedisinfo().getRedistemplate());
		
		queuemodel.setQueuecount(count);	
	}
	
	
	private long getCount(String queuename, RedisTemplate<String, Object> redisTemplate) {
		
		RedisConnection connection=null;
		
		long result=0;
		
		try {
			connection=redisTemplate.getConnectionFactory().getConnection();
			
			Long e= connection.lLen(queuename.getBytes());
			
			if(e!=null) {
				
				result=e;
			}
			
		}finally {
			
			close(connection);
		}
		
		return result;
	}
	
	public void addQueue(String queuename) {
		
		if(!redistemlatepool.getQueueinfomap().containsKey(queuename)) {
			
			redistemlatepool.getQueueinfomap().put(queuename, getList(queuename));
		}
	}

	
	
	private List<QueueModel> getList(String queuename) {
		
		List<QueueModel> list =new ArrayList<QueueModel>();
		
		redistemlatepool.getRedispoolinfomap().forEach((redisid,redisinfo)->{
			
			list.add(QueueModel.builder().queuename(queuename).connectionavailable(true).redisinfo(redisinfo).build());
		});
		
		return list;
	}
}