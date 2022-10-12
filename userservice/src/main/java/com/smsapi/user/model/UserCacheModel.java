package com.smsapi.user.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCacheModel {

	private Map<String,UserModel> usercache=new HashMap<String,UserModel>();
	
	private String memoryid;
	
}
