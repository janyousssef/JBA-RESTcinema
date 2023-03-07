package cinema.services;

import cinema.models.Cinema;
import cinema.models.Seat;

public class CinemaService {
    private final Cinema cinema;

    public CinemaService(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Seat getSeat(Integer row, Integer col) {
        row--;
        col--;
        return cinema.getSeats()[row * cinema.TOTAL_ROWS + col];
    }

    public Seat reserveSeat(Seat seat) {
        return seat.reserve();
    }

    public Integer getSeatIndex(Seat seat) {
        return seat.getRow() * cinema.TOTAL_ROWS + seat.getColumn();
    }
}