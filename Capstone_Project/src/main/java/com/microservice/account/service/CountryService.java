package com.microservice.account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Country;
import com.microservice.account.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	public Country postCountry(Country country) {
		return countryRepository.save(country);
	}

	public Country getCountryById(int countryId) {
		Optional<Country> optional = countryRepository.findById(countryId);
		return optional.get();
	}

}