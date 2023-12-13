package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NoContentException;
import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.ITicketService;
import com.processes.cinematicketapi.models.Ticket;
import com.processes.cinematicketapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketService implements ITicketService
{
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository)
    {
        this.ticketRepository = ticketRepository;
    }

    // Metody:
    @Override
    public Ticket getTicketById(Long id)
    {
        return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("Ticket with id: " + id + " not found!"));
    }

    @Override
    public List<Ticket> getTicketsByCustomerId(Long id)
    {
        List<Ticket> tickets = ticketRepository.findByCustomer_Id(id);
        if (tickets.isEmpty())
        {
            throw new NotFoundException("Cannot find any tickets for customer with id: " + id + '!');
        }
        return tickets;
    }

    @Override
    public List<Ticket> getAllTickets()
    {
        List<Ticket> tickets = ticketRepository.findAll();
        if (tickets.isEmpty())
        {
            throw new NoContentException("Cannot find any tickets!");
        }
        return tickets;
    }

    @Override
    public Ticket save(Ticket ticket)
    {
        return ticketRepository.save(ticket);
    }

    @Override
    public boolean deleteById(Long id)
    {
        return ticketRepository.deleteAndReturnStatusById(id) != 0;
    }
}