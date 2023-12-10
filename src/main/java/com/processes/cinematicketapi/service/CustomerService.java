package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.ICustomerService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean deleteById(Long id)
    {
        if(customerRepository.deleteAndReturnStatusById(id)!=0) return true;
        return false;
    }
}