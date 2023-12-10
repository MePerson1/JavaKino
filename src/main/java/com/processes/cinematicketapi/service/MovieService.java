package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.IMovieService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService implements IMovieService
{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Metody:
    @Override
    public Movie getMovieById(Long id) throws NotFoundException
    {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));
    }

    @Override
    public List<Movie> getMovieByTitle(String title)
    {
        List<Movie> movies = movieRepository.findByTitle(title);
        if (movies.isEmpty()) {
            throw new NotFoundException("Movie not found with title: " + title);
        }
        return movies;
    }

    @Override
    public List<Movie> getAllMovies()
    {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            throw new NotFoundException("Cannot find any movies");
        }
        return movies;
    }

    @Override
    public Movie save(Movie movie)
    {
        return movieRepository.save(movie);
    }

    @Override
    public boolean deleteById(Long id)
    {
        if(movieRepository.deleteAndReturnStatusById(id)!=0) return true;
        return false;
    }
}
