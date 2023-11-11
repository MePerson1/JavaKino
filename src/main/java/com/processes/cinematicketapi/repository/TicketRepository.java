package com.processes.cinematicketapi.repository;

import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Long>
{
    List<Ticket> findByCustomer(Customer customer);
    public List<Ticket> findAll();
}