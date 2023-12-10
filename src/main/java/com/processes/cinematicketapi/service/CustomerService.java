package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.AlreadyExistsException;
import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.ICustomerService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService implements ICustomerService
{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Long id)
    {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
    }

    public Customer getCustomerByName(String name)
    {
        Customer customer = customerRepository.findByName(name);
        if (customer == null) {
            throw new NotFoundException("Customer not found with name: " + name);
        }
        return customer;
    }

    public List<Customer> getAllCustomers()
    {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new NotFoundException("Cannot find any customers.");
        }
        return customers;
    }

    public Customer save(Customer customer) {
        boolean alreadyExists = customerRepository.existsByEmail(customer.getEmail());
        if(alreadyExists)
        {
            throw new AlreadyExistsException("User with email "+customer.getEmail()+" already exists!");
        }

        return customerRepository.save(customer);


    }
    public boolean deleteById(Long id)
    {
        if(customerRepository.deleteAndReturnStatusById(id)!=0) return true;
        return false;
    }
}