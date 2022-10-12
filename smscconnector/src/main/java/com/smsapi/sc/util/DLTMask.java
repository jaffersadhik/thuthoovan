package com.smsapi.sc.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.smsapi.dlt.model.DLTMemory;

public class DLTMask {
	
	@Autowired static DLTMemory dltmemory;
	
	public static String getEntityid(int dltid) {
	
		return dltmemory.getDltmemorybyid().get(dltid).getEntityid();
	}
	
	public static String getTemplateid(int dltid) {
		
		return dltmemory.getDltmemorybyid().get(dltid).getTemplateid();
	}
	
	public static String getSenderid(int dltid) {
		
		return dltmemory.getDltmemorybyid().get(dltid).getSenderid();
	}

}
