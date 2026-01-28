package com.landonfritsch.bettracker.analytics.controller;

import com.landonfritsch.bettracker.analytics.dto.AnalyticsGroupRow;
import com.landonfritsch.bettracker.analytics.dto.AnalyticsSummaryResponse;
import com.landonfritsch.bettracker.analytics.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public AnalyticsSummaryResponse summary() {
        return analyticsService.getSummary();
    }

    @GetMapping("/by-sport")
    public List<AnalyticsGroupRow> bySport() {
        return analyticsService.getBySport();
    }

    @GetMapping("/by-status")
    public List<AnalyticsGroupRow> byStatus() {
        return analyticsService.getByStatus();
    }
}

