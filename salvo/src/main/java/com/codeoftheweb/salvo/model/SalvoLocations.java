/*
package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SalvoLocations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="salvoID")
    @JsonIgnore
    private Salvo salvo;
    private String cell;

    public SalvoLocations(Salvo salvo, String cell) {
        this.salvo = salvo;
        this.cell = cell;
    }
    public SalvoLocations() {
    }

    public Salvo getSalvo() {
        return salvo;
    }

    public void setSalvo(Salvo salvo) {
        this.salvo = salvo;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "SalvoLocation{" +

                ", cell='" + cell + '\'' +
                '}';
    }
}*/
