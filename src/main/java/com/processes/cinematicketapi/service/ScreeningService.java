package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.IScreeningService;
import com.processes.cinematicketapi.models.Customer;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.models.Screening;
import com.processes.cinematicketapi.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScreeningService implements IScreeningService
{
    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository)
    {
        this.screeningRepository = screeningRepository;
    }

    //Metody:
    @Override
    public Screening getScreeningById(Long id)
    {
        return screeningRepository.findById(id).orElseThrow(() -> new NotFoundException("Screening not found with id: " + id));
    }

    @Override
    public List<Screening> getAllScreenings()
    {
        return screeningRepository.findAll();
    }

    @Override
    public List<Screening> getScreeningsByMovieTitle(String movieTitle)
    {
        return screeningRepository.findByMovieTitle(movieTitle);
    }

    @Override
    public Screening save(Screening screening)
    {
        return screeningRepository.save(screening);
    }

    @Override
    public boolean deleteById(Long id)
    {
       if(screeningRepository.deleteAndReturnStatusById(id)!=0) return true;
        return false;
    }
}