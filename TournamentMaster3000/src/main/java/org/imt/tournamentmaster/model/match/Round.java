package org.imt.tournamentmaster.model.match;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.imt.tournamentmaster.model.equipe.Equipe;

import java.util.Objects;

@Entity
public class Round {

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

    @Min(value = 0, message = "Le score ne peut pas être négatif")
    @Max(value = 999, message = "Le score ne peut pas dépasser 999")
    private int scoreA;

    @Min(value = 0, message = "Le score ne peut pas être négatif")
    @Max(value = 999, message = "Le score ne peut pas dépasser 999")
    private int scoreB;

    @Min(value = 1, message = "Le numéro du round doit être supérieur à 0")
    private int roundNumber;

    public Round() {
    }

    public Round(long id, Equipe equipeA, Equipe equipeB, int scoreA, int scoreB, int roundNumber) {
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.roundNumber = roundNumber;
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

    public int getScoreA() {
        return scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public int getRoundNumber() {
        return roundNumber;
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

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Equipe determineWinner() {
        if (scoreA > scoreB) {
            return equipeA;
        } else {
            return equipeB;
        }
    }

    @Override
    public String toString() {
        return "Round{" +
                "equipeA=" + equipeA +
                ", equipeB=" + equipeB +
                ", scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                ", roundNumber=" + roundNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return id == round.id && scoreA == round.scoreA && scoreB == round.scoreB && roundNumber == round.roundNumber && Objects.equals(equipeA, round.equipeA) && Objects.equals(equipeB, round.equipeB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipeA, equipeB, scoreA, scoreB, roundNumber);
    }

    @AssertTrue(message = "Les équipes A et B ne peuvent pas être identiques")
    @JsonIgnore
    private boolean areTeamsValid() {
        return equipeA != null && equipeB != null && !equipeA.equals(equipeB);
    }

}
