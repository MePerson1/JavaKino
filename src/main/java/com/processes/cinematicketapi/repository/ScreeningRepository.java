package com.processes.cinematicketapi.repository;

import com.processes.cinematicketapi.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ScreeningRepository extends JpaRepository<Screening, Long>
{
    List<Screening> findByMovieTitle(String movieTitle);
    public List<Screening> findAll();
}