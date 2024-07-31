package com.microservice.account.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.enums.JobTitle;
import com.microservice.account.enums.TaskStatus;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Employee;
import com.microservice.account.model.Manager;
import com.microservice.account.model.Project;
import com.microservice.account.model.Task;
import com.microservice.account.repository.EmployeeRepository;
import com.microservice.account.repository.ProjectRepository;
import com.microservice.account.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	
	public void assignTask(int pid,int eid, Task task) {
		Employee employee = employeeRepository.findById(eid).get();
		Project project = projectRepository.findById(pid).get();
		task.setEmployee(employee);
		task.setProject(project);
		taskRepository.save(task);
	}

	public List<Task> getAllTask(int eid) {
		// TODO Auto-generated method stub
		return taskRepository.findByEmployeeId(eid);
		
	}
	
	public void updateTaskForArchival(int tid) {
		Task task = taskRepository.findById(tid).get();
		task.setArchived(true);
		taskRepository.save(task);
	}

	public List<Task> getTaskByEmployee(String username) {
		// TODO Auto-generated method stub
		return taskRepository.getTaskByEmployeeJpql(username);
	}

	public Task getTaskById(int tid) throws ResourceNotFoundException {
		Optional<Task> optional = taskRepository.findById(tid);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid ID");
		}
		return optional.get();
	}

	public List<String> getAllTaskStatus() {
		TaskStatus[] status = TaskStatus.values();
		List<TaskStatus> list = Arrays.asList(status);
		List<String> listStr = new ArrayList<>();
		list.stream().forEach(jt->{
			listStr.add(jt.toString());
		});
		return listStr;
	}

	public Task saveStatus(Task taskOld) {
		return taskRepository.save(taskOld);
	}

	public Task postTask(Task task) {
		return taskRepository.save(task);
	}

	
	
}
	