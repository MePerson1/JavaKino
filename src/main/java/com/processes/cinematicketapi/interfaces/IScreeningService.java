package com.processes.cinematicketapi.interfaces;

import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.models.Screening;

import java.util.List;

public interface IScreeningService
{
    public Screening getScreeningById(Long id);
    public List<Screening> getScreeningsByMovieTitle(String movieTitle);
    public Screening save(Screening screening);
    public boolean deleteById(Long id);
}
