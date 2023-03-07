package cinema.controllers;

import cinema.models.Cinema;
import cinema.models.Seat;
import cinema.services.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController()
public class Controller {
    CinemaService cinemaService = new CinemaService(new Cinema(9, 9));


    @GetMapping("/seats")
    public ResponseEntity<Cinema> getCinema() {
        return new ResponseEntity<>(cinemaService.getCinema(), HttpStatus.OK);
    }

    @GetMapping("/seat")
    public ResponseEntity<Seat> getSeatByRowCol(@RequestParam Integer row, @RequestParam Integer col) {
        Seat seat = cinemaService.getSeat(row, col);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public Map<String, Object> purchase(@RequestBody Seat seat) {
        seat = cinemaService.getSeat(seat);
        if (seat.isInvalidInstance()) {
            String INVALID_SEAT = "The number of a row or a column is out of bounds!";
            return Map.of("error", INVALID_SEAT);
        }

        if (seat.isReserved()) {
            String ALREADY_PURCHASED = "The ticket has been already purchased!";
            return Map.of("error", ALREADY_PURCHASED);
        }

        UUID id = cinemaService.reserveSeat(seat);
        return Map.of("token", id, "ticket", seat);

    }

    @PostMapping("/return")
    public Map<String, Object> returnSeat(@RequestBody String json) {
        UUID id = getUUIDFromJSON(json);


        if (cinemaService.seatIsNotPurchased(id)) return Map.of("error", "Wrong token!");
        return Map.of("returned ticket", cinemaService.returnSeat(id));

    }

    private UUID getUUIDFromJSON(String token) {
        return UUID.fromString(token.substring(token.indexOf(": ") + 3, token.length() - 4));
    }

}
