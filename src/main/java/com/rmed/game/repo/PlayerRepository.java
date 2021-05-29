package com.rmed.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmed.game.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	Player findByName(String name);
}
