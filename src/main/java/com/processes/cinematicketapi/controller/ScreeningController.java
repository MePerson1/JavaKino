package com.processes.cinematicketapi.controller;

import com.processes.cinematicketapi.interfaces.IScreeningService;
import com.processes.cinematicketapi.models.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/screening")
public class ScreeningController
{
    private final IScreeningService _screeningService;

    @Autowired
    public ScreeningController(IScreeningService screeningService)
    {
        _screeningService = screeningService;
    }

    @GetMapping
    ResponseEntity<List<Screening>> getAll()
    {
        List<Screening> screenings = _screeningService.getAllScreenings();
        if(screenings.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(screenings,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Screening> GetMovieById(@PathVariable Long id)
    {
        Screening screening = _screeningService.getScreeningById(id);
        return new ResponseEntity<>(screening,HttpStatus.OK);
    }

    @GetMapping("title/{title}")
    ResponseEntity<List<Screening>> getByTitle(@PathVariable String title)
    {
        List<Screening> screenings = _screeningService.getScreeningsByMovieTitle(title);
        if(screenings.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(screenings,HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody Screening newScreening)
    {
        try
        {
            Screening screening = _screeningService.save(newScreening);
            return new ResponseEntity<>(screening,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Failed to create customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        try
        {
            boolean isDeleted = _screeningService.deleteById(id);

            if (isDeleted)
            {
                return new ResponseEntity<>("Screening deleted successfully", HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Screening with ID " + id + " does not exist or couldn't be deleted", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Error deleting screening", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
