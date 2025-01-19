package org.imt.tournamentmaster.controller.equipe;

import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.service.equipe.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/joueur")
public class JoueurController {

    private final JoueurService joueurService;

    @Autowired
    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joueur> getJoueurById(@PathVariable long id) {
        Optional<Joueur> joueur = joueurService.getById(id);

        return joueur.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Joueur> getAllJoueurs() {
        return joueurService.getAll();
    }

    @PostMapping
    public ResponseEntity<Joueur> addJoueur(@RequestBody Joueur newJoueur) {
        Joueur savedJoueur = joueurService.save(newJoueur);
        return ResponseEntity.ok(savedJoueur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Joueur> updateJoueur(@PathVariable long id, @RequestBody Joueur updatedJoueur) {
        Optional<Joueur> existingJoueur = joueurService.getById(id);

        if (existingJoueur.isPresent()) {
            Joueur joueur = existingJoueur.get();

            // Update fields.
            joueur.setNom(updatedJoueur.getNom());
            joueur.setPrenom(updatedJoueur.getPrenom());
            joueur.setNumero(updatedJoueur.getNumero());

            // Save updated joueur.
            joueurService.save(joueur);

            return ResponseEntity.ok(joueur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoueur(@PathVariable long id) {
        Optional<Joueur> existingJoueur = joueurService.getById(id);

        if (existingJoueur.isPresent()) {
            joueurService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
