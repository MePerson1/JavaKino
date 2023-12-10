package com.processes.cinematicketapi.controller;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.repository.CustomerRepository;
import com.processes.cinematicketapi.repository.MovieRepository;
import com.processes.cinematicketapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private final CustomerService _customerService;

    public CustomerController(CustomerService customerService)
    {
        _customerService  = customerService;
    }

    @GetMapping
    List<Customer> GetAllCustomers(){
        List<Customer> customers = _customerService.getAllCustomers();
        if(customers.isEmpty())
        {
            return null;
        }

        return customers;
    }

    @GetMapping("/{id}")
    Customer GetCustomerById(@PathVariable Long id){
        Customer customer = _customerService.getCustomerById(id);
        if(customer == null)
        {
            return null;
        }
        return customer;
    }
    @GetMapping("/byName/{name}")
    Customer GetCustomerByName(@PathVariable String name)
    {
        Customer customer = _customerService.getCustomerByName(name);
        if(customer == null)
        {
            return null;
        }
        return customer;
    }
    @PostMapping
    Customer CreateCustomer(@RequestBody Customer customer)
    {
        return _customerService.save(customer);
    }

    @PutMapping("/{id}")
    Customer UpdateCustomer(@RequestBody Customer customer, @PathVariable Long id)
    {
        Customer existingCustomer = _customerService.getCustomerById(id);
        if(existingCustomer == null){
            throw new NotFoundException("Customer not found with id: " + id);
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPassword(customer.getPassword());

        return _customerService.save(existingCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public String DeleteCustomer(@PathVariable Long id) {
        boolean isDeleted = _customerService.deleteById(id);

        if (isDeleted) {
            return "Customer deleted successfully";
        } else {
            return "Customer with ID " + id + " does not exist or couldn't be deleted";
        }
    }
}