package com.processes.cinematicketapi;

import com.processes.cinematicketapi.models.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    @Test
    public void testToString() {

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Shark");
        movie.setDescription("Movie about shark");

        String expectedToString = "Movie{id=1, title='Shark', description='Movie about shark'}";
        String actualToString = movie.toString();

        assertEquals(expectedToString, actualToString);
    }
}
