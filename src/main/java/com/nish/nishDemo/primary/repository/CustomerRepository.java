package com.nish.nishDemo.primary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nish.nishDemo.model.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Long>{
	
	@Transactional
	Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
