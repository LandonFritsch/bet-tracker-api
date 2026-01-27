package com.landonfritsch.bettracker.bets.controller;

import com.landonfritsch.bettracker.bets.model.Bet;
import com.landonfritsch.bettracker.bets.repository.BetRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetRepository betRepository;

    public BetController(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    // GET /api/bets
    @GetMapping
    public List<Bet> getAll() {
        return betRepository.findAll();
    }

    // GET /api/bets/{id}
    @GetMapping("/{id}")
    public Bet getById(@PathVariable Long id) {
        return betRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Bet not found: " + id
                ));
    }

    // POST /api/bets
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bet create(@Valid @RequestBody Bet bet) {
        bet.setId(null); // ensure new row
        return betRepository.save(bet);
    }

    // PUT /api/bets/{id}
    @PutMapping("/{id}")
    public Bet update(@PathVariable Long id, @Valid @RequestBody Bet incoming) {
        Bet existing = betRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Bet not found: " + id
                ));

        existing.setSport(incoming.getSport());
        existing.setBetType(incoming.getBetType());
        existing.setStatus(incoming.getStatus());
        existing.setEvent(incoming.getEvent());
        existing.setStakeUnits(incoming.getStakeUnits());
        existing.setAmericanOdds(incoming.getAmericanOdds());
        existing.setLine(incoming.getLine());
        existing.setProfitUnits(incoming.getProfitUnits());

        return betRepository.save(existing);
    }

    // DELETE /api/bets/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!betRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bet not found: " + id);
        }
        betRepository.deleteById(id);
    }
}


