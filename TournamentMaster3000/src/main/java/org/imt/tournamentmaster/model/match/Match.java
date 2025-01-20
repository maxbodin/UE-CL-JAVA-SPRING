package org.imt.tournamentmaster.model.match;

import jakarta.persistence.*;
import org.imt.tournamentmaster.model.equipe.Equipe;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`match`")
public class Match {

    @Id
    private long id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Equipe equipeA;

    @OneToOne(cascade = {CascadeType.ALL})
    private Equipe equipeB;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Round> rounds; // Set est un type de collection, on va éviter les confusions et appeler ça un "round"

    private Status status;

    public Match() {
    }

    public Match(long id, Equipe equipeA, Equipe equipeB, List<Round> rounds, Status status) {
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.rounds = rounds;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipe determineWinner() {
        int wonByA = 0;
        int wonByB = 0;

        for (Round round : rounds) {
            Equipe winner = round.determineWinner();

            if (winner.equals(equipeA)) {
                wonByA++;
            } else if (winner.equals(equipeB)) {
                wonByB++;
            }
        }

        if (wonByA > wonByB) {
            return equipeA;
        } else {
            return equipeB;
        }
    }

    @Override
    public String toString() {
        return "Match{" +
                "equipeA=" + equipeA +
                ", equipeB=" + equipeB +
                ", rounds=" + rounds +
                ", status=" + status +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id == match.id && Objects.equals(equipeA, match.equipeA) && Objects.equals(equipeB, match.equipeB) && Objects.equals(rounds, match.rounds) && status == match.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipeA, equipeB, rounds, status);
    }

    public enum Status {
        NOUVEAU, EN_COURS, TERMINE
    }
}
