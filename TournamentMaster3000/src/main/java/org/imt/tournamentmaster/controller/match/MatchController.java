package org.imt.tournamentmaster.controller.match;

import jakarta.validation.constraints.Min;
import org.imt.tournamentmaster.dto.ImportReport;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/match")
@Validated
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") long id) {
        Optional<Match> match = matchService.getById(id);

        return match.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAll() {
        return ResponseEntity.ok(matchService.getAll());
    }

    @PostMapping("/import")
    public ResponseEntity<ImportReport> importMatches(@RequestBody List<Match> matches) {
        ImportReport report = matchService.importMatches(matches);
        return ResponseEntity.ok(report);
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
