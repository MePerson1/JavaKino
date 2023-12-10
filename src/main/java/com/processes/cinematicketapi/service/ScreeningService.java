package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.IScreeningService;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.models.Screening;
import com.processes.cinematicketapi.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService implements IScreeningService
{
    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository)
    {
        this.screeningRepository = screeningRepository;
    }

    //Metody:
    public Screening getScreeningById(Long id)
    {
        return screeningRepository.findById(id).orElseThrow(() -> new NotFoundException("Screening not found with id: " + id));
    }

    public List<Screening> getScreeningsByMovieTitle(String movieTitle)
    {
        return screeningRepository.findByMovieTitle(movieTitle);
    }

    public Screening save(Screening screening)
    {
        return screeningRepository.save(screening);
    }

    public boolean deleteById(Long id)
    {
       if(screeningRepository.deleteAndReturnStatusById(id)!=0) return true;
        return false;
    }
}