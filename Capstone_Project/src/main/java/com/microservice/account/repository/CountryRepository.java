package com.microservice.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.account.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
