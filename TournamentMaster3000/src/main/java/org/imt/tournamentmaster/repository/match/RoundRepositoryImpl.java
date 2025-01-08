package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.MapRepository;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;

import java.util.Map;

public class RoundRepositoryImpl extends MapRepository<Round> implements RoundRepository {

    private final EquipeRepository equipeRepository;

    public RoundRepositoryImpl(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    @Override
    public Map<Long, Round> initData() {
        Equipe equipe1 = equipeRepository.findById(1L);
        Equipe equipe2 = equipeRepository.findById(2L);

        return Map.of(
                1L, new Round(1L, equipe1, equipe2, 21, 14, 1),
                2L, new Round(2L, equipe1, equipe2, 19, 21, 2),
                3L, new Round(3L, equipe1, equipe2, 21, 17, 3)
        );
    }
}
