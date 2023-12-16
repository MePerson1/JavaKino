package com.processes.cinematicketapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = {"screenings"})
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Screening> screenings;

    private String title;
    private String description;
    private String genre;
    private String director;
    private String originalLanguage;
    private int rating;
    private int ageRestriction;
    private int runtime;
}
