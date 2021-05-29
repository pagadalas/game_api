package com.rmed.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmed.game.entity.GameRound;

public interface GameRoundRepository extends JpaRepository<GameRound, Long> {


}
