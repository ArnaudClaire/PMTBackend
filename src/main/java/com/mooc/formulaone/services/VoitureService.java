package com.mooc.formulaone.services;

import com.mooc.formulaone.models.Voiture;

import java.util.List;

public interface VoitureService {
    int create(Voiture voiture);
    Voiture findById(int id);
    List<Voiture> findAll();
}
