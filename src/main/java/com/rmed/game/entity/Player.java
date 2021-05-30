package com.rmed.game.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModel;

/**
 * This class represents the entity for Player object.
 * This holds name of the player, and have association with GameRound entity
 */
@Entity
@ApiModel(value = "Player details", description = "Details of the Player")
public class Player implements Serializable {
	
	private static final long serialVersionUID = 7779069337284621L;

	@Id
	private Integer id;
	
	private String name;
		
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="player_id", referencedColumnName="id")
	private Set<GameRound> gameRounds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<GameRound> getGameRounds() {
		return gameRounds;
	}

	public void setGameRounds(Set<GameRound> gameRounds) {
		this.gameRounds = gameRounds;
	}
	
}
