package com.microservice.account.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.enums.JobTitle;
import com.microservice.account.enums.RoleType;
import com.microservice.account.model.Employee;
import com.microservice.account.model.Manager;
import com.microservice.account.model.UserInfo;
import com.microservice.account.service.ManagerService;
import com.microservice.account.service.UserInfoService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ManagerController {
	
	@Autowired
	private ManagerService managerService; 
	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/capstone/manager/add")
	public Manager postManager(@RequestBody Manager manager) {
		manager.setJobTitle(JobTitle.PROJECT_MANAGER);
		/*detach UserInfo from hr */
		UserInfo userInfo = manager.getUserInfo();
		
		/*assign role and save it in DB and fetch full UserInfo object with id*/
		userInfo.setRole(RoleType.MANAGER);
		userInfo = userInfoService.insertUserInfo(userInfo);
		
		/* Attach UserInfo back to HR */
		manager.setUserInfo(userInfo);
		
		/* Save HR */
		return managerService.insertManager(manager);
	}
	
	@GetMapping("/capstone/manager/all")
	public List<Manager> getAll(){
		return managerService.getAll();
	}
	
	@GetMapping("/capstone/manager/employee")
	public List<Employee> getEmployeeByManager(Principal principal) {
		String username =principal.getName();
		List<Employee> list = managerService.getEmployeeByManager(username);
		return list;
		
	}
	
}
