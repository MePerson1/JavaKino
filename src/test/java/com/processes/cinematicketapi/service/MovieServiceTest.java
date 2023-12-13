package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.repository.MovieRepository;
import com.processes.cinematicketapi.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;
    @InjectMocks
    MovieService movieService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void MovieService_SaveMovie_ReturnsMovie(){
        Movie movieToSave = new Movie();
        movieToSave.setTitle("Shark");
        when(movieRepository.save(movieToSave)).thenReturn(movieToSave);

        Movie savedMovie = movieService.save(movieToSave);

        assertNotNull(savedMovie);
        assertThat((savedMovie.getTitle()).equals(movieToSave.getTitle()));
    }

    @Test
    public void MovieService_GetMovieById_ReturnsMovie(){
        Long movieId = 1L;

        Movie movie = new Movie();
        movie.setId(movieId);


        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        Movie gettedMovie = movieService.getMovieById(movieId);
        assertNotNull(gettedMovie);
        assertEquals(movie,gettedMovie);
    }
    @Test
    public void MovieService_GetMovieById_ThrowsExpectation(){
        Long movieId = 1L;

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.getMovieById(movieId));
    }

    @Test
    public void MovieService_GetMovieByTitle_ReturnsMovie(){
        String movieTitle = "Shark";

        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle(movieTitle);
        movies.add(movie);


        when(movieRepository.findByTitle(movieTitle)).thenReturn(movies);

        List<Movie> gettedMovies = movieService.getMovieByTitle(movieTitle);
        assertNotNull(gettedMovies);
        assertEquals(1,gettedMovies.size());
        assertEquals(movie,(gettedMovies.get(0)));
    }

    @Test
    public void MovieService_DeleteMovieById_MovieDeleted()
    {
        Long movieId = 1L;
        when(movieRepository.deleteAndReturnStatusById(movieId)).thenReturn(1);

        boolean isDeleted = movieService.deleteById(movieId);

        assertTrue(isDeleted);
    }
}
