package com.mooc.formulaone.services.impl;

import com.mooc.formulaone.dao.NotificationRepository;
import com.mooc.formulaone.exceptions.EntityDontExistException;
import com.mooc.formulaone.models.Notification;
import com.mooc.formulaone.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> findAll() {
        List<Notification> notifications = new ArrayList<>();
        notificationRepository.findAll().forEach(notifications::add);
        return notifications;
    }

    @Override
    public Notification findById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            return notification.get();
        }
        throw new EntityDontExistException();
    }

    @Override
    public Long create(Notification notification) {
        return notificationRepository.save(notification).getId();
    }

    @Override
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }
}
