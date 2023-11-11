package com.processes.cinematicketapi.repository;

import com.processes.cinematicketapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long>
{
    public Movie findByTitle(String title);
    public List<Movie> findAll();
}