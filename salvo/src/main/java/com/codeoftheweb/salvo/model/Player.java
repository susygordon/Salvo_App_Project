package com.codeoftheweb.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private long id;

	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String email;

	private String name;

	@NotNull
	@NotEmpty
	private String password;

	@OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
	Set<GamePlayer> gamePlayers;

	@OneToMany(mappedBy = "player",fetch = FetchType.EAGER)
	private List<Score> scores;

	public Player() {
	}

	public Player(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public String getPassword() {return password; }

	public void setPassword(String password) {this.password = password;}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<GamePlayer> getGamePlayers() {
		return gamePlayers;
	}

	public void addGamePlayer(GamePlayer gamePlayer) {
		gamePlayer.setPlayer(this);
		this.gamePlayers.add(gamePlayer);
	}

	public void setGamePlayers(Set<GamePlayer> gamePlayers) {
		this.gamePlayers = gamePlayers;
	}

	public List<Game> getGames() {
		return gamePlayers.stream().map(sub -> sub.getGame()).collect(toList());
	}

	public Map<String, Object> toDTO() {
		Map<String, Object> dto = new LinkedHashMap<String, Object>();
		dto.put("id", getId());
		dto.put("email", getEmail());
		return dto;
	}

	public void setLastLogin(Date date) {

	}

	public Score getScore(Game game) {
		return this.getScores()
				.stream()
				.filter(score -> score.getGame().getId() == game.getId())
				.findFirst().orElse(null);
	}

	public Double getTotalScore(){
		return getScores()
				.stream()
				.mapToDouble(Score::getScore).sum();
	}

	public Long getTotalWins(){
		return getScores()
				.stream()
				.filter(score -> score.getScore() == 1d)
				.count();
	}

	public Long getTotalLoses(){
		return getScores()
				.stream()
				.filter(score -> score.getScore() == 0d)
				.count();
	}

	public Long getTotalTies(){
		return getScores()
				.stream()
				.filter(score -> score.getScore() == 0.5d)
				.mapToDouble(Score::getScore).count();
	}
}
