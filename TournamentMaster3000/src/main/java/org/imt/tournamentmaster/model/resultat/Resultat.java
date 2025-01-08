package org.imt.tournamentmaster.model.resultat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;

import java.util.Objects;

public class Resultat {

    @JsonIgnore
    private long id;

    private Match match;


    public Resultat() {
    }

    public Resultat(long id, Match match) {
        this.id = id;
        this.match = match;
    }

    public long getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public Equipe determineWinner() {
        return match.determineWinner();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "id=" + id +
                ", match=" + match +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resultat resultat = (Resultat) o;
        return id == resultat.id && Objects.equals(match, resultat.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, match);
    }
}

