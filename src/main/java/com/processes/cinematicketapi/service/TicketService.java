package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.ITicketService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Screening;
import com.processes.cinematicketapi.models.Ticket;
import com.processes.cinematicketapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService
{
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository)
    {
        this.ticketRepository = ticketRepository;
    }

    // Metody:

    public Ticket getTicketById(Long id)
    {
        return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found with id: " + id));
    }

    public List<Ticket> getTicketsByCustomer(Customer customer)
    {
        return ticketRepository.findByCustomer(customer);
    }

    public List<Ticket> getAllTickets()
    {
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket ticket)
    {
        return ticketRepository.save(ticket);
    }

    public boolean deleteById(Long id)
    {
        if(ticketRepository.deleteAndReturnStatusById(id)!=0) return true;
        return false;
    }
}