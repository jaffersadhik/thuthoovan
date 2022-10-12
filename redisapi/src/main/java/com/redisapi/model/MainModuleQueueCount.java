package com.redisapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MainModuleQueueCount {
	
	private Map<String, Map<String /* submodule name */, Integer /* queuecount */>> mainmodulemap=new HashMap<String,Map<String/*submodule*/,Integer/*queuecount*/>>();
	
}
