package com.processes.cinematicketapi.controller;

import com.processes.cinematicketapi.interfaces.ICustomerService;
import com.processes.cinematicketapi.interfaces.IScreeningService;
import com.processes.cinematicketapi.interfaces.ITicketService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Screening;
import com.processes.cinematicketapi.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ticket")
public class TicketController
{
    private final ITicketService _ticketService;
    private final ICustomerService _customerService;
    private final IScreeningService _screeningService;

    @Autowired
    public TicketController(ITicketService ticketService, ICustomerService customerService, IScreeningService screeningService)
    {
        _ticketService = ticketService;
        _customerService = customerService;
        _screeningService = screeningService;
    }

    @GetMapping
    ResponseEntity<List<Ticket>> getAll()
    {
        List<Ticket> tickets = _ticketService.getAllTickets();
        if(tickets.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Ticket> GetMovieById(@PathVariable Long id)
    {
        Ticket ticket = _ticketService.getTicketById(id);
        return new ResponseEntity<>(ticket,HttpStatus.OK);
    }

    //TODO: dokończyć, może zamienić metodę na getTicketsByCustomerId to by było jedno zapytanie
    @GetMapping("customer/{id}")
    ResponseEntity<List<Ticket>> getByCustomer(@PathVariable Long id)
    {
        Customer customer = _customerService.getCustomerById(id);
        List<Ticket> tickets = _ticketService.getTicketsByCustomer(customer);

        return new ResponseEntity<>(tickets,HttpStatus.OK);
    }

    //TODO: dokończyć
    @PostMapping
    ResponseEntity<?> create(@RequestBody Long customer_id, @RequestBody Screening screening)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO dokończyć
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        return new ResponseEntity<>("",HttpStatus.OK);
    }
}
