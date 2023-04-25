package com.mooc.formulaone.services.impl;

import com.mooc.formulaone.dao.VoitureRepository;
import com.mooc.formulaone.exceptions.EntityDontExistException;
import com.mooc.formulaone.models.Pilote;
import com.mooc.formulaone.models.Voiture;
import com.mooc.formulaone.services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoitureServiceImpl implements VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Override
    public int create(Voiture voiture) {
        return voitureRepository.save(voiture).getId();
    }

    @Override
    public Voiture findById(int id) {

        Optional<Voiture> voiture = voitureRepository.findById(id);

        // On trouve la voiture
        if(voiture.isPresent()) {
            return voiture.get();
        }

        throw new EntityDontExistException();

    }

    @Override
    public List<Voiture> findAll() {
        List<Voiture> voitures = new ArrayList<Voiture>();
        voitureRepository.findAll().forEach(voitures::add);
        return voitures;
    }
}
