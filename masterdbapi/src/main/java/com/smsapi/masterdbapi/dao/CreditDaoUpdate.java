package com.smsapi.masterdbapi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.CreditModel;

@Repository 
public interface CreditDaoUpdate extends CrudRepository<CreditModel, Integer> {
	
	boolean save(List<CreditModel> creditlist);

}