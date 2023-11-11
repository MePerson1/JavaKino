package com.processes.cinematicketapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer
{
    //Id:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Relacje:
    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;

    //Pola:
    private String name;
    private String email;
    private String password;
}
