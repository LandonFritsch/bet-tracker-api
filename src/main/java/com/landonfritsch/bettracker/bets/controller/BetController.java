package com.landonfritsch.bettracker.bets.controller;

import com.landonfritsch.bettracker.bets.dto.BetResponse;
import com.landonfritsch.bettracker.bets.dto.CreateBetRequest;
import com.landonfritsch.bettracker.bets.dto.UpdateBetRequest;
import com.landonfritsch.bettracker.bets.service.BetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @GetMapping
    public List<BetResponse> getAllBets() {
        return betService.getAllBets();
    }

    @GetMapping("/{id}")
    public BetResponse getBetById(@PathVariable Long id) {
        return betService.getBetById(id);
    }

    @PostMapping
    public ResponseEntity<BetResponse> createBet(@Valid @RequestBody CreateBetRequest request) {
        BetResponse created = betService.createBet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public BetResponse updateBet(@PathVariable Long id, @Valid @RequestBody UpdateBetRequest request) {
        return betService.updateBet(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBet(@PathVariable Long id) {
        betService.deleteBet(id);
    }
}



