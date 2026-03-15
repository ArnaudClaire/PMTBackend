package com.mooc.formulaone.services.impl;

import com.mooc.formulaone.dao.TaskRepository;
import com.mooc.formulaone.exceptions.EntityDontExistException;
import com.mooc.formulaone.models.Task;
import com.mooc.formulaone.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        }
        throw new EntityDontExistException();
    }

    @Override
    public Long create(Task task) {
        return taskRepository.save(task).getId();
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
