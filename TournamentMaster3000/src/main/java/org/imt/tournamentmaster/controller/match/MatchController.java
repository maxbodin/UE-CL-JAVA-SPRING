package org.imt.tournamentmaster.controller.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable long id) {
        Optional<Match> match = matchService.getById(id);

        return match.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Match> getAll() {
        return matchService.getAll();
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match newMatch) {
        Match savedMatch = matchService.save(newMatch);
        return ResponseEntity.ok(savedMatch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable long id, @RequestBody Match updatedMatch) {
        Optional<Match> existingMatch = matchService.getById(id);

        if (existingMatch.isPresent()) {
            Match match = existingMatch.get();

            // Update fields of the existing match.
            match.setEquipeA(updatedMatch.getEquipeA());
            match.setEquipeB(updatedMatch.getEquipeB());
            match.setRounds(updatedMatch.getRounds());
            match.setStatus(updatedMatch.getStatus());

            // Save updated match.
            matchService.save(match);

            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable long id) {
        Optional<Match> existingMatch = matchService.getById(id);

        if (existingMatch.isPresent()) {
            matchService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Match>> searchMatches(
            @RequestParam(value = "equipeAId", required = false) Long equipeAId,
            @RequestParam(value = "equipeBId", required = false) Long equipeBId,
            @RequestParam(value = "status", required = false) Match.Status status) {

        List<Match> matches = matchService.searchMatches(equipeAId, equipeBId, status);

        return matches.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(matches);
    }
}
