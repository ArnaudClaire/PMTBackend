package com.mooc.formulaone.services.impl;

import com.mooc.formulaone.dao.PiloteRepository;
import com.mooc.formulaone.exceptions.EntityDontExistException;
import com.mooc.formulaone.models.Pilote;
import com.mooc.formulaone.models.Voiture;
import com.mooc.formulaone.services.PiloteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PiloteServiceImpl implements PiloteService {

    @Autowired
    private PiloteRepository piloteRepository;

    @Override
    public List<Pilote> findAll() {

        List<Pilote> pilotes = new ArrayList<Pilote>();
        piloteRepository.findAll().forEach(pilotes::add);
        return pilotes;
    }

    @Override
    public Pilote findById(int id) {

        Optional<Pilote> pilote = piloteRepository.findById(id);

        // On trouve le pilote
        if(pilote.isPresent()) {
            return pilote.get();
        }

        throw new EntityDontExistException();

    }

    @Override
    public int creer(Pilote pilote) {
        return piloteRepository.save(pilote).getId();
    }

    @Override
    public void delete(Pilote pilote) {
        piloteRepository.delete(pilote);
    }

    @Override
    public void associerVoiture(Voiture voiture, Pilote pilote) {

        pilote.setVoiture(voiture);
        piloteRepository.save(pilote);

    }
}
