package com.processes.cinematicketapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = {"tickets"})
@Entity
@Table(name = "screenings")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "screening")
    @JsonIgnore
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "movies_id")
    private Movie movie;

    private Date date;
    private int roomNumber;
    private int ticketCount;
    private double ticketPrice;
}
