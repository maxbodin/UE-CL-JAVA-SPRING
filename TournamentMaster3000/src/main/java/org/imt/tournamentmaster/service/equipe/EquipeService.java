package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;

import java.util.List;

public class EquipeService {

    private final EquipeRepository equipeRepository;

    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public Equipe getById(int id) {
        return equipeRepository.findById(id);
    }

    public List<Equipe> getAll() {
        return equipeRepository.findAll();
    }
}
