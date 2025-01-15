package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;

    @Autowired
    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Equipe> getById(long id) {
        return equipeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Equipe> getAll() {
        return StreamSupport.stream(equipeRepository.findAll().spliterator(), false)
                .toList();
    }
}
