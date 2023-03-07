package cinema.services;

import cinema.models.Cinema;
import cinema.models.Seat;

import java.util.UUID;


public class CinemaService {
    private final Cinema cinema;

    public CinemaService(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Seat getSeat(Integer row, Integer col) {
        return cinema.getSeats()[getSeatIndex(row, col)];
    }

    public Seat getSeat(Seat seat) {
        return cinema.getSeats()[getSeatIndex(seat)];
    }

    public UUID reserveSeat(Seat seat) {
        UUID id = seat.reserve();
        cinema.registerPurchase(id, seat);
        return id;
    }

    public Integer getSeatIndex(Seat seat) {
        return seat.getRow() * cinema.TOTAL_ROWS + seat.getColumn() - 2;
    }

    public Integer getSeatIndex(Integer row, Integer col) {
        return row * cinema.TOTAL_ROWS + col - 2;
    }

    public boolean seatIsNotPurchased(UUID id) {
        return !cinema.isPurchased(id);
    }

    public Seat returnSeat(UUID id) {
        return cinema.returnSeat(id);
    }
}