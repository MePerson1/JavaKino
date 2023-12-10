package com.processes.cinematicketapi;

import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.repository.CustomerRepository;
import com.processes.cinematicketapi.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CinemaTicketApiApplicationTests {
    @Autowired
    CustomerRepository repository;

	@Test
	void contextLoads() {
//        CustomerService cs = new CustomerService(repository);
//        Assertions.assertDoesNotThrow(()->cs.deleteById(new Customer()));
	}

}
