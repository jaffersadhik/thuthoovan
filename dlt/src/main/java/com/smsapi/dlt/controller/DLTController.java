package com.smsapi.dlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.dlt.model.DLTModel;
import com.smsapi.dlt.service.DLTDaoService;

@RestController
@RequestMapping(value = "/dlt")
public class DLTController {


	@Autowired DLTDaoService dltdaoservice;

		
	
	@PostMapping("/getdltbyid")
	public ResponseEntity<?> getByDLTID(@RequestBody DLTModel dltmodel) {

		
		return ResponseEntity.ok().body(dltdaoservice.get(dltmodel));
	
	}
	
	
	@PostMapping("/getdltbykey")
	public ResponseEntity<?> getByDLTKEY(@RequestBody DLTModel dltmodel) {

		return ResponseEntity.ok().body(dltdaoservice.create(dltmodel));
	
	}
	
	
	
	
}