package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Customer;

import java.util.List;

public interface ICustomerService
{
    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer getCustomerByName(String name);

    Customer save(Customer customer);

    boolean deleteById(Long id);
}
