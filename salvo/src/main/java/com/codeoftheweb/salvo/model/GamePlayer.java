package com.codeoftheweb.salvo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date joinDate;
    @ManyToOne
    private Player player;
    @ManyToOne
    private Game game;
    @OneToMany
    private List<Ship> ships;
    @OneToMany
    private List<Salvo> salvos;

    //Empty Constructor
    public GamePlayer() {
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
}
