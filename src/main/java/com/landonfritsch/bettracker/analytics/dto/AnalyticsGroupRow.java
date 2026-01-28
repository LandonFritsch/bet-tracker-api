package com.landonfritsch.bettracker.analytics.dto;

import java.math.BigDecimal;

public class AnalyticsGroupRow {

    private String key;              // e.g. "NBA" or "WON"
    private long totalBets;
    private BigDecimal totalUnitsWagered;
    private BigDecimal totalProfitLoss;
    private double winPercentage;    // 0..100 within this group

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(long totalBets) {
        this.totalBets = totalBets;
    }

    public BigDecimal getTotalUnitsWagered() {
        return totalUnitsWagered;
    }

    public void setTotalUnitsWagered(BigDecimal totalUnitsWagered) {
        this.totalUnitsWagered = totalUnitsWagered;
    }

    public BigDecimal getTotalProfitLoss() {
        return totalProfitLoss;
    }

    public void setTotalProfitLoss(BigDecimal totalProfitLoss) {
        this.totalProfitLoss = totalProfitLoss;
    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }
}

