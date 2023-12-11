package com.processes.cinematicketapi.repository;

import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Long>
{
    List<Ticket> findByCustomer(Customer customer);
    List<Ticket> findByCustomer_Id(Long customerId);
    List<Ticket> findAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Ticket t WHERE t.id = :id")
    int deleteAndReturnStatusById(@Param("id") Long id);
}