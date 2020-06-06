package com.thescientist.coffeelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeLogController {

    CoffeeLogRepo service;

    @Autowired
    public CoffeeLogController(CoffeeLogRepo service) {
        this.service = service;
    }

    @PostMapping(value = "/coffee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCoffee(Recipe recipe) {
        return new ResponseEntity(service.add(recipe), HttpStatus.CREATED);
    }

}
