package com.microservice.account.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Employee;
import com.microservice.account.model.EmployeeProject;
import com.microservice.account.model.Project;
import com.microservice.account.repository.EmployeeProjectRepository;

@Service
public class EmployeeProjectService {
	
	@Autowired
	private EmployeeProjectRepository employeeProjectRepository;
	
	public EmployeeProject postEmployeeProject( EmployeeProject employeeProject ) {
		//LocalDate dateoFAssign = emp.getDateOfAssign();
		//emp.setEmployee(employee);
		return employeeProjectRepository.save(employeeProject);
	}

	
}
