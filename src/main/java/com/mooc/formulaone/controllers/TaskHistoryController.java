package com.mooc.formulaone.controllers;

import com.mooc.formulaone.models.TaskHistory;
import com.mooc.formulaone.services.TaskHistoryService;
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
 * Expose les endpoints REST de gestion de l'historique des taches.
 */
public class TaskHistoryController {

    @Autowired
    private TaskHistoryService taskHistoryService;

    /**
     * Retourne toutes les entrees d'historique.
     *
     * @return la liste des historiques
     */
    @GetMapping("/task-histories")
    @ResponseStatus(code = HttpStatus.OK)
    public List<TaskHistory> findAll() {
        return taskHistoryService.findAll();
    }

    /**
     * Retourne une entree d'historique a partir de son identifiant.
     *
     * @param id identifiant de l'historique
     * @return l'entree correspondante
     */
    @GetMapping("/task-histories/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public TaskHistory findById(@PathVariable Long id) {
        return taskHistoryService.findById(id);
    }

    /**
     * Cree une entree d'historique.
     *
     * @param taskHistory entree a enregistrer
     * @return identifiant genere
     */
    @PostMapping("/task-histories")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long create(@RequestBody TaskHistory taskHistory) {
        return taskHistoryService.create(taskHistory);
    }

    /**
     * Supprime une entree d'historique existante.
     *
     * @param id identifiant de l'entree a supprimer
     */
    @DeleteMapping("/task-histories/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        TaskHistory taskHistory = taskHistoryService.findById(id);
        taskHistoryService.delete(taskHistory);
    }
}
