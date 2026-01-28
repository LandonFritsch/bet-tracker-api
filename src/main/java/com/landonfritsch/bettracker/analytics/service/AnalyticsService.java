package com.landonfritsch.bettracker.analytics.service;

import com.landonfritsch.bettracker.analytics.dto.AnalyticsGroupRow;
import com.landonfritsch.bettracker.analytics.dto.AnalyticsSummaryResponse;
import com.landonfritsch.bettracker.bets.model.Bet;
import com.landonfritsch.bettracker.bets.model.BetStatus;
import com.landonfritsch.bettracker.bets.repository.BetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final BetRepository betRepository;

    public AnalyticsService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public AnalyticsSummaryResponse getSummary() {
        List<Bet> bets = betRepository.findAll();

        BigDecimal totalUnitsWagered = sumStake(bets);
        BigDecimal totalProfitLoss = sumProfit(bets);

        long totalBets = bets.size();

        // Win% should be based on "settled" bets only: WON/LOST/PUSH/VOID (exclude PENDING).
        long settled = bets.stream().filter(b -> b.getStatus() != BetStatus.PENDING).count();
        long wins = bets.stream().filter(b -> b.getStatus() == BetStatus.WON).count();

        double winPct = settled == 0 ? 0.0 : round2((wins * 100.0) / settled);

        AnalyticsSummaryResponse resp = new AnalyticsSummaryResponse();
        resp.setTotalBets(totalBets);
        resp.setTotalUnitsWagered(totalUnitsWagered);
        resp.setTotalProfitLoss(totalProfitLoss);
        resp.setWinPercentage(winPct);
        return resp;
    }

    public List<AnalyticsGroupRow> getBySport() {
        List<Bet> bets = betRepository.findAll();
        Map<String, List<Bet>> grouped = bets.stream()
                .collect(Collectors.groupingBy(b -> b.getSport().name()));

        return grouped.entrySet().stream()
                .map(e -> buildGroupRow(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(AnalyticsGroupRow::getKey))
                .toList();
    }

    public List<AnalyticsGroupRow> getByStatus() {
        List<Bet> bets = betRepository.findAll();
        Map<String, List<Bet>> grouped = bets.stream()
                .collect(Collectors.groupingBy(b -> b.getStatus().name()));

        return grouped.entrySet().stream()
                .map(e -> buildGroupRow(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(AnalyticsGroupRow::getKey))
                .toList();
    }

    private AnalyticsGroupRow buildGroupRow(String key, List<Bet> bets) {
        BigDecimal totalUnitsWagered = sumStake(bets);
        BigDecimal totalProfitLoss = sumProfit(bets);

        long totalBets = bets.size();
        long settled = bets.stream().filter(b -> b.getStatus() != BetStatus.PENDING).count();
        long wins = bets.stream().filter(b -> b.getStatus() == BetStatus.WON).count();
        double winPct = settled == 0 ? 0.0 : round2((wins * 100.0) / settled);

        AnalyticsGroupRow row = new AnalyticsGroupRow();
        row.setKey(key);
        row.setTotalBets(totalBets);
        row.setTotalUnitsWagered(totalUnitsWagered);
        row.setTotalProfitLoss(totalProfitLoss);
        row.setWinPercentage(winPct);
        return row;
    }

    private BigDecimal sumStake(List<Bet> bets) {
        return bets.stream()
                .map(Bet::getStakeUnits)
                .filter(v -> v != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal sumProfit(List<Bet> bets) {
        // profitUnits can be null (especially for PENDING), treat null as 0.
        return bets.stream()
                .map(b -> b.getProfitUnits() == null ? BigDecimal.ZERO : b.getProfitUnits())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private double round2(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}

