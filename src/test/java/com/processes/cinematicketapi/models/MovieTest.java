package com.processes.cinematicketapi.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    @Test
    public void testToString() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Shark");
        movie.setDescription("Movie about shark");

        String expectedToString = "Movie(id=1, title=Shark, description=Movie about shark, genre=null, director=null, originalLanguage=null, rating=0, ageRestriction=0, runtime=0)";
        String actualToString = movie.toString();

        assertEquals(expectedToString, actualToString);
    }
}
