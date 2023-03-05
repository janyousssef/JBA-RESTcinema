package cinema.controllers;

import cinema.errors.SeatPurchaseProblem;
import cinema.models.Cinema;
import cinema.models.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class Controller {
    Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getAllSeats() {
        return new ResponseEntity<>(cinema, HttpStatus.OK);
    }

    @GetMapping("/seat")
    public ResponseEntity<Seat> getSeatByRowCol(@RequestParam Integer row, @RequestParam Integer col) {
        Seat seat = cinema.getSeat(row, col);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity purchase(@RequestBody Seat seat) {

        if (seat == null || seat.getColumn() == null || seat.getRow() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (seat.invalidInstance())
            return new ResponseEntity<>(new SeatPurchaseProblem("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);

        seat = cinema.getSeat(seat.getRow(), seat.getColumn());
        if (seat.isReserved())
            return new ResponseEntity<>(new SeatPurchaseProblem("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(cinema.reserveSeat(seat), HttpStatus.OK);

    }

}
