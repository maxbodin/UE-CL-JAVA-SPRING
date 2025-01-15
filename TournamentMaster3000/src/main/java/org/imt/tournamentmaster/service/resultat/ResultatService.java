package org.imt.tournamentmaster.service.resultat;

import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.repository.resultat.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ResultatService {

    private final ResultatRepository resultatRepository;

    @Autowired
    public ResultatService(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Resultat> getById(long id) {
        return resultatRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Resultat> getAll() {
        return StreamSupport.stream(resultatRepository.findAll().spliterator(), false)
                .toList();
    }
}
