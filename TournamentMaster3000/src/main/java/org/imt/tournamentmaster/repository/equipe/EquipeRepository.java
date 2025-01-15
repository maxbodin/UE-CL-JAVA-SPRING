package org.imt.tournamentmaster.repository.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends CrudRepository<Equipe, Long> {

}
