package com.microservice.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Employee;
import com.microservice.account.model.Record;
import com.microservice.account.model.Task;
import com.microservice.account.repository.EmployeeRepository;
import com.microservice.account.repository.RecordRepository;
import com.microservice.account.repository.TaskRepository;
@Service
public class RecordService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private RecordRepository recordRepository;
	public void postRecord(int tid, Record record) {
		
		Task task = taskRepository.findById(tid).get();
		record.setTask(task);
		recordRepository.save(record);
		
	}
		
	}
