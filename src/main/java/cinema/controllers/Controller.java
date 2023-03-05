package cinema.controllers;

import cinema.models.Cinema;
import cinema.models.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class Controller {
    Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getAllSeats() {
        return new ResponseEntity<>(cinema, HttpStatus.OK);
    }

    @GetMapping("/seat")
    public ResponseEntity<Seat> getSeatByRowCol(@RequestParam Integer row,@RequestParam Integer col) {
        Seat seat = cinema.getSeat(row, col);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }
}
