package com.processes.cinematicketapi.repository;

import com.processes.cinematicketapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    public Customer findByName(String name);

    public boolean existsByEmail(String email);
    public List<Customer> findAll();
    @Transactional
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.id = :id")
    public int deleteAndReturnStatusById(@Param("id") Long id);
}