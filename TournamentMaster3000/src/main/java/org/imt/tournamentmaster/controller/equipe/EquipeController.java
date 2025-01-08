package org.imt.tournamentmaster.controller.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.service.equipe.EquipeService;

import java.util.List;

public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    public Equipe getById(int id) {
        return equipeService.getById(id);
    }

    public List<Equipe> getAll() {
        return equipeService.getAll();
    }
}
