package org.imt.tournamentmaster.repository.resultat;

import org.imt.tournamentmaster.model.resultat.Resultat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatRepository extends CrudRepository<Resultat, Long> {
}
