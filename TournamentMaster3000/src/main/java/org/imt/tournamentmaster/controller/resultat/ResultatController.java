package org.imt.tournamentmaster.controller.resultat;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.service.resultat.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultat")
public class ResultatController {

    private final ResultatService resultatService;

    @Autowired
    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getById(@PathVariable long id) {
        Optional<Resultat> resultat = resultatService.getById(id);

        return resultat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Resultat> getAll() {
        return resultatService.getAll();
    }

    @PostMapping
    public ResponseEntity<Resultat> createResultat(@RequestBody Resultat newResultat) {
        Resultat savedResultat = resultatService.save(newResultat);
        return ResponseEntity.ok(savedResultat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateResultat(@PathVariable long id, @RequestBody Resultat updatedResultat) {
        Optional<Resultat> existingResultat = resultatService.getById(id);

        if (existingResultat.isPresent()) {
            Resultat resultat = existingResultat.get();
            resultat.setMatch(updatedResultat.getMatch());

            Resultat savedResultat = resultatService.save(resultat);
            return ResponseEntity.ok(savedResultat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultat(@PathVariable long id) {
        Optional<Resultat> existingResultat = resultatService.getById(id);

        if (existingResultat.isPresent()) {
            resultatService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/winner")
    public ResponseEntity<String> determineWinner(@PathVariable long id) {
        Optional<Resultat> existingResultat = resultatService.getById(id);

        if (existingResultat.isPresent()) {
            Equipe winner = existingResultat.get().determineWinner();
            return ResponseEntity.ok("The winner is: " + winner.getNom());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
