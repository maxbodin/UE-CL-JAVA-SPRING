package org.imt.tournamentmaster.repository.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.MapRepository;

import java.util.List;
import java.util.Map;

public class EquipeRepositoryImpl extends MapRepository<Equipe> implements EquipeRepository {

    private final JoueurRepository joueurRepository;

    public EquipeRepositoryImpl(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @Override
    public Map<Long, Equipe> initData() {
        Joueur joueur1 = joueurRepository.findById(1L);
        Joueur joueur2 = joueurRepository.findById(2L);
        Joueur joueur3 = joueurRepository.findById(3L);
        Joueur joueur4 = joueurRepository.findById(4L);
        Joueur joueur5 = joueurRepository.findById(5L);
        Joueur joueur6 = joueurRepository.findById(6L);

        return Map.of(
                1L, new Equipe(1L, "Equipe 1", List.of(joueur1, joueur2, joueur3)),
                2L, new Equipe(2L, "Equipe 2", List.of(joueur4, joueur5, joueur6))
        );
    }
}
