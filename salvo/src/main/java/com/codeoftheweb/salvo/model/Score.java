package com.codeoftheweb.salvo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private Game gameId;
    //private Player playerID;
    @OneToOne
    private GamePlayer gamePlayer;
    private Integer score;
    private Date finishDate;

    //Empty Constructor
    public Score() {
    }

    //Constructor with parameters
    public Score(Long id, Game gameId, Player playerID, Integer score, Date finishDate) {
        this.id = id;

        this.score = score;
        this.finishDate = finishDate;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    //toString Method
    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", score=" + score +
                ", finishDate=" + finishDate +
                '}';
    }
}
