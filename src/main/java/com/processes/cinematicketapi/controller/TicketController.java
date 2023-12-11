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

    @GetMapping("customer/{id}")
    ResponseEntity<List<Ticket>> getByCustomer(@PathVariable Long id)
    {
        List<Ticket> tickets = _ticketService.getTicketsByCustomerId(id);
        if(tickets.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets,HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody Long customer_id, @RequestBody Long screening_id)
    {
        Screening screening = _screeningService.getScreeningById(screening_id);
        int ticketsCount = screening.getTicketCount();
        if(ticketsCount<=0)
        {
            return new ResponseEntity<>("There is no tickets avaliable for this screening!",HttpStatus.OK);
        }
        Customer customer = _customerService.getCustomerById(customer_id);

        screening.setTicketCount(ticketsCount-1);
        Ticket newTicket = new Ticket();
        newTicket.setCustomer(customer);
        newTicket.setScreening(screening);
        newTicket.setName(screening.getMovie().getTitle());
        newTicket.setPrice(screening.getTicketPrice());

        _ticketService.save(newTicket);

        return new ResponseEntity<>(newTicket,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        try
        {
            boolean isDeleted = _ticketService.deleteById(id);

            if (isDeleted)
            {
                return new ResponseEntity<>("Ticket deleted successfully", HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Ticket with ID " + id + " does not exist or couldn't be deleted", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Error deleting ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
