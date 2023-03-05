package cinema.controllers;

import cinema.models.Cinema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class Controller {
    @GetMapping("/seats")
    public ResponseEntity<Cinema> web() {
        Logger logger = LoggerFactory.getLogger(Controller.class);
        logger.info("controller called");
        return new ResponseEntity<>(new Cinema(9,9), HttpStatus.OK);
    }
}
