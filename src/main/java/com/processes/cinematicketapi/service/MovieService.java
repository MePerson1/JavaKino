package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Metody:
    public Movie getMovieById(Long id)
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
}
