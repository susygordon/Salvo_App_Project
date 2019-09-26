package com.codeoftheweb.salvo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class SalvoLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Salvo salvoId;

    //Es un String con las coordenadas por ejemplo "A10"
    @NotNull
    @NotEmpty
    private String cell;

    //Empty Constructor
    public SalvoLocations() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Salvo getSalvoId() {
        return salvoId;
    }

    public void setSalvoId(Salvo salvoId) {
        this.salvoId = salvoId;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "SalvoLocations{" +
                "id=" + id +
                ", salvoId=" + salvoId +
                ", cell='" + cell + '\'' +
                '}';
    }
}
