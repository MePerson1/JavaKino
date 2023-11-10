package com.processes.cinematicketapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "screenings")
public class Screening
{
    //Id:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Relacje:
    @OneToMany(mappedBy = "screening")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name="movies_id")
    private Movie movie;

    //Pola:
    private Date date;
    private int roomNumber;
    private int ticketCount;
}
