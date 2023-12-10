package com.processes.cinematicketapi.controller;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.service.CustomerService;
import com.processes.cinematicketapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private final MovieService _movieService;

    public MovieController(MovieService movieService) {
        _movieService = movieService;
    }

    @GetMapping
    List<Movie> GetAllMovies(){
        List<Movie> movies = _movieService.getAllMovies();
        if(movies.isEmpty())
        {
            return null;
        }

        return movies;
    }

    @GetMapping("/{id}")
    Movie GetMovieById(@PathVariable Long id){
        Movie movie = _movieService.getMovieById(id);
        if(movie == null)
        {
            return null;
        }
        return movie;
    }
    @GetMapping("/byTitle/{title}")
    Movie GetMovieById(@PathVariable String title){
        Movie movie = _movieService.getMovieByTitle(title);
        if(movie == null)
        {
            return null;
        }
        return movie;
    }

    @PostMapping
    Movie CreateCustomer(@RequestBody Movie movie)
    {
        return _movieService.save(movie);
    }

    @PutMapping("/{id}")
    Movie UpdateCustomer(@RequestBody Movie updatedMovie, @PathVariable Long id)
    {
        Movie existingMovie = _movieService.getMovieById(id);
        if(existingMovie == null){
            throw new NotFoundException("Movie not found with id: " + id);
        }

        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setDescription(updatedMovie.getDescription());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setDirector(updatedMovie.getDirector());
        existingMovie.setOriginalLanguage(updatedMovie.getOriginalLanguage());
        existingMovie.setRating(updatedMovie.getRating());
        existingMovie.setAgeRestriction(updatedMovie.getAgeRestriction());
        existingMovie.setRuntime(updatedMovie.getRuntime());

        return _movieService.save(existingMovie);
    }

    @DeleteMapping("/{id}")
    public String DeleteMovie(@PathVariable Long id) {
        boolean isDeleted = _movieService.deleteById(id);

        if (isDeleted) {
            return "Customer deleted successfully";
        } else {
            return "Customer with ID " + id + " does not exist or couldn't be deleted";
        }
    }
}
