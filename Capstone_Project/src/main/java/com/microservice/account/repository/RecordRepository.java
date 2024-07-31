package com.microservice.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.account.model.Record;

public interface RecordRepository extends JpaRepository<Record, Integer>{

}
