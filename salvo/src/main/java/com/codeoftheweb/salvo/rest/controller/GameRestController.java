package com.codeoftheweb.salvo.rest.controller;

import com.codeoftheweb.salvo.exception.GameNotFoundException;
import com.codeoftheweb.salvo.model.*;
import com.codeoftheweb.salvo.service.GamePlayerService;
import com.codeoftheweb.salvo.service.GameService;
import com.codeoftheweb.salvo.service.PlayerService;
import com.codeoftheweb.salvo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class GameRestController {
	@Autowired
	private GameService gameService;

    @Autowired
	private GamePlayerService gamePlayerService;

    @Autowired
    private PlayerService playerService;

	@GetMapping
	public List<Game> findAll() {
		return gameService.findAll();
	}

	@GetMapping("/{id}")
	public Game findGameById(@PathVariable("id") Long id) {
		Game game = new Game();
		try{
		 	game= 	gameService.findById(id);
		}catch(GameNotFoundException gameException){
			System.out.println("El game no existe:"+ gameException.getMessage());
		}
		return game;
	}

	@PostMapping
	public Game createGame(@RequestBody Game game) {
		return gameService.createGame(game);
	}

	@PostMapping("/{id}/player")
	public Game joinPlayerToGame(@PathVariable("id") Long id) {
		return gameService.joinPlayerToGame(id);
	}

	@RequestMapping("/game_view/{id}")
	public Map<String, Object> mapDTO(@PathVariable Long id){
		Game game = null;
		try{
			game = gameService.findById(id);
            return mapGameToGameDTO(game);
		}catch (Exception e){
			System.out.println("No se encontro el game con id:"+id+" error:"+e.getMessage());
			throw e;
		}

    }

	private Map<String, Object> mapGameToGameDTO(Game game) {
        Map<String, Object> gameDTO = new HashMap<>();
        gameDTO.put("id" , game.getId());
        gameDTO.put("created" , game.getCreationDate());
        gameDTO.put("gamePlayers", mapGamePlayersToDTO(game.getGamePlayers()));
        /**Necesito recorrer la lista List<GamePlayer> gamePlayer y obtener los id mediante for*/
        return gameDTO;
    }

    private List<Map<String, Object>> mapGamePlayersToDTO(List<GamePlayer> gamePlayers) {
        List<Map<String,Object>> gamePlayerList = new ArrayList<>();
        for(GamePlayer gamePlayer: gamePlayers){
            Map<String,Object> gamePlayerMap = new HashMap<>();
            gamePlayerMap.put("id",gamePlayer.getId());
            Map<String,Object> playerMap = new HashMap<>();
            playerMap.put("id",gamePlayer.getPlayer().getId());
            playerMap.put("email",gamePlayer.getPlayer().getUserName());
            gamePlayerMap.put("player",playerMap);
            gamePlayerMap.put("salvos", mapSalvoToSalvoDTO(gamePlayer.getSalvos()));
            gamePlayerMap.put("ships", mapShipToShipDTO(gamePlayer.getShips()));
            gamePlayerList.add(gamePlayerMap);

        }
        return gamePlayerList;
    }

	private List<Map<String, Object>> mapShipToShipDTO(List<Ship> ships) {
		return ships.stream().map(ship -> this.mapShipToShipDTO(ship)).collect(Collectors.toList());
	}

	private Map<String, Object> mapShipToShipDTO(Ship ship) {
		Map<String, Object> shipDTO = new HashMap<>();
		shipDTO.put("id", ship.getId());
		return shipDTO;
	}

	private List<Map<String, Object>> mapSalvoToSalvoDTO(List<Salvo> salvos) {
		return salvos.stream().map(salvo -> this.mapSalvoToSalvoDTO(salvo)).collect(Collectors.toList());
	}

	private Map<String, Object> mapSalvoToSalvoDTO(Salvo salvo) {
		Map<String, Object> salvoDTO = new HashMap<>();
		salvoDTO.put("id", salvo.getId());
		return salvoDTO;
	}

}


