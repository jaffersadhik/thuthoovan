package com.redisapi.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubModuleQueueCount {
	
	private Map<String, Map<String /* submodule name */, Integer /* queuecount */>> submodulemap=new HashMap<String,Map<String/*submodule*/,Integer/*queuecount*/>>();
	
}
