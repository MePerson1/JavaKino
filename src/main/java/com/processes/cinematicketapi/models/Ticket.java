package com.processes.cinematicketapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    private String movieTitle;
    private double price;
    private boolean isExpired;

    @Override
    public String toString() {
        return "Ticket{" +
                "customer=" + customer.getName() +
                ", date=" + screening.getDate() + '\n' +
                "room=" + screening.getRoomNumber() +
                ", movie=" + movieTitle + '\n' +
                "id=" + id +
                ", price=" + price +
                ", isExpired=" + isExpired +
                '}';
    }
}
