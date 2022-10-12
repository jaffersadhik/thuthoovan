package com.smsapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.model.ResultModel;
import com.smsapi.model.ResultPoolModel;
import com.smsapi.model.TestRequestM;
import com.smsapi.model.TestStatus;
import com.smsapi.model.TestSummary;
import com.smsapi.service.SMSSenderService;

@RestController
public class RequestController {
	
	@Autowired SMSSenderService smssenderservice;

	@Autowired TestStatus teststatus;
	
	@Autowired ResultPoolModel resultpool;
	
	@PostMapping("/teststart")
	public String starttest(@RequestBody TestRequestM requestmodel) {
		
		if(smssenderservice.startTest(requestmodel)) {
			
			return "STARTED";
		}else {
			
			return "IGNORED PREVIOUS TEST IS RUNNING";
		}
	
	}
	
	
	@PostMapping("/teststatus")
	public boolean getmemory() {

		return teststatus.isRunning();
	
	}

	@PostMapping("/testsummary")
	public ResponseEntity<?>  gettestsummary() {

		List<TestSummary> list=new ArrayList<TestSummary>();
		
		resultpool.getResultpool().forEach((k,v)->{
				list.add(TestSummary.builder().date(new Date(((ResultModel)v).getStarttime())).acceptancetps(((ResultModel)v).getAccecptancetps()).build());
		});
		return ResponseEntity.ok().body(list);
		
	}

	
	@PostMapping("/testresult")
	public ResponseEntity<?> gettestresult() {

		return ResponseEntity.ok().body(resultpool);

	
	}
	
	
}