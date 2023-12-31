package com.processes.cinematicketapi.controller;

import com.processes.cinematicketapi.exceptions.AlreadyExistsException;
import com.processes.cinematicketapi.interfaces.ICustomerService;
import com.processes.cinematicketapi.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController
{
    private final ICustomerService _customerService;

    @Autowired
    public CustomerController(ICustomerService customerService)
    {
        _customerService = customerService;
    }

    @GetMapping
    ResponseEntity<List<Customer>> getAllCustomers()
    {
        List<Customer> customers = _customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> getCustomerById(@PathVariable Long id)
    {
        Customer customer = _customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/by-name/{name}")
    ResponseEntity<Customer> getCustomerByName(@PathVariable String name)
    {
        Customer customer = _customerService.getCustomerByName(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> createCustomer(@RequestBody Customer newCustomer)
    {
        try
        {
            if(_customerService.checkIfEmailTaken(newCustomer.getEmail()))
            {
                return new ResponseEntity<>("User with email " + newCustomer.getEmail() + " already exists!", HttpStatus.BAD_REQUEST);
            }
            Customer customer = _customerService.save(newCustomer);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Failed to create customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id)
    {
        Customer existingCustomer = _customerService.getCustomerById(id);
        if(!existingCustomer.getEmail().equals(customer.getEmail()))
        {
            if(_customerService.checkIfEmailTaken(customer.getEmail()))
            {
                return new ResponseEntity<>("Customer with email " + customer.getEmail() + " already exists!", HttpStatus.BAD_REQUEST);
            }
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPassword(customer.getPassword());

        Customer updatedCustomer = _customerService.save(existingCustomer);

        if (updatedCustomer == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCustomer(@PathVariable Long id)
    {
        try
        {
            boolean isDeleted = _customerService.deleteById(id);

            if (isDeleted)
            {
                return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Customer with ID " + id + " does not exist or couldn't be deleted", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Error deleting customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
