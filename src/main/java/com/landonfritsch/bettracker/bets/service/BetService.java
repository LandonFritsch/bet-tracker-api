package com.landonfritsch.bettracker.bets.service;

import com.landonfritsch.bettracker.bets.dto.BetResponse;
import com.landonfritsch.bettracker.bets.dto.CreateBetRequest;
import com.landonfritsch.bettracker.bets.dto.UpdateBetRequest;
import com.landonfritsch.bettracker.bets.model.Bet;
import com.landonfritsch.bettracker.bets.model.BetStatus;
import com.landonfritsch.bettracker.bets.repository.BetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class BetService {

    private final BetRepository betRepository;

    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public List<BetResponse> getAllBets() {
        return betRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BetResponse getBetById(Long id) {
        Bet bet = betRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bet not found"));
        return toResponse(bet);
    }

    public BetResponse createBet(CreateBetRequest request) {
        Bet bet = new Bet();
        bet.setSport(request.getSport());
        bet.setBetType(request.getBetType());
        bet.setEvent(request.getEvent());
        bet.setStakeUnits(request.getStakeUnits());
        bet.setAmericanOdds(request.getAmericanOdds());
        bet.setLine(request.getLine());

        bet.setStatus(BetStatus.PENDING);
        bet.setPlacedAt(OffsetDateTime.now(ZoneOffset.UTC));

        Bet saved = betRepository.save(bet);
        return toResponse(saved);
    }

    public BetResponse updateBet(Long id, UpdateBetRequest request) {
        Bet bet = betRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bet not found"));

        bet.setSport(request.getSport());
        bet.setBetType(request.getBetType());
        bet.setStatus(request.getStatus());
        bet.setEvent(request.getEvent());
        bet.setStakeUnits(request.getStakeUnits());
        bet.setAmericanOdds(request.getAmericanOdds());
        bet.setLine(request.getLine());
        bet.setProfitUnits(request.getProfitUnits());

        Bet saved = betRepository.save(bet);
        return toResponse(saved);
    }

    public void deleteBet(Long id) {
        if (!betRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bet not found");
        }
        betRepository.deleteById(id);
    }

    private BetResponse toResponse(Bet bet) {
        BetResponse response = new BetResponse();
        response.setId(bet.getId());
        response.setSport(bet.getSport());
        response.setBetType(bet.getBetType());
        response.setStatus(bet.getStatus());
        response.setEvent(bet.getEvent());
        response.setStakeUnits(bet.getStakeUnits());
        response.setAmericanOdds(bet.getAmericanOdds());
        response.setLine(bet.getLine());
        response.setProfitUnits(bet.getProfitUnits());
        response.setPlacedAt(bet.getPlacedAt());
        return response;
    }
}

