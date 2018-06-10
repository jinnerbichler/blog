package com.johannesinnerbichler.spring.data.jpa.encryption.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}