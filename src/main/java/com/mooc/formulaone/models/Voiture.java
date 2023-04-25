package com.mooc.formulaone.models;

import jakarta.persistence.*;

@Table(name="voiture")
@Entity
public class Voiture {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String nom;
    private int vitesse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}
