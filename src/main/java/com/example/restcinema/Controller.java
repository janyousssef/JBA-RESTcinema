package com.example.restcinema;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/test")
    public ResponseEntity web() {
        return new ResponseEntity("great", HttpStatusCode.valueOf(200));
    }
}
