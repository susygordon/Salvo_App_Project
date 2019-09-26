package com.codeoftheweb.salvo.service;

import com.codeoftheweb.salvo.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
	Optional<Game> findById(Long id);

	List<Game> findAll();

	Game createGame(Game game);

	Game joinPlayerToGame(Long id);
}
