package com.codeoftheweb.salvo.rest.controller;

import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.security.JWTRepository;
import com.codeoftheweb.salvo.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.codeoftheweb.salvo.security.SecurityConstants.HEADER_STRING;
import static com.codeoftheweb.salvo.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/players")
public class PlayerRestController {

	@Autowired
	private PlayerService playerService;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	public List<Player> findAll() {
		return playerService.findAll();
	}

	@GetMapping("/{id}")
	public Player findPlayerById(@PathVariable("id") Long id) {
		return playerService.findById(id);
	}

	@PostMapping
	public Player createPlayer(@RequestBody Player player) {
		return playerService.createPlayer(player);
	}

	@PutMapping
	public Player updatePlayer(@RequestBody Player player) {
		return playerService.updatePlayer(player);
	}

	@DeleteMapping("/{id}")
	public void deletePlayer(@PathVariable("id") Long id) {
		playerService.deleteById(id);
	}

	@DeleteMapping("/logout")
	public ResponseEntity<Object> logout(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");
		JWTRepository.getInstance().removeToken(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
