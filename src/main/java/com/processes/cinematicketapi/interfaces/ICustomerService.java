package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Customer;

import java.util.List;

public interface ICustomerService
{
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(Long id);
    public Customer getCustomerByName(String name);
    public Customer save(Customer customer);
    public boolean deleteById(Long id);
}
