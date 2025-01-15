package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class JoueurService {

    private final JoueurRepository joueurRepository;

    @Autowired
    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Joueur> getById(long id) {
        return joueurRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Joueur> getAll() {
        return StreamSupport.stream(joueurRepository.findAll().spliterator(), false)
                .toList();
    }
}
