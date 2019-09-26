package com.codeoftheweb.salvo.service.impl;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

	@Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);

    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

	@Override
	public Game createGame(Game game) {
    	game.setCreationDate(new Date());
		return gameRepository.save(game);
	}

	@Override
	public Game joinPlayerToGame(Long id) {
		gameRepository.findById(id);
		return null;
	}
}
