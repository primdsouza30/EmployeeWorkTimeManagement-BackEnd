package com.microservice.account.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.dto.ResponseDto;
import com.microservice.account.enums.TaskStatus;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Task;
import com.microservice.account.service.TaskService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/capstone/task/employee/{projectId}/{eid}")
	public void assignTask(@PathVariable("projectId") int pid,@PathVariable("eid") int eid,@RequestBody Task task) {
		
		task.setTaskStatus(TaskStatus.ASSIGN);
		taskService.assignTask(pid,eid,task);
	}
	
	@GetMapping("/capstone/task/{eid}")
	public List<Task> getAllTask(@PathVariable("eid") int eid){
		return taskService.getAllTask(eid)
					.stream()
					.filter(t->t.isArchived() == false)
					.collect(Collectors.toList());
	}
	
	@GetMapping("/capstone/task/employee")
	public List<Task> getTaskByEmployee(Principal principal) {
		String username = principal.getName();
		List<Task> list = taskService.getTaskByEmployee(username);
		return list;
	}

	
	@GetMapping("/capstone/task/archive/{tid}")
	public void updateTaskForArchival(@PathVariable("tid") int tid) {
		taskService.updateTaskForArchival(tid);
	}
	
	@GetMapping("/capstone/taskstatus")
	public List<String> getAllJobType(){
		List<String> list = taskService.getAllTaskStatus();
		return list;
	}
	
	@GetMapping("/capstone/task/update/{tid}/{status}")
	public Task updateTaskStatus(@PathVariable("tid") int tid,@PathVariable("status") TaskStatus taskStatus) {
		
		Task task = null;
		try {
			task = taskService.getTaskById(tid);
			task.setTaskStatus(taskStatus);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return taskService.postTask(task);
	}
}