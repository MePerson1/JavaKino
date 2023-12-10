package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Movie;

import java.util.List;

public interface IMovieService
{
    List<Movie> getAllMovies();

    Movie getMovieById(Long id);

    List<Movie> getMovieByTitle(String title);

    Movie save(Movie movie);

    boolean deleteById(Long id);
}
