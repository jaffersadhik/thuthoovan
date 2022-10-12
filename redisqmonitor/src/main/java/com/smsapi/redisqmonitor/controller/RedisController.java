package com.smsapi.redisqmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redisapi.model.QueuePoolModel;
import com.redisapi.model.RedisPoolModel;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {


	@Autowired @Qualifier("queuepoolmodelmaster") private QueuePoolModel queuepoolmodel; 
	
	@Autowired @Qualifier("redispoolmodelmaster") private RedisPoolModel redispoolmodel; 

	
	
	@PostMapping("/getqueuepoolinfo")
	public ResponseEntity<?> getqueuepoolinfo() {

		return ResponseEntity.ok().body(queuepoolmodel);
	
	}

	@PostMapping("/getredispoolinfo")
	public ResponseEntity<?> getredispoolinfo() {

		return ResponseEntity.ok().body(redispoolmodel);
	
	}
}