package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Ticket;

import java.util.List;

public interface ITicketService
{
    Ticket getTicketById(Long id);
    List<Ticket> getTicketsByCustomerId(Long id);
    Ticket save(Ticket ticket);
    boolean deleteById(Long id);
    List<Ticket> getAllTickets();
}
