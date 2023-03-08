package cinema.services;

import cinema.models.Cinema;
import cinema.models.Seat;

import java.util.HashMap;
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
        return (seat.getRow() - 1) * cinema.TOTAL_ROWS + seat.getColumn() - 1;
    }

    public Integer getSeatIndex(Integer row, Integer col) {
        return (row - 1) * cinema.TOTAL_ROWS + col - 1;
    }

    public boolean seatIsNotPurchased(UUID id) {
        return !cinema.isPurchased(id);
    }

    public Seat returnSeat(UUID id) {
        return cinema.returnSeat(id);
    }

    public HashMap<String, Integer> statistics() {
        HashMap<String, Integer> map = new HashMap<>();
        Integer income = (int) cinema
                .getPurchasedSeats()
                .values()
                .stream()
                .mapToLong(Seat::getPrice)
                .sum();

        map.put("current_income", income);
        map.put("number_of_purchased_tickets", cinema.getNumberOfSoldSeats());
        Integer numAvailibleSeats = cinema.TOTAL_ROWS * cinema.TOTAL_COLUMNS - cinema.getNumberOfSoldSeats();
        map.put("number_of_available_seats", numAvailibleSeats);

        return map;
    }
}