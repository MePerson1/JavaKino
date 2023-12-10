package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;

public interface IMovieService
{
    public Movie getMovieById(Long id);
    public Movie getMovieByTitle(String title);
    public Movie save(Movie movie);
    public boolean deleteById(Long id);
}
