package com.mooc.formulaone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String nom;
    private String pays;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "pilote_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "pilote_id")
    )
    private Set<Pilote> pilotes = new HashSet<>();

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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Set<Pilote> getPilotes() {
        return pilotes;
    }

    public void setPilotes(Set<Pilote> pilotes) {
        this.pilotes = pilotes;
    }
}
