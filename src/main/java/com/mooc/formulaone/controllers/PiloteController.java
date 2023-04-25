package com.mooc.formulaone.controllers;

import com.mooc.formulaone.models.Pilote;
import com.mooc.formulaone.models.Voiture;
import com.mooc.formulaone.services.PiloteService;
import com.mooc.formulaone.services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PiloteController {

    @Autowired
    private PiloteService piloteService;

    @Autowired
    private VoitureService voitureService;

    @GetMapping("/pilotes")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Pilote> listerTous(){

        return piloteService.findAll();

    }

    @GetMapping("/pilote/{id_pilote}/voiture/{id_voiture}")
    @ResponseStatus(code = HttpStatus.OK)
    public void associerVoiture(@PathVariable("id_pilote") int id_pilote, @PathVariable("id_voiture") int id_voiture){

        Voiture voiture = voitureService.findById(id_voiture);
        Pilote pilote = piloteService.findById(id_pilote);

        piloteService.associerVoiture(voiture,pilote);

    }

    @PostMapping("/pilote")
    @ResponseStatus(code = HttpStatus.CREATED)
    public int creer(@RequestBody Pilote pilote) {

        return piloteService.creer(pilote);

    }

    @DeleteMapping("/pilote/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {

        // Contrôle si l'entité existe
        Pilote pilote = piloteService.findById(id);

        piloteService.delete(pilote);
    }

}
