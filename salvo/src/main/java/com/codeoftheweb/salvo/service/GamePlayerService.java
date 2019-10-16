package com.codeoftheweb.salvo.service;

import com.codeoftheweb.salvo.model.GamePlayer;

import java.util.List;
import java.util.Optional;

public interface GamePlayerService {
	GamePlayer findById(Long id);

	List<GamePlayer> findAll();
}
