package com.processes.cinematicketapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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
    @JsonIgnore
    private List<Ticket> tickets;

    //Pola:
    private String name;
    private String email;
    private String password;

    public String toString()
    {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
