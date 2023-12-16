package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NoContentException;
import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.ICustomerService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name).orElseThrow(() -> new NotFoundException("Customer with name: " + name + " not found!"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new NoContentException("Cannot find any customers!");
        }
        return customers;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean checkIfEmailTaken(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public boolean deleteById(Long id) {
        return customerRepository.deleteAndReturnStatusById(id) != 0;
    }
}