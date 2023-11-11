package com.processes.cinematicketapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "movies")
public class Movie
{
    //Id:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Relacje:
    @OneToMany(mappedBy = "movie")
    private List<Screening> screenings;

    //Movie:
    private String title;
    private String description;
    private String genre;
    private String director;
    private String originalLanguage;
    private int raiting;
    private int ageRestriction;
    private int runtime;
}
