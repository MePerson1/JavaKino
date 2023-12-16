package com.processes.cinematicketapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ScreeningDto {
    private Long movieId;
    private Date date;
    private int roomNumber;
    private int ticketCount;
    private double ticketPrice;
}
