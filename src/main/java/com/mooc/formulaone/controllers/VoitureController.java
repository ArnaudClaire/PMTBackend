package com.mooc.formulaone.controllers;

import com.mooc.formulaone.models.Voiture;
import com.mooc.formulaone.services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/voitures")
    public List<Voiture> findAll(){
        return voitureService.findAll();
    }


    @PostMapping("/voiture")
    @ResponseStatus(code = HttpStatus.CREATED)
    public int create(@RequestBody Voiture voiture){

        return voitureService.create(voiture);

    }

}
