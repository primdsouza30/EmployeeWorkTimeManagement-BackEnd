package com.microservice.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.microservice.account.model.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

}
