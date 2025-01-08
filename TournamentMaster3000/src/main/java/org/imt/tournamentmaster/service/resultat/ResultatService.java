package org.imt.tournamentmaster.service.resultat;

import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.repository.resultat.ResultatRepository;

import java.util.List;

public class ResultatService {

    private final ResultatRepository resultatRepository;

    public ResultatService(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    public Resultat getById(int id) {
        return resultatRepository.findById(id);
    }

    public List<Resultat> getAll() {
        return resultatRepository.findAll();
    }
}
