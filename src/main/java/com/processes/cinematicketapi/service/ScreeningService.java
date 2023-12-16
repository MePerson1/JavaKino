package com.processes.cinematicketapi.service;

import com.processes.cinematicketapi.exceptions.NoContentException;
import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.IScreeningService;
import com.processes.cinematicketapi.models.Screening;
import com.processes.cinematicketapi.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScreeningService implements IScreeningService {
    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public Screening getScreeningById(Long id) {
        return screeningRepository.findById(id).orElseThrow(() -> new NotFoundException("Screening with id: " + id + " not found!"));
    }

    @Override
    public List<Screening> getAllScreenings() {
        List<Screening> screenings = screeningRepository.findAll();
        if (screenings.isEmpty()) {
            throw new NoContentException("Cannot find any Screenings!");
        }
        return screenings;
    }

    @Override
    public List<Screening> getScreeningsByMovieTitle(String movieTitle) {
        List<Screening> screenings = screeningRepository.findByMovieTitle(movieTitle);
        if (screenings.isEmpty()) {
            throw new NotFoundException("Cannot find any screenings for movie " + movieTitle + '!');
        }
        return screenings;
    }

    @Override
    public Screening save(Screening screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public boolean deleteById(Long id) {
        return screeningRepository.deleteAndReturnStatusById(id) != 0;
    }
}