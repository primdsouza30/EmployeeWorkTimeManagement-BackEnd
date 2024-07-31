package com.microservice.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Region;
import com.microservice.account.repository.RegionRepository;

@Service
public class RegionService {

	@Autowired
	private RegionRepository regionRepository;
	
	public Region saveRegion(Region region) {
		 
		return regionRepository.save(region);
	}

	public Region getRegionById(int regionId) throws ResourceNotFoundException {
		 Optional<Region> optional = regionRepository.findById(regionId); 
		 if(optional.isEmpty()) {
				throw new ResourceNotFoundException("Invalid Employee Id Given");
			}
		return regionRepository.findById(regionId).get();
	}

	public List<Region> getAllRegion() {
		// TODO Auto-generated method stub
		return regionRepository.findAll();
	}

}