package org.imt.tournamentmaster.controller.equipe;

import jakarta.validation.constraints.Min;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.service.equipe.JoueurService;
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
@RequestMapping("/api/joueur")
@Validated
public class JoueurController {

    private final JoueurService joueurService;

    @Autowired
    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joueur> getJoueurById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") long id) {
        Optional<Joueur> joueur = joueurService.getById(id);

        return joueur.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Joueur> getAllJoueurs() {
        return joueurService.getAll();
    }
}
