package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.match.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundRepository extends CrudRepository<Round, Long> {

    /**
     * Requête implémentée par <a href="https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html">JPA</a>
     * Ici on utilise le format de nommage pour que ça corresponde au SQL suivant :
     * <pre>SELECT * FROM round WHERE scoreA >= :scoreA</pre>
     *
     * Astuce : on peut s'affranchir de ce mécanisme et écrire du sql via les annotations @Query et @NativeQuery <a href="https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/Query.html">(javadoc)</a>
     * A savoir : le langage par défaut est du JPQL (Java Persistence Query Language) qui est un langage de requête orienté objet
     * Pour faire du sql classique, il faut passer par des Native Query
     */
    List<Round> findByScoreAGreaterThanEqual(int scoreA);
}
