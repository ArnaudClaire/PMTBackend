package com.mooc.formulaone.controllers;

import com.mooc.formulaone.models.Task;
import com.mooc.formulaone.services.TaskService;
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
 * Expose les endpoints REST de gestion des taches.
 */
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Retourne toutes les taches.
     *
     * @return la liste complete des taches
     */
    @GetMapping("/tasks")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Task> findAll() {
        return taskService.findAll();
    }

    /**
     * Retourne une tache a partir de son identifiant.
     *
     * @param id identifiant de la tache
     * @return la tache correspondante
     */
    @GetMapping("/tasks/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    /**
     * Cree une tache.
     *
     * @param task tache a enregistrer
     * @return identifiant genere
     */
    @PostMapping("/tasks")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long create(@RequestBody Task task) {
        return taskService.create(task);
    }

    /**
     * Supprime une tache existante.
     *
     * @param id identifiant de la tache a supprimer
     */
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        Task task = taskService.findById(id);
        taskService.delete(task);
    }
}
