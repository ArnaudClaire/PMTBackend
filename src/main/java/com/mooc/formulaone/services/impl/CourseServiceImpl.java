package com.mooc.formulaone.services.impl;

import com.mooc.formulaone.dao.CourseRepository;
import com.mooc.formulaone.dao.PiloteRepository;
import com.mooc.formulaone.exceptions.EntityDontExistException;
import com.mooc.formulaone.models.Course;
import com.mooc.formulaone.models.Pilote;
import com.mooc.formulaone.models.Voiture;
import com.mooc.formulaone.services.CourseService;
import com.mooc.formulaone.services.PiloteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PiloteService piloteService;

    private PiloteRepository piloteRepository;

    @Override
    public Course findById(int id) {

        Optional<Course> course = courseRepository.findById(id);

        // On trouve la course
        if(course.isPresent()) {
            return course.get();
        }

        throw new EntityDontExistException();

    }

    @Override
    public int create(Course course) {

        return courseRepository.save(course).getId();

    }

    @Override
    public String startRace(Course course, int vitesse) {

        String resultat = "";

        for(Pilote pilote : course.getPilotes()){

            Voiture voiture = pilote.getVoiture();
            if(voiture != null && voiture.getVitesse() > vitesse){

                // si la voiture a la vitesse nécessaire pour faire la course
                resultat = resultat + "Le pilote "+ pilote.getNom() +" peut participer à la course avec sa voiture " + voiture.getNom() + "/n";

            }

        }

        System.out.println(resultat);

        return resultat;

    }

    @Override
    public void associerPilote(Course course, Pilote pilote) {

        pilote.getCourses().add(course);
        course.getPilotes().add(pilote);

    }


}
