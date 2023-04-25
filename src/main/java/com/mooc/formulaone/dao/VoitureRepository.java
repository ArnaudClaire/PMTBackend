package com.mooc.formulaone.dao;

import com.mooc.formulaone.models.Voiture;
import org.springframework.data.repository.CrudRepository;

public interface VoitureRepository extends CrudRepository<Voiture,Integer> {
}
