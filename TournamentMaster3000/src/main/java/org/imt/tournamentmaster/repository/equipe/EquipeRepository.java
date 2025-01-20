package org.imt.tournamentmaster.repository.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

}
