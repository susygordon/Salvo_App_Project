package com.codeoftheweb.salvo.model;

public enum ShipType {
    BARCO_1("barco1", 1);
    private Integer cantiPosition;
    private String name;
    private ShipType(String name, Integer cantPosition) {
        this.cantiPosition = cantPosition;
        this.name=name;
    }
    public Integer getCantiPosition() {
        return cantiPosition;
    }
    public String getName() {
        return name;
    }


}
