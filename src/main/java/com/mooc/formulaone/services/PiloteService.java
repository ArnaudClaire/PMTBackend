package com.mooc.formulaone.services;

import com.mooc.formulaone.models.Pilote;
import com.mooc.formulaone.models.Voiture;

import java.util.List;

public interface PiloteService {

    List<Pilote> findAll();
    Pilote findById(int id);
    int creer(Pilote pilote);

    void delete(Pilote pilote);

    void associerVoiture(Voiture voiture, Pilote pilote);
}
