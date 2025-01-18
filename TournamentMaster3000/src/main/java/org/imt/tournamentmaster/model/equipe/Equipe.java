package org.imt.tournamentmaster.model.equipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Equipe {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Le nom de l'équipe ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s-]+$", message = "Le nom ne peut contenir que des lettres, chiffres, espaces et tirets")
    private String nom;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @NotNull(message = "La liste des joueurs ne peut pas être nulle")
    @Size(min = 1, max = 11, message = "L'équipe doit avoir entre 1 et 11 joueurs")
    private List<Joueur> joueurs;

    public Equipe() {
    }

    public Equipe(long id, String nom, List<Joueur> joueurs) {
        this.id = id;
        this.nom = nom;
        this.joueurs = joueurs;
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "nom='" + nom + '\'' +
                ", joueurs=" + joueurs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipe equipe = (Equipe) o;
        return id == equipe.id && Objects.equals(nom, equipe.nom) && Objects.equals(joueurs, equipe.joueurs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, joueurs);
    }

    @AssertTrue(message = "L'équipe doit avoir une liste de joueurs non-vide")
    @JsonIgnore
    private boolean areJoueursValid() {
        return joueurs != null && !joueurs.isEmpty();
    }
}
