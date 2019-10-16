package com.codeoftheweb.salvo.rest.controller;

import com.codeoftheweb.salvo.model.Salvo;
import com.codeoftheweb.salvo.service.SalvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/salvo")
@RestController
public class SalvoRestController {

    @Autowired
    private SalvoService salvoService;

    @GetMapping
    public List<Salvo> findAll() {
        return salvoService.findAll();

    }

    @GetMapping("/{id}")
    public Salvo findSalvoById(@PathVariable("id") Long id) {
        //return salvoService.findById(id).orElseThrow(()->new RuntimeException());
        return salvoService.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
