package com.processes.cinematicketapi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "tickets")
public class Ticket
{
    //Id:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Relacje:
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="screening_id")
    private Screening screening;

    //Pola:
    private String name;
    private double price;
    private boolean isExpired;

    @Override
    public String toString()
    {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isExpired=" + isExpired +
                '}';
    }
}
