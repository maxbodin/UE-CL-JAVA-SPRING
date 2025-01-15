package org.imt.tournamentmaster.repository.equipe;

import org.imt.tournamentmaster.model.equipe.Joueur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends CrudRepository<Joueur, Long> {

}
