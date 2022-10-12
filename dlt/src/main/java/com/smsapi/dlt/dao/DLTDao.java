package com.smsapi.dlt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.dlt.model.DLTModel;


@Repository
public interface DLTDao extends JpaRepository<DLTModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	DLTModel saveAndFlush(DLTModel entity);
	
	DLTModel findBySenderidAndEntityidAndTemplateidEquals(String senderid,String entityid,String templateid);
	
	DLTModel findByDltidEquals(int dltid);

	

}