package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;

import java.util.List;

public interface IMovieService
{
    public List<Movie> getAllMovies();
    public Movie getMovieById(Long id);
    public List<Movie> getMovieByTitle(String title);
    public Movie save(Movie movie);
    public boolean deleteById(Long id);
}
