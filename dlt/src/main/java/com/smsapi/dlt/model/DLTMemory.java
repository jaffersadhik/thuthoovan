package com.smsapi.dlt.model;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DLTMemory {

	private Map<String/* DLT Key */,DLTModel> dltmemorybykey;
	
	private Map<Integer/* DLT ID */,DLTModel> dltmemorybyid;

}
