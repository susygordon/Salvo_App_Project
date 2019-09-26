package com.codeoftheweb.salvo.rest.controller;

import com.codeoftheweb.salvo.exception.GameNotFoundException;
import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/game")
@RestController
public class GameRestController {
	@Autowired
	private GameService gameService;

	@GetMapping
	public List<Game> findAll() {
		return gameService.findAll();
	}

	@GetMapping("/{id}")
	public Game findGameById(@PathVariable("id") Long id) {
		return gameService.findById(id).orElseThrow(() -> new GameNotFoundException(id));
	}

	@PostMapping
	public Game createGame(@RequestBody Game game) {
		return gameService.createGame(game);
	}

	@PostMapping("/{id}/player")
	public Game joinPlayerToGame(@PathVariable("id") Long id) {
		return gameService.joinPlayerToGame(id);
	}

}
