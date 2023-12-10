package com.processes.cinematicketapi.controller;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.ICustomerService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.repository.CustomerRepository;
import com.processes.cinematicketapi.repository.MovieRepository;
import com.processes.cinematicketapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private final ICustomerService _customerService;

    public CustomerController(ICustomerService customerService)
    {
        _customerService  = customerService;
    }

    @GetMapping
    ResponseEntity<List<Customer>> GetAllCustomers(){
        List<Customer> customers = _customerService.getAllCustomers();
        if(customers.isEmpty())
        {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> GetCustomerById(@PathVariable Long id){
        Customer customer = _customerService.getCustomerById(id);
        if(customer == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @GetMapping("/by-name/{name}")
    ResponseEntity<Customer> GetCustomerByName(@PathVariable String name)
    {
        Customer customer = _customerService.getCustomerByName(name);
        if(customer == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> CreateCustomer(@RequestBody Customer newCustomer)
    {
        try{
            Customer customer = _customerService.save(newCustomer);
            return new ResponseEntity<>(customer,HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Failed to create customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    ResponseEntity<Customer> UpdateCustomer(@RequestBody Customer customer, @PathVariable Long id)
    {
        Customer existingCustomer = _customerService.getCustomerById(id);
        if(existingCustomer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPassword(customer.getPassword());

        Customer updatedCustomer = _customerService.save(existingCustomer);

        if(updatedCustomer == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> DeleteCustomer(@PathVariable Long id) {
        try {
            boolean isDeleted = _customerService.deleteById(id);

            if (isDeleted) {
                return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Customer with ID " + id + " does not exist or couldn't be deleted", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Error deleting customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
