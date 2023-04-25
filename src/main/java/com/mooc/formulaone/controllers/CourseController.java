package com.mooc.formulaone.controllers;

import com.mooc.formulaone.models.Course;
import com.mooc.formulaone.models.Pilote;
import com.mooc.formulaone.services.CourseService;
import com.mooc.formulaone.services.PiloteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    PiloteService piloteService;

    @GetMapping("/course/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Course findById(@PathVariable int id){

        return courseService.findById(id);

    }

    @PostMapping("/course")
    @ResponseStatus(code = HttpStatus.CREATED)
    public int ajouter(@RequestBody Course course){

        return courseService.create(course);

    }

    @GetMapping("/course/{id_course}/pilote/{id_pilote}")
    @ResponseStatus(code = HttpStatus.OK)
    public void associerPilote(@PathVariable("id_course") int id_course, @PathVariable("id_pilote") int id_pilote){

        Course course = courseService.findById(id_course);
        Pilote pilote = piloteService.findById(id_pilote);

        courseService.associerPilote(course,pilote);

    }

    @GetMapping("/course/{id}/{vitesse}/start")
    @ResponseStatus(code = HttpStatus.OK)
    public String startRace(@PathVariable("id") int id, @PathVariable("vitesse") int vitesse){

        Course course = courseService.findById(id);

        return courseService.startRace(course, vitesse);

    }

}
