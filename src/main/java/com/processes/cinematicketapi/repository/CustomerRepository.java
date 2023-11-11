package com.processes.cinematicketapi.repository;

import com.processes.cinematicketapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    public Customer findByName(String name);
    public List<Customer> findAll();
}