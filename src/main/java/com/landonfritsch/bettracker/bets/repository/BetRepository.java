package com.landonfritsch.bettracker.bets.repository;

import com.landonfritsch.bettracker.bets.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
