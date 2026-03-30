package com.example.demo.repository;

import com.example.demo.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	public Optional<Customer> findByPhoneNumber(String phoneNumber);
}
