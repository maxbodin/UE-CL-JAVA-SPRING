package org.imt.tournamentmaster.controller.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.service.equipe.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipe")
public class EquipeController {

    private final EquipeService equipeService;

    @Autowired
    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> getById(@PathVariable long id) {
        Optional<Equipe> equipe = equipeService.getById(id);

        return equipe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Equipe> getAll() {
        return equipeService.getAll();
    }

    @PostMapping
    public ResponseEntity<Equipe> createEquipe(@RequestBody Equipe newEquipe) {
        Equipe savedEquipe = equipeService.createEquipeWithJoueurs(newEquipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> updateEquipe(@PathVariable long id, @RequestBody Equipe updatedEquipe) {
        Optional<Equipe> existingEquipe = equipeService.getById(id);

        if (existingEquipe.isPresent()) {
            Equipe equipe = existingEquipe.get();
            equipe.setNom(updatedEquipe.getNom());
            equipe.setJoueurs(updatedEquipe.getJoueurs());

            Equipe savedEquipe = equipeService.createEquipeWithJoueurs(equipe);
            return ResponseEntity.ok(savedEquipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable long id) {
        Optional<Equipe> existingEquipe = equipeService.getById(id);

        if (existingEquipe.isPresent()) {
            equipeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

