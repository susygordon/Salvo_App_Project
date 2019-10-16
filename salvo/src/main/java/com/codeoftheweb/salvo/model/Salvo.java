package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayerId;
    //Se van contando los turnos, cada disparo está asociado con el turno en que se hizo

    private Integer turn;

    @OneToMany
    private List<SalvoLocations> salvoLocations;

    @ElementCollection
    @Column(name="location", nullable = false)
    @CollectionTable(name="SALVO_LOCATION", joinColumns = {@JoinColumn(name = "SALVO_ID")})
    private List<String> locations = new ArrayList<>();

    //Empty Constructor
    public Salvo() {
    }

    //Constructor with parameters
    public Salvo(Long id, GamePlayer gamePlayerId, Integer turn) {
        this.id = id;
        this.gamePlayerId = gamePlayerId;
        this.turn = turn;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GamePlayer getGamePlayerId() {
        return gamePlayerId;
    }

    public void setGamePlayerId(GamePlayer gamePlayerId) {
        this.gamePlayerId = gamePlayerId;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public List<SalvoLocations> getSalvoLocations() {
        return salvoLocations;
    }

    public void setSalvoLocations(List<SalvoLocations> salvoLocations) {
        this.salvoLocations = salvoLocations;
    }

    @Override
    public String toString() {
        return "Salvo{" +
                "id=" + id +
                ", gamePlayerId=" + gamePlayerId +
                ", turn=" + turn +
                ", salvoLocations=" + salvoLocations +
                '}';
    }
}
