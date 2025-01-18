package org.imt.tournamentmaster.controller.match;

import jakarta.validation.constraints.Min;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.service.match.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/round")
@Validated
public class RoundController {

    private final RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Round> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") long id) {
        Optional<Round> round = roundService.getById(id);

        return round.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Round>> getAll() {
        return ResponseEntity.ok(roundService.getAll());
    }

    @GetMapping("/max/{scoreA}")
    public ResponseEntity<List<Round>> getMaxScore(@PathVariable int scoreA) {
       return ResponseEntity.ok(roundService.getByScoreAGreaterThanEqual(scoreA));
    }
}
