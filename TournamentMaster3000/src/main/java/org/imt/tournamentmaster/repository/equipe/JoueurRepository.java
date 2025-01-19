package org.imt.tournamentmaster.repository.equipe;

import org.imt.tournamentmaster.model.equipe.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Long> {

}
