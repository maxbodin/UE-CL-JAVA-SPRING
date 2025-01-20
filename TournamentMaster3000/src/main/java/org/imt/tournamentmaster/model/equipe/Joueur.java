package org.imt.tournamentmaster.model.equipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Le nom du joueur ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "Le nom ne peut contenir que des lettres, espaces et tirets")
    private String nom;

    @NotBlank(message = "Le prénom du joueur ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "Le prénom ne peut contenir que des lettres, espaces et tirets")
    private String prenom;

    @Min(value = 1, message = "Le numéro doit être supérieur à 0")
    @Max(value = 99, message = "Le numéro doit être inférieur à 100")
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
