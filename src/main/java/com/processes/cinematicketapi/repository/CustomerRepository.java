package com.processes.cinematicketapi.repository;

import com.processes.cinematicketapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Optional<Customer> findByName(String name);

    boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.id = :id")
    int deleteAndReturnStatusById(@Param("id") Long id);
}