package com.example.restcinema.models;

public class Cinema {

    private final Integer TOTAL_ROWS = 9;
    private final Integer TOTAL_COLUMNS = 9;
    Seat[] availableSeats = new Seat[TOTAL_ROWS * TOTAL_COLUMNS];

    public Cinema() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                availableSeats[i * TOTAL_ROWS + j ] = new Seat(i + 1, j + 1);
            }
        }
    }

    public Integer getTOTAL_ROWS() {
        return TOTAL_ROWS;
    }

    public Integer getTOTAL_COLUMNS() {
        return TOTAL_COLUMNS;
    }
    public Seat[] getAvailableSeats() {
        return availableSeats;
    }

}
