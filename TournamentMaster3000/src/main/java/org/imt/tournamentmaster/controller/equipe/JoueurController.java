package org.imt.tournamentmaster.controller.equipe;

import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.service.equipe.JoueurService;

import java.util.List;

public class JoueurController {

    private final JoueurService joueurService;

    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    public Joueur getById(int id) {
        return joueurService.getById(id);
    }

    public List<Joueur> getAll() {
        return joueurService.getAll();
    }
}
