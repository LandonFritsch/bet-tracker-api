package com.landonfritsch.bettracker.bets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sport sport;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BetType betType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BetStatus status = BetStatus.PENDING;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String event;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    @Digits(integer = 6, fraction = 2)
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal stakeUnits;

    @NotNull
    @Min(-10000)
    @Max(10000)
    @Column(nullable = false)
    private Integer americanOdds;

    @Digits(integer = 6, fraction = 2)
    @Column(precision = 8, scale = 2)
    private BigDecimal line;

    @Digits(integer = 6, fraction = 2)
    @Column(precision = 8, scale = 2)
    private BigDecimal profitUnits;

    @NotNull
    @Column(nullable = false, updatable = false)
    private OffsetDateTime placedAt = OffsetDateTime.now();

    // ---- Getters & Setters ----

    public void setId(Long id) {
        this.id = id;
    }

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

