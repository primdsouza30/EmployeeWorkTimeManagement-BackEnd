package com.microservice.account.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.dto.ResponseDto;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Manager;
import com.microservice.account.model.Project;
import com.microservice.account.model.Region;
import com.microservice.account.service.ProjectService;
import com.microservice.account.service.RegionService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProjectController {

	@Autowired
	private RegionService regionService;
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/capstone/project/add")
	/*
	public ResponseEntity<?> postProject(@RequestBody Project project) {
		
		/* Fetch region obj based on regionid 
		
		Region region = null; 
		try {
			region  = regionService.getRegionById(regionId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity
					 .badRequest()
					 .body(new ResponseDto(e.getMessage(), "400"));
		}
		/* Attach region to project 
		
		/* Save project 
		project = projectService.postProject(project);
		return ResponseEntity.ok().body(project); 
	}
	*/
	
	public Project postProject(@RequestBody Project project) {
		return projectService.postProject(project);
	}
	
	@GetMapping("/capstone/project/all")
	public List<Project> getAll(){
		return projectService.getAll();
	}
	
	
	
}