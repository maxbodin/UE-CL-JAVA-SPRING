package org.imt.tournamentmaster.controller.equipe;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.service.equipe.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipe")
@Validated
public class EquipeController {

    private final EquipeService equipeService;

    @Autowired
    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") long id) {
        Optional<Equipe> equipe = equipeService.getById(id);
        return equipe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Equipe>> getAll() {
        List<Equipe> equipes = equipeService.getAll();
        return ResponseEntity.ok(equipes);
    }

    @PostMapping
    public ResponseEntity<Equipe> createEquipe(@RequestBody @Valid Equipe equipe) {
        try {
            Equipe savedEquipe = equipeService.save(equipe);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipe);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> updateEquipe(
            @PathVariable @Min(value = 1, message = "ID must be greater than 0") long id,
            @RequestBody @Valid Equipe equipe) {
        if (equipeService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        equipe.setId(id);
        Equipe updatedEquipe = equipeService.save(equipe);
        return ResponseEntity.ok(updatedEquipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(
            @PathVariable @Min(value = 1, message = "ID must be greater than 0") long id) {
        Optional<Equipe> equipe = equipeService.getById(id);
        if (equipe.isPresent()) {
            equipeService.delete(equipe.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}