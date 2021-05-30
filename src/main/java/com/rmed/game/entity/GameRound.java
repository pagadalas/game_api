package com.rmed.game.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;

/**
 * @author Spagadala
 * This class represents the domin/entity for storing the information of GameRound
 */
@Entity
@ApiModel(value = "GameRound details", description = "Details of the Game round, includes playerId, shape, status of the game")
public class GameRound implements Serializable {
	
	private static final long serialVersionUID = -1043238083975456174L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "player_id", nullable = false)
	private Player player;
	
	private String playerShape;
	
	private String appShape;
	
	private String status;
	
	private Date playDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getPlayerShape() {
		return playerShape;
	}

	public void setPlayerShape(String playerShape) {
		this.playerShape = playerShape;
	}

	public String getAppShape() {
		return appShape;
	}

	public void setAppShape(String appShape) {
		this.appShape = appShape;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}
	
}
