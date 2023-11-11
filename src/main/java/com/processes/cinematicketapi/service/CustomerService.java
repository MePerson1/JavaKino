package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exeptions.NotFoundException;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    // Metody:
    public Customer getCustomerById(Long id)
    {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
    }

    public Customer getCustomerByName(String name)
    {
        return customerRepository.findByName(name);
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}