package com.mooc.formulaone.dao;

import com.mooc.formulaone.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Integer> {
}
