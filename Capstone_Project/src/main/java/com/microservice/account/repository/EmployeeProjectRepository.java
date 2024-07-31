package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.account.model.EmployeeProject;
import com.microservice.account.model.Project;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer> {
	
	
	

}
