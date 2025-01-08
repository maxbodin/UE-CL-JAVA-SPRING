package org.imt.tournamentmaster.repository.equipe;

import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.MapRepository;

import java.util.Map;

public class JoueurRepositoryImpl extends MapRepository<Joueur> implements JoueurRepository {


    @Override
    public Map<Long, Joueur> initData() {

        return Map.of(
                1L, new Joueur(1L, "Joueur 1", "Jonathan", 1),
                2L, new Joueur(2L, "Joueur 2", "Joseph", 2),
                3L, new Joueur(3L, "Joueur 3", "Jotaro", 3),
                4L, new Joueur(4L, "Joueur 4", "Giorno", 4),
                5L, new Joueur(5L, "Joueur 5", "Josuke", 5),
                6L, new Joueur(6L, "Joueur 6", "Jolyne", 6)
        );
    }
}
