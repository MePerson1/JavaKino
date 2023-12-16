package com.processes.cinematicketapi.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    @Test
    public void testToString() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Kowaslki Marian");
        customer.setEmail("marian@onet.pl");
        customer.setPassword("Hardpassword1");

        String expectedToString = "Customer(id=1, name=Kowaslki Marian, email=marian@onet.pl, password=Hardpassword1)";

        String actualToString = customer.toString();

        assertEquals(expectedToString, actualToString);
    }
}
