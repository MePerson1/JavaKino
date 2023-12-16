package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Screening;

import java.util.List;

public interface IScreeningService {
    Screening getScreeningById(Long id);

    List<Screening> getScreeningsByMovieTitle(String movieTitle);

    Screening save(Screening screening);

    boolean deleteById(Long id);

    List<Screening> getAllScreenings();
}
