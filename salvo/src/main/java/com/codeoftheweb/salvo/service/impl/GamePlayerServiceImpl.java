package com.codeoftheweb.salvo.service.impl;

import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.service.GamePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamePlayerServiceImpl implements GamePlayerService {
    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Override
    public GamePlayer findById(Long id) {

        return gamePlayerRepository.findById(id).get();

    }

    @Override
    public List<GamePlayer> findAll() {
        return gamePlayerRepository.findAll();
    }
}
