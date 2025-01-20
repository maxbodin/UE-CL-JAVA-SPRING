package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final JoueurRepository joueurRepository;

    @Autowired
    public EquipeService(EquipeRepository equipeRepository, JoueurRepository joueurRepository, JoueurRepository joueurRepository1) {
        this.equipeRepository = equipeRepository;
        this.joueurRepository = joueurRepository1;
    }

    @Transactional(readOnly = true)
    public Optional<Equipe> getById(long id) {
        return equipeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Equipe> getAll() {
        return equipeRepository.findAll();
    }

    @Transactional
    public Equipe createEquipeWithJoueurs(Equipe newEquipe) {
        List<Joueur> processedJoueurs = new ArrayList<>();

        // Fetch existing equipe if the ID is provided
        Equipe existingEquipe = equipeRepository.findById(newEquipe.getId()).orElse(null);

        if (existingEquipe != null) {
            // If the equipe already exists, we need to update it with new joueurs
            List<Joueur> existingJoueurs = existingEquipe.getJoueurs();

            for (Joueur joueur : newEquipe.getJoueurs()) {
                // If the joueur is new (id = 0), save it and add to processedJoueurs
                if (joueur.getId() == 0) {
                    Joueur savedJoueur = joueurRepository.saveAndFlush(joueur);
                    processedJoueurs.add(savedJoueur);
                } else {
                    // If the joueur already exists, fetch it from the database and attach it
                    Optional<Joueur> existingJoueur = joueurRepository.findById(joueur.getId());
                    if (existingJoueur.isPresent()) {
                        processedJoueurs.add(existingJoueur.get());
                    } else {
                        // Handle case where joueur ID is invalid or not found
                        processedJoueurs.add(joueur);
                    }
                }

                // Add new joueurs if they are not already in the existing list
                if (!existingJoueurs.contains(joueur)) {
                    existingEquipe.addJoueur(joueur);
                }
            }

            // Update the joueurs list and save the updated equipe
            existingEquipe.setJoueurs(processedJoueurs);
            return equipeRepository.saveAndFlush(existingEquipe);
        } else {
            // If the equipe doesn't exist, we create it from scratch
            for (Joueur joueur : newEquipe.getJoueurs()) {
                if (joueur.getId() == 0) {
                    // If the joueur doesn't have an ID, it's new. Save it.
                    Joueur savedJoueur = joueurRepository.saveAndFlush(joueur);
                    processedJoueurs.add(savedJoueur);
                } else {
                    // If the joueur has an ID, fetch it from the database to attach it.
                    Optional<Joueur> existingJoueur = joueurRepository.findById(joueur.getId());
                    if (existingJoueur.isPresent()) {
                        processedJoueurs.add(existingJoueur.get());
                    } else {
                        // Handle the case where the joueur does not exist in the database.
                        processedJoueurs.add(joueur);
                    }
                }
            }

            // Set the processed joueurs to the newEquipe and save
            newEquipe.setJoueurs(processedJoueurs);
            return equipeRepository.saveAndFlush(newEquipe);
        }
    }

    @Transactional
    public void deleteById(long id) {
        equipeRepository.deleteById(id);
    }
}
