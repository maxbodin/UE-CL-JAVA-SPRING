package org.imt.tournamentmaster.model.match;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.imt.tournamentmaster.model.equipe.Equipe;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`match`")
public class Match {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "L'équipe A ne peut pas être nulle")
    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Equipe equipeA;

    @NotNull(message = "L'équipe B ne peut pas être nulle")
    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Equipe equipeB;

    @NotNull(message = "La liste des rounds ne peut pas être nulle")
    @Size(min = 1, max = 11, message = "Le match doit avoir entre 1 et 11 rounds")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<@Valid Round> rounds;

    @NotNull(message = "Le statut du match ne peut pas être nul")
    @Enumerated
    private Status status;

    @AssertTrue(message = "Les équipes A et B ne peuvent pas être identiques")
    @JsonIgnore
    private boolean isTeamsValid() {
        return equipeA != null && equipeB != null && !equipeA.equals(equipeB);
    }

    @AssertTrue(message = "Les numéros de rounds doivent être séquentiels")
    @JsonIgnore
    private boolean isRoundNumbersValid() {
        if (rounds == null || rounds.isEmpty()) return true;
        for (int i = 0; i < rounds.size(); i++) {
            if (rounds.get(i).getRoundNumber() != i + 1) return false;
        }
        return true;
    }

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

    public Equipe getEquipeA() {
        return equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
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
