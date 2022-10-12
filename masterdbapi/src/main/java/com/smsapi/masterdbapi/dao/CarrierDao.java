package com.smsapi.masterdbapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.CarrierModel;


@Repository
public interface CarrierDao extends JpaRepository<CarrierModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	CarrierModel saveAndFlush(CarrierModel entity);
	
	CarrierModel findByCarriernameEquals(String carriername);
	

}