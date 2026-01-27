package com.landonfritsch.bettracker.bets.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sport sport;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BetType betType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BetStatus status = BetStatus.PENDING;

    @Column(nullable = false, length = 200)
    private String event;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal stakeUnits;

    @Column(nullable = false)
    private Integer americanOdds;

    @Column(precision = 8, scale = 2)
    private BigDecimal line;

    @Column(precision = 8, scale = 2)
    private BigDecimal profitUnits;

    @Column(nullable = false)
    private OffsetDateTime placedAt = OffsetDateTime.now();

    // ---- Getters & Setters ----

    public Long getId() {
        return id;
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

    public OffsetDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(OffsetDateTime placedAt) {
        this.placedAt = placedAt;
    }
}

