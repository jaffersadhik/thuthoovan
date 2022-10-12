package com.smsapi.smsclass.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.smsclass.model.SMSClassModel;


@Repository
public interface SMSClassDao extends JpaRepository<SMSClassModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	SMSClassModel saveAndFlush(SMSClassModel entity);
	
	SMSClassModel findBySenderidAndEntityidAndTemplateidEquals(String senderid,String entityid,String templateid);
	
	SMSClassModel findByDltidEquals(int dltid);

	

}