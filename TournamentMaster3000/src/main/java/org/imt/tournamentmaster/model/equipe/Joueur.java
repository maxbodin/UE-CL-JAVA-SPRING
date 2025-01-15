package org.imt.tournamentmaster.model.equipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Joueur {

    @JsonIgnore
    @Id
    private long id;

    private String nom;

    private String prenom;

    private int numero;

    public Joueur() {
    }

    public Joueur(long id, String nom, String prenom, int numero) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNumero() {
        return numero;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numero=" + numero +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return id == joueur.id && numero == joueur.numero && Objects.equals(nom, joueur.nom) && Objects.equals(prenom, joueur.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, numero);
    }
}
