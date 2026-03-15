package com.mooc.formulaone.controllers;

import com.mooc.formulaone.models.Project;
import com.mooc.formulaone.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/**
 * Expose les endpoints REST de gestion des projets.
 */
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * Retourne la liste des projets.
     *
     * @return les projets persistants
     */
    @GetMapping("/projects")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Project> findAll() {
        return projectService.findAll();
    }

    /**
     * Retourne un projet a partir de son identifiant.
     *
     * @param id identifiant du projet
     * @return le projet correspondant
     */
    @GetMapping("/projects/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Project findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    /**
     * Cree un projet.
     *
     * @param project projet a enregistrer
     * @return identifiant genere
     */
    @PostMapping("/projects")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long create(@RequestBody Project project) {
        return projectService.create(project);
    }

    /**
     * Supprime un projet s'il existe.
     *
     * @param id identifiant du projet a supprimer
     */
    @DeleteMapping("/projects/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        Project project = projectService.findById(id);
        projectService.delete(project);
    }
}
