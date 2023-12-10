package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.models.Screening;
import com.processes.cinematicketapi.models.Ticket;

import java.util.List;

public interface ITicketService
{
    public Ticket getTicketById(Long id);
    public List<Ticket> getTicketsByCustomer(Customer customer);
    public Ticket save(Ticket ticket);
    public boolean deleteById(Long id);
    List<Ticket> getAllTickets();
}
