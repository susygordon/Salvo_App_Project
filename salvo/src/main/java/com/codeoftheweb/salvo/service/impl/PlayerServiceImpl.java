package com.codeoftheweb.salvo.service.impl;

import com.codeoftheweb.salvo.exception.PlayerNotExistException;
import com.codeoftheweb.salvo.exception.UsernameExistException;
import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import com.codeoftheweb.salvo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Player> findById(Long id){
        return playerRepository.findById(id);

    }
	@Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

	@Override
	public Player createPlayer(Player player) {
		Optional<Player> playerByUser = playerRepository.findByUserName(player.getUserName());
		if(playerByUser.isPresent()) {
			throw new UsernameExistException();
		}
		player.setPassword(passwordEncoder.encode(player.getPassword()));
    	return playerRepository.save(player);
	}

	@Override
	public Player updatePlayer(Player player) {
		Player persistedPlayerById;
    	Optional<Player> playerByUser = playerRepository.findByUserName(player.getUserName());
    	if(playerByUser.isPresent()){
    		if(playerByUser.get().getId().equals(player.getId())) {
				persistedPlayerById = playerByUser.get();
			} else {
    			throw new UsernameExistException();
		    }
	    }  else {
		    persistedPlayerById = playerRepository.findById(player.getId()).orElseThrow(() -> new PlayerNotExistException(player.getId()));
	    }
    	return playerRepository.save(mergePlayer(persistedPlayerById, player));
	}

	@Override
	public void deleteById(Long id) {
		playerRepository.deleteById(id);
	}

	private Player mergePlayer(Player persistedPlayer, Player player) {
		persistedPlayer.setLastName(player.getLastName());
		persistedPlayer.setFirstName(player.getFirstName());
		persistedPlayer.setUserName(player.getUserName());
		persistedPlayer.setPassword(passwordEncoder.encode(player.getPassword()));
		return persistedPlayer;
	}
}
