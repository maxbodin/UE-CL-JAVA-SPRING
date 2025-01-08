package org.imt.tournamentmaster.controller.resultat;

import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.service.resultat.ResultatService;

import java.util.List;

public class ResultatController {

    private final ResultatService resultatService;

    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    public Resultat getById(int id) {
        return resultatService.getById(id);
    }

    public List<Resultat> getAll() {
        return resultatService.getAll();
    }
}
