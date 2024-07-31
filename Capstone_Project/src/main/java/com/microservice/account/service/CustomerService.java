package com.microservice.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Customer;
import com.microservice.account.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	public Customer addCustomer(Customer customer) {
		 
		return customerRepository.save(customer);
	}
	public List<Customer> getAllCustomers() {
		
		return customerRepository.findAll();
	}
	public List<Customer> getCustomerByRegion(int regionId) {
		
		return customerRepository.findByRegionId(regionId);
	}
	public List<Customer> getCustomerByCountry(int countryId) {
		
		return customerRepository.getCustomerByCountry(countryId);
	}

}