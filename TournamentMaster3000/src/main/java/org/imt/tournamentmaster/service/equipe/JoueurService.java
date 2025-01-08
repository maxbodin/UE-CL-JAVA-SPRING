package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;

import java.util.List;

public class JoueurService {

    private final JoueurRepository joueurRepository;

    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public Joueur getById(int id) {
        return joueurRepository.findById(id);
    }

    public List<Joueur> getAll() {
        return joueurRepository.findAll();
    }
}
