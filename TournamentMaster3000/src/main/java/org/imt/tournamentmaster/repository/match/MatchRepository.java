package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByRoundsContaining(Round round);

    @Query("SELECT m FROM Match m " +
            "WHERE (:equipeAId IS NULL OR m.equipeA.id = :equipeAId) " +
            "AND (:equipeBId IS NULL OR m.equipeB.id = :equipeBId) " +
            "AND (:status IS NULL OR m.status = :status)")
    List<Match> searchMatches(@Param("equipeAId") Long equipeAId,
                              @Param("equipeBId") Long equipeBId,
                              @Param("status") Match.Status status);
}
