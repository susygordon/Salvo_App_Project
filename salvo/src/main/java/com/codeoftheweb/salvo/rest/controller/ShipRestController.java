package com.codeoftheweb.salvo.rest.controller;

import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.model.Ship;
import com.codeoftheweb.salvo.service.PlayerService;
import com.codeoftheweb.salvo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/ship")
@RestController
public class ShipRestController {
    @Autowired
    private ShipService shipService;

    @GetMapping
    public List<Ship> findAll() {
        return shipService.findAll();

    }

    @GetMapping("/{id}")
    public Ship findShipById(@PathVariable("id") Long id) {
        //return shipService.findById(id).orElseThrow(()->new RuntimeException());
        return shipService.findById(id).get();
    }
}
