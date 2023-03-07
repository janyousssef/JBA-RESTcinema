package cinema.controllers;

import cinema.errors.SeatPurchaseProblem;
import cinema.models.Cinema;
import cinema.models.Seat;
import cinema.services.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class Controller {
    final String INVALID_SEAT = "The number of a row or a column is out of bounds!";
    final String ALREADY_PURCHASED = "The ticket has been already purchased!";
    CinemaService cinemaService = new CinemaService(new Cinema(9, 9));

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getAllSeats() {
        return new ResponseEntity<>(cinemaService.getCinema(), HttpStatus.OK);
    }

    @GetMapping("/seat")
    public ResponseEntity<Seat> getSeatByRowCol(@RequestParam Integer row, @RequestParam Integer col) {
        Seat seat = cinemaService.getSeat(row, col);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity purchase(@RequestBody Seat seat) {

        if (seat.invalidInstance())
            return new ResponseEntity<>(new SeatPurchaseProblem(INVALID_SEAT), HttpStatus.BAD_REQUEST);

        seat = cinemaService.getSeat(seat.getRow(), seat.getColumn());
        if (seat.isReserved())
            return new ResponseEntity<>(new SeatPurchaseProblem(ALREADY_PURCHASED), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(cinemaService.reserveSeat(seat), HttpStatus.OK);

    }

}
