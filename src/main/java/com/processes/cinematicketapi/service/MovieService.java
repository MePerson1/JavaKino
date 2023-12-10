package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.IMovieService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService
{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Metody:
    public Movie getMovieById(Long id) throws NotFoundException
    {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));
    }

    public Movie getMovieByTitle(String title)
    {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }

    public Movie save(Movie movie)
    {
        return movieRepository.save(movie);
    }

    public boolean deleteById(Long id)
    {
        if(movieRepository.deleteAndReturnStatusById(id)!=0) return true;
        return false;
    }
}
