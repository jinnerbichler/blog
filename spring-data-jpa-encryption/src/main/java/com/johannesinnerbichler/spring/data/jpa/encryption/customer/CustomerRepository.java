package com.johannesinnerbichler.spring.data.jpa.encryption.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findOneByEmail(String email);
}