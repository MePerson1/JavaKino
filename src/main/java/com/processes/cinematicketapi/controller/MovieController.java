package com.processes.cinematicketapi.controller;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.IMovieService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.service.CustomerService;
import com.processes.cinematicketapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private final IMovieService _movieService;

    public MovieController(IMovieService movieService) {
        _movieService = movieService;
    }

    @GetMapping
    ResponseEntity<List<Movie>> GetAllMovies(){
        List<Movie> movies = _movieService.getAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    ResponseEntity<Movie> GetMovieById(@PathVariable Long id){
        Movie movie = _movieService.getMovieById(id);
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }
    @GetMapping("/by-title/{title}")
    ResponseEntity<List<Movie>> GetMovieByTitle(@PathVariable String title){
        List<Movie> movies = _movieService.getMovieByTitle(title);
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> CreateMovie(@RequestBody Movie newMovie)
    {
        try{
            Movie movie = _movieService.save(newMovie);
            return new ResponseEntity<>(movie,HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Failed to create customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Movie> UpdateMovie(@RequestBody Movie movie, @PathVariable Long id)
    {
        Movie existingMovie = _movieService.getMovieById(id);
        if(existingMovie == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setOriginalLanguage(movie.getOriginalLanguage());
        existingMovie.setRating(movie.getRating());
        existingMovie.setAgeRestriction(movie.getAgeRestriction());
        existingMovie.setRuntime(movie.getRuntime());

        Movie updatedMovie = _movieService.save(existingMovie);

        if(updatedMovie == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedMovie,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        try {
            boolean isDeleted = _movieService.deleteById(id);

            if (isDeleted) {
                return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Movie with ID " + id + " does not exist or couldn't be deleted", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Error deleting movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
