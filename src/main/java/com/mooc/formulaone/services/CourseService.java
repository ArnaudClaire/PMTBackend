package com.mooc.formulaone.services;

import com.mooc.formulaone.models.Course;
import com.mooc.formulaone.models.Pilote;

public interface CourseService {

    Course findById(int id_course);

    void associerPilote(Course course, Pilote pilote);

    int create(Course course);

    String startRace(Course course, int vitesse);
}
