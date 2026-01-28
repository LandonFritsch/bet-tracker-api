package com.landonfritsch.bettracker.bets.dto;

import com.landonfritsch.bettracker.bets.model.BetStatus;
import com.landonfritsch.bettracker.bets.model.BetType;
import com.landonfritsch.bettracker.bets.model.Sport;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateBetRequest {

    @NotNull
    private Sport sport;

    @NotNull
    private BetType betType;

    @NotNull
    private BetStatus status;

    @NotBlank
    private String event;

    @NotNull
    private BigDecimal stakeUnits;

    @NotNull
    private Integer americanOdds;

    private BigDecimal line;

    private BigDecimal profitUnits;

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

    public void setStakeUnits(BigDecimal stakeUnits) {
        this.stakeUnits = stakeUnits;
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
}
