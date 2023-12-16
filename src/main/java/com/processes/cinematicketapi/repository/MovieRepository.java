package com.processes.cinematicketapi.repository;

import com.processes.cinematicketapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String title);

    @Transactional
    @Modifying
    @Query("DELETE FROM Movie m WHERE m.id = :id")
    int deleteAndReturnStatusById(@Param("id") Long id);
}