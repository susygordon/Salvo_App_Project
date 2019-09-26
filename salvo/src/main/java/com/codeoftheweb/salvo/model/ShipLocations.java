package com.codeoftheweb.salvo.model;

public class ShipLocations {
    private Ship shipId;
    private String cell;

    //Empty Constructor
    public ShipLocations() {
    }

    //Constructor with parameters
    public ShipLocations(Ship shipId, String cell) {
        this.shipId = shipId;
        this.cell = cell;
    }

    //Getters and Setters
    public Ship getShipId() {
        return shipId;
    }

    public void setShipId(Ship shipId) {
        this.shipId = shipId;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    //toString Method
    @Override
    public String toString() {
        return "ShipLocations{" +
                "shipId=" + shipId +
                ", cell='" + cell + '\'' +
                '}';
    }
}
