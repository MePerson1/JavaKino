package com.processes.cinematicketapi.controller;

import com.processes.cinematicketapi.dto.ScreeningDto;
import com.processes.cinematicketapi.exceptions.NotFoundException;
import com.processes.cinematicketapi.interfaces.IMovieService;
import com.processes.cinematicketapi.interfaces.IScreeningService;
import com.processes.cinematicketapi.models.Movie;
import com.processes.cinematicketapi.models.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/screening")
public class ScreeningController {
    private final IScreeningService _screeningService;
    private final IMovieService _movieService;

    @Autowired
    public ScreeningController(IScreeningService screeningService, IMovieService movieService) {
        _screeningService = screeningService;
        _movieService = movieService;
    }

    @GetMapping
    ResponseEntity<List<Screening>> getAll() {
        List<Screening> screenings = _screeningService.getAllScreenings();
        return new ResponseEntity<>(screenings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Screening> getById(@PathVariable Long id) {
        Screening screening = _screeningService.getScreeningById(id);
        return new ResponseEntity<>(screening, HttpStatus.OK);
    }

    @GetMapping("title/{title}")
    ResponseEntity<List<Screening>> getByTitle(@PathVariable String title) {
        List<Screening> screenings = _screeningService.getScreeningsByMovieTitle(title);
        if (screenings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(screenings, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody ScreeningDto newScreeningDto) {
        try {
            Movie movie = _movieService.getMovieById(newScreeningDto.getMovieId());

            Screening screening = new Screening();
            screening.setMovie(movie);
            screening.setDate(newScreeningDto.getDate());
            screening.setTicketCount(newScreeningDto.getTicketCount());
            screening.setTicketPrice(newScreeningDto.getTicketPrice());
            screening.setRoomNumber(newScreeningDto.getRoomNumber());

            Screening savedScreening = _screeningService.save(screening);

            return new ResponseEntity<>(savedScreening, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>("Failed to create screening: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody ScreeningDto screening, @PathVariable Long id) {
        try {
            Screening existingScreening = _screeningService.getScreeningById(id);
            Movie existingMovie = _movieService.getMovieById(screening.getMovieId());

            existingScreening.setMovie(existingMovie);
            existingScreening.setRoomNumber(screening.getRoomNumber());
            existingScreening.setDate(screening.getDate());
            existingScreening.setTicketPrice(screening.getTicketPrice());
            existingScreening.setTicketCount(screening.getTicketCount());

            Screening updatedScreening = _screeningService.save(existingScreening);

            return new ResponseEntity<>(updatedScreening, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>("Failed to update screening: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isDeleted = _screeningService.deleteById(id);

        if (isDeleted) {
            return new ResponseEntity<>("Screening deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Screening with ID " + id + " does not exist or couldn't be deleted", HttpStatus.NOT_FOUND);
        }
    }
}
