package com.mooc.formulaone.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name="pilote")
@Entity
public class Pilote {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String nom;

    @ManyToOne
    private Voiture voiture;

    @ManyToMany
    @JoinTable(name = "pilotes_courses", joinColumns = @JoinColumn(name = "pilote_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))

    private Set<Course> courses = new HashSet<>();

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
