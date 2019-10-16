package com.codeoftheweb.salvo.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    private Date creationDate;
    @OneToMany(mappedBy = "game", cascade = CascadeType.MERGE)
    private List<GamePlayer> gamePlayers;

    //Empty Constructor
    public Game() {
    }

    //Constructor with parameters
    public Game(Long id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<GamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }

    //método para establecer la relación entre un objeto Game y un objeto GamePlayer
    public void addGamePlayer(GamePlayer gamePlayer) {
        //se agrega el gamePlayer que ingresa como parámetro al set declarado en los atributos
        this.gamePlayers.add(gamePlayer);
        //al gamePlayer ingresado se le agrega este game mediante su setter en la clase GamePlayer
        gamePlayer.setGame(this);
    }

    //método que retorna todos los players relacionados con el game a partir de los gamePlayers
    public List<Player> getPlayers() {
        return this.gamePlayers.stream().map(gp -> gp.getPlayer()).collect(Collectors.toList());
    }

    //DTO (data transfer object) para administrar la info de Game
    public Map<String, Object> gameDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("created", this.getCreationDate());
        dto.put("gamePlayers", this.getGamePlayers().stream().map(GamePlayer::gamePlayerDTO).collect(Collectors.toList()));
        return dto;
    }


    //toString Method
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                '}';
    }
}
