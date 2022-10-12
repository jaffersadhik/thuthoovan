package com.smsapi.dlt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smsapi.dlt.dao.DLTDao;
import com.smsapi.dlt.model.DLTModel;

@Service
public class DLTDaoService {

	
	@Autowired
	private DLTDao dltdao;

	
	public DLTModel create(DLTModel dltmodel) {
		
		DLTModel dltmodelresult=dltdao.findBySenderidAndEntityidAndTemplateidEquals(dltmodel.getSenderid(), dltmodel.getEntityid(), dltmodel.getTemplateid());
		
		if(dltmodelresult!=null) {
			
			return dltmodelresult;
			
		}else {
			
			dltmodelresult=dltdao.saveAndFlush(dltmodel);
			
			return dltmodelresult;
		}
	}
	
	
	public DLTModel get(DLTModel dltmodel) {
		
		return dltdao.findByDltidEquals(dltmodel.getDltid());
	}
}
