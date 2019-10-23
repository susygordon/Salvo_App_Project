package com.codeoftheweb.salvo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sarasa")
public class SarasaController {

    @GetMapping
    public Object returnObject() {

        return new Object();
    }


    @PostMapping
    public Object metodoQueFalleSiNoTengoUnId
            (@RequestBody Map<String, Object> sarasa) {
        if(sarasa.get("id")== null) {
            throw new RuntimeException();
        }
        return sarasa;
    }

    @GetMapping("/conBody")
    public Object testDeGet
            (@RequestParam("fileOculto") Boolean fileOculto, @RequestParam("saveOptions") Boolean saveOptions) {
        if(fileOculto) {
            throw new RuntimeException();
        }
        return new Object();
    }
}