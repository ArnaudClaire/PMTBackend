package com.mooc.formulaone.services.impl;

import com.mooc.formulaone.dao.ProjectRepository;
import com.mooc.formulaone.exceptions.EntityDontExistException;
import com.mooc.formulaone.models.Project;
import com.mooc.formulaone.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

    @Override
    public Project findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            return project.get();
        }
        throw new EntityDontExistException();
    }

    @Override
    public Long create(Project project) {
        return projectRepository.save(project).getId();
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
    }
}
