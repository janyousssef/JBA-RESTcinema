package cinema.controllers;

import cinema.models.Cinema;
import cinema.models.Seat;
import cinema.services.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
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
    public ResponseEntity<Map<String, Object>> purchase(@RequestBody Seat seat) {
        if (seat.anyNulls())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if (seat.isInvalidInstance()) {
            String INVALID_SEAT = "The number of a row or a column is out of bounds!";
            return new ResponseEntity<>(Map.of("error", INVALID_SEAT), HttpStatus.BAD_REQUEST);
        }

        seat = cinemaService.getSeat(seat);
        if (seat.isReserved()) {
            String ALREADY_PURCHASED = "The ticket has been already purchased!";
            return new ResponseEntity<>(Map.of("error", ALREADY_PURCHASED), HttpStatus.BAD_REQUEST);
        }

        UUID id = cinemaService.reserveSeat(seat);
        return new ResponseEntity<>(Map.of("token", id, "ticket", seat), HttpStatus.OK);

    }

    @PostMapping("/return")
    public ResponseEntity<Map<String, Object>> returnSeat(@RequestBody String json) {
        UUID id = getUUIDFromJSON(json);


        if (cinemaService.seatIsNotPurchased(id))
            return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(Map.of("returned_ticket", cinemaService.returnSeat(id)), HttpStatus.OK);

    }

    @PostMapping("/stats")
    public ResponseEntity<Map<String, Integer>> statistics() {


        Map<String, Integer> stats = new HashMap<>(cinemaService.statistics());

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    private UUID getUUIDFromJSON(String token) {
        int beginIndex = token.indexOf("\"", token.indexOf(":")) + 1;
        int endIndex = token.lastIndexOf("\"");
        String substring = token.substring(beginIndex, endIndex);
        return UUID.fromString(substring);
    }

}
