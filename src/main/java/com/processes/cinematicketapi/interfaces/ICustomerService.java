package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Customer;

public interface ICustomerService
{
    public Customer getCustomerById(Long id);
    public Customer getCustomerByName(String name);
    public Customer save(Customer customer);
    public boolean deleteById(Long id);
}
