package com.codeoftheweb.salvo.service;

import com.codeoftheweb.salvo.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
	Player findById(Long id);

	List<Player> findAll();

	Player createPlayer(Player player);

	Player updatePlayer(Player player);

	void deleteById(Long id);
}
