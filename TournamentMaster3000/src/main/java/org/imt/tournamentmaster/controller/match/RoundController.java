package org.imt.tournamentmaster.controller.match;

import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.service.match.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/round")
public class RoundController {

    private final RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Round> getById(@PathVariable long id) {
        Optional<Round> round = roundService.getById(id);

        return round.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Round> getAll() {
        return roundService.getAll();
    }

    @GetMapping("/max/{scoreA}")
    public List<Round> getMaxScore(@PathVariable int scoreA) {
        return roundService.getByScoreAGreaterThanEqual(scoreA);
    }

    @PostMapping
    public ResponseEntity<Round> createRound(@RequestBody Round newRound) {
        Round savedRound = roundService.save(newRound);
        return ResponseEntity.ok(savedRound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Round> updateRound(@PathVariable Long id, @RequestBody Round round) {
        Round updatedRound = roundService.updateRound(id, round);
        return ResponseEntity.ok(updatedRound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRound(@PathVariable long id) {
        Optional<Round> existingRound = roundService.getById(id);

        if (existingRound.isPresent()) {
            roundService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
