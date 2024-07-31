package com.microservice.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.Country;
import com.microservice.account.model.Region;
import com.microservice.account.service.CountryService;
import com.microservice.account.service.RegionService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class RegionController {

	@Autowired
	private RegionService regionService;
	
	@Autowired
	private CountryService countryService;
	
	@PostMapping("/api/region/add/{countryId}")
	public Region postRegion(@PathVariable("countryId") int countryId,
							@RequestBody Region region) {
		/* fetch countryObj based on countryId given */
		Country countryObj = countryService.getCountryById(countryId);
		
		/* Set this countryObj to region */
		region.setCountry(countryObj);
		
		/* save region to DB */
		return regionService.saveRegion(region);
	}
	
	
	@GetMapping("/api/region/getall")
	public List<Region> getAllRegion() {
		return regionService.getAllRegion();
		
	}
}