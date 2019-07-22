package com.boardgais.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boardgais.batch.bean.Customer;

public interface RestApiRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findById(int id);
	

}
