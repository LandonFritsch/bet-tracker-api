package com.landonfritsch.bettracker.bets.dto;

import com.landonfritsch.bettracker.bets.model.BetStatus;
import com.landonfritsch.bettracker.bets.model.BetType;
import com.landonfritsch.bettracker.bets.model.Sport;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BetResponse {

    private Long id;
    private Sport sport;
    private BetType betType;
    private BetStatus status;
    private String event;
    private BigDecimal stakeUnits;
    private Integer americanOdds;
    private BigDecimal line;
    private BigDecimal profitUnits;
    private OffsetDateTime placedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public BetType getBetType() {
        return betType;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public BigDecimal getStakeUnits() {
        return stakeUnits;
    }

    public void setStakeUnits(BigDecimal stakeDecimal) {
        this.stakeUnits = stakeDecimal;
    }

    public Integer getAmericanOdds() {
        return americanOdds;
    }

    public void setAmericanOdds(Integer americanOdds) {
        this.americanOdds = americanOdds;
    }

    public BigDecimal getLine() {
        return line;
    }

    public void setLine(BigDecimal line) {
        this.line = line;
    }

    public BigDecimal getProfitUnits() {
        return profitUnits;
    }

    public void setProfitUnits(BigDecimal profitUnits) {
        this.profitUnits = profitUnits;
    }

    public OffsetDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(OffsetDateTime placedAt) {
        this.placedAt = placedAt;
    }
}
