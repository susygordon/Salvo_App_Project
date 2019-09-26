package com.codeoftheweb.salvo.rest.controller;

import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.service.GamePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/gamePlayer")
@RestController
public class GamePlayerRestController {
    @Autowired
    private GamePlayerService gamePlayerService;

    @GetMapping
    public List<GamePlayer> findAll() {
        return gamePlayerService.findAll();

    }

    @GetMapping("/{id}")
    public GamePlayer findGamePlayerById(@PathVariable("id") Long id) {
        //return gamePlayerService.findById(id).orElseThrow(()->new RuntimeException());
        return gamePlayerService.findById(id).get();
    }
}
