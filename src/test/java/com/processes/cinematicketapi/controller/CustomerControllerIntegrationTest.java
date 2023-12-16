package com.processes.cinematicketapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processes.cinematicketapi.CinemaTicketApiApplication;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CinemaTicketApiApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
@Transactional
public class CustomerControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository repository;

    @Test
    public void whenValidInput_thenCreateCustomer() throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setName("John Smith");
        newCustomer.setEmail("john@test.com");
        newCustomer.setPassword("superhardpassword");

        ObjectMapper objectMapper = new ObjectMapper();
        mvc.perform(post("/api/customer").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newCustomer)));
        List<Customer> found = repository.findAll();

        assertThat(found).extracting(Customer::getName).containsOnly("John Smith");
    }

    @Test
    public void givenCustomers_whenGetCustomers_thenStatus200() throws Exception {
        createTestCustomer("John Smith", "john@test.com", "Superhardpassword1!");
        createTestCustomer("Alex Timberman", "alexy5@erd.ru", "$russianPasswordExample900");

        mvc.perform(get("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("John Smith")))
                .andExpect(jsonPath("$[1].name", is("Alex Timberman")));
    }

    @Test
    public void givenNone_whenGetCustomers_thenStatus204() throws Exception {
        mvc.perform(get("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private void createTestCustomer(String name, String email, String password) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        repository.saveAndFlush(customer);
    }
}
