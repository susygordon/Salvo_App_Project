package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private Date joinDate;

    @ManyToOne
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Game game;

    @JoinColumn(name = "game_player_id_id")
    @OneToMany (fetch = FetchType.EAGER)
    private List<Ship> ships;

    @OneToMany
    private List<Salvo> salvos;

    //Empty Constructor
    public GamePlayer() {
    }

    public GamePlayer(Game game, Player player, Date joinDate) {
        this.game = game;
        this.player = player;
        this.joinDate = joinDate;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Salvo> getSalvos() {
        return salvos;
    }

    public void setSalvos(List<Salvo> salvos) {
        this.salvos = salvos;
    }

    public void addShip(Ship ship){
        this.ships.add(ship);
        ship.setGamePlayerId(this);
    }

    //toString Method
    @Override
    public String toString() {
        return "GamePlayer{" +
                "id=" + id +
                ", joinDate=" + joinDate +
                ", player=" + player +
                ", game=" + game +
                '}';
    }

    //DTO (data transfer object) para administrar la info de GamePlayer
    public Map<String, Object> gamePlayerDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("player", this.getPlayer().playerDTO());
        return dto;
    }
}
