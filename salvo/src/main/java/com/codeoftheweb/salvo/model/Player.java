package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Player implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Long id;

	private String firstName;

	private String lastName;

	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String userName;

	@NotNull
	@NotEmpty
	private String password;
	private Date lastLogin;

	@OneToMany (fetch = FetchType.EAGER)
	private Set<GamePlayer> gamePlayerList;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    private Set<GamePlayer> gamePlayers = new HashSet<>();

	//Empty Constructor
	public Player() {
	}

	//Constructor with parameters
	public Player(String firstName, String lastName, String userName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
       // this.xp = 0;
	}


	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public Set<GamePlayer> getGamePlayers(){
        return this.gamePlayers;
    }

	//toString Method
	@Override
	public String toString() {
		return "Player{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", userName='" + userName + '\'' +
				'}';
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(RoleEnum.PLAYER.name()));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<GamePlayer> getGamePlayerList() {
		return (List<GamePlayer>) gamePlayerList;
	}

	public void setGamePlayerList(List<GamePlayer> gamePlayerList) {
		this.gamePlayerList = (Set<GamePlayer>) gamePlayerList;
	}

    //método para establecer la relación entre un objeto Player y un objeto GamePlayer
    public void addGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayers.add(gamePlayer);
        gamePlayer.setPlayer(this);
    }

    //método que retorna todos los games relacionados con el player a partir de los gamePlayers
    @JsonIgnore
    public List<Game> getGames() {
        return this.gamePlayers.stream().map(x -> x.getGame()).collect(Collectors.toList());
    }

    //métodos (comportamientos de los objetos del tipo Player)
    public String greet(){
        return "Hi! my name is " + this.firstName;
    }

    public String completeName(){
        return this.firstName + " " + this.lastName;
    }

   /* public String getExperience(){
        if(this.xp < 5000){
            return "newbie";
        } else if(this.xp < 25000){
            return "amateur";
        } else if(this.xp < 50000){
            return "pro";
        } else{
            return "legend";
        }
    }*/

    //DTO (data transfer object) para administrar la info de Player
    public Map<String, Object> playerDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("username", this.getUserName());
        return dto;
    }

}
