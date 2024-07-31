package com.microservice.account.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.Record;
import com.microservice.account.service.RecordService;
import com.microservice.account.service.TaskService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class RecordController {

	@Autowired
	private RecordService recordService;
	private TaskService taskService;
	
	@PostMapping("/capstone/record/employee/{tid}")
	public void postRecord(@PathVariable("tid") int tid,@RequestBody Record record) {
		recordService.postRecord(tid,record);
	}
}
