package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Ship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Long id;

    @Enumerated(value=EnumType.STRING)
    private ShipType shipType;

    private String type;

    @ElementCollection
    @Column(name="location", nullable = false)
    @CollectionTable(name="SHIP_LOCATION", joinColumns = {@JoinColumn(name = "SHIP_ID")})
    private List<String> locations = new ArrayList<>();

	@JsonIgnore
	@ManyToOne
	private GamePlayer gamePlayerId;

	//Empty Constructor
    private Ship() {
	}

    public Ship(String type, List<String> locations) {
        this.type = type;
        this.locations = locations;
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

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getLocations() {
        return this.locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Map<String, Object> shipDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", this.getType());
        dto.put("locations", this.getLocations());
        return dto;
    }


    //to String Method
	@Override
	public String toString() {
		return "Ship{" +
				"id=" + id +
				", gamePlayerId=" + gamePlayerId +
				'}';
	}


}
