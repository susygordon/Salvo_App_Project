package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.dto.SalvoDTO;
import com.codeoftheweb.salvo.model.*;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;


    @RequestMapping("/games")
    public List<Map<String,Object>> getGames(){
        List<Game> gamesList = this.gameRepository.findAll();
        List<Map<String,Object>> gamesMap = new ArrayList<>();
        //meti el new map adentro del for por que me pasaba esto https://stackoverflow.com/questions/4100486/java-create-a-list-of-hashmaps
        List<Map<String,Object>> gamePlayerMap = new ArrayList<>();

        for(Game g:gamesList){
            Map<String,Object> gameMap = new LinkedHashMap<>();
            gameMap.put("id",g.getId());
            gameMap.put("created",g.getCreationDate());
            for(GamePlayer gamePlayer: g.getGamePlayers()){
                Map<String,Object> gamesPlayers = new HashMap<>();
                gamesPlayers.put("id",gamePlayer.getId());
                Map<String,Object> player = new HashMap<>();
                player.put("id",gamePlayer.getPlayer().getId());
                player.put("email",gamePlayer.getPlayer().getEmail());
                gamesPlayers.put("player",player);
                gamePlayerMap.add(gamesPlayers);
            }


            gameMap.put("gamePlayers",gamePlayerMap);
            gameMap.put("scores:",g.getScores());
            gamePlayerMap = new ArrayList<>();
            gamesMap.add(gameMap);


        }
        return gamesMap;


    }

    private List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @RequestMapping("/leaderboard")
    public List<Map<String,Object>> getScoreView(){
        List<Map<String,Object>> finalList = new ArrayList<>();
        this.getAllPlayers().forEach(player -> {
            if(!player.getScores().isEmpty()) {
                Map<String, Object> dto = new LinkedHashMap<>();
                Map<String, Object> dtoScore = new LinkedHashMap<>();
                dtoScore.put("total",player.getTotalScore() );
                dtoScore.put("won", player.getTotalWins());
                dtoScore.put("lost", player.getTotalLoses());
                dtoScore.put("tied", player.getTotalTies());
                dto.put("name",player.getEmail());
                dto.put("score",dtoScore);
                finalList.add(dto);
            }
        });
        return finalList;
    }
    @RequestMapping("/game_view/{id}")
    public Map<String,Object> getGameView(@PathVariable Integer id){
        Game game = this.gameRepository.getOne(id);
        Map<String,Object> gameMap= new LinkedHashMap<>(); // ordena los elementos segun se van cargando
        gameMap.put("id",game.getId());
        gameMap.put("created",game.getCreationDate());
        List<Map<String,Object>>gamePlayersList  = new ArrayList<>(); // lista para los GamePlayers
        List<Map<String,Object>> shipsList = new ArrayList<>();
        List<SalvoDTO> salvoJson = new ArrayList<>();

        for(GamePlayer gamePlayer:game.getGamePlayers()){
            Map<String,Object> gamePlayerMap = new HashMap<>(); //map para cada game Player
            gamePlayerMap.put("id",gamePlayer.getId());
            Map<String,Object> playerMap = new HashMap<>();
            playerMap.put("id",gamePlayer.getPlayer().getId());
            playerMap.put("email",gamePlayer.getPlayer().getEmail());
            gamePlayerMap.put("player",playerMap);
            gamePlayersList.add(gamePlayerMap); // lo agrego a la lista
            /* mappeo ships */
            for(Ship ship :gamePlayer.getShips()){
                Map<String,Object> shipMap = new HashMap<>();
                shipMap.put("type", ship.getType());
                shipMap.put("locations",ship.getLocations());
                shipsList.add(shipMap);
            }
            //--mapeo salvo  -----//
            for(Salvo salvo: gamePlayer.getSalvoes()){
                SalvoDTO salvoDTO = new SalvoDTO();
                salvoDTO.setTurn(salvo.getTurn());
                salvoDTO.setPlayerID((int) gamePlayer.getPlayer().getId());
                List<String> cells = new ArrayList<>();
                for (String salvoLoc:salvo.getLocations()) {
                    cells.add(salvoLoc);
                }
                salvoDTO.setLocations(cells);

                salvoJson.add(salvoDTO);

            }

        }
        gameMap.put("gamePlayers",gamePlayersList); // lo agrego al map general
        gameMap.put("ships",shipsList);
        gameMap.put("salvoes",salvoJson);

        return gameMap;

    }
}