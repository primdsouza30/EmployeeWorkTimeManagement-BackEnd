package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.account.model.Employee;

public interface EmployeeRepository  
extends JpaRepository<Employee, Integer>{

	@Query(nativeQuery = true , value = "select * from employee where name LIKE %?1% OR city LIKE %?1%")
	List<Employee> searchEmployeeOnName(String searchStr);

	@Query("select e from Employee e where e.name LIKE %?1% OR e.city LIKE %?1%")
	List<Employee> searchEmployeeOnNameJpql(String searchStr);
	
	
}

/*
 * JpaRepository <I>
 * Obj save(Obj)		EntityManager:void persist(obj)
 * 		|
 *
 * EmployeeRepository <I>
 * 
 * Obj save(Obj)
 * List<T> findAll()
 * deleteById(id)
 * Optional<Employee> findById(id) : if id is valid , u get Employee obj else u have 
 * empty optional value
 * */