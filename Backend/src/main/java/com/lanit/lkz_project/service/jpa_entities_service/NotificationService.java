package com.lanit.lkz_project.service.jpa_entities_service;

import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.repositories.entitity_repositories.NotificationRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public void addNotification(@Valid @NonNull Notification notification) {
        notificationRepository.save(notification);
    }

    @Transactional
    public void updateNotification(@NonNull Notification notification) {
        notificationRepository.save(notification);
    }

    @Transactional
    public void removeNotification(long id) {
        notificationRepository.deleteById(id);
    }

    @Transactional
    public Notification getNotification(long id) {
        return notificationRepository.findById(id).get();
    }

    @Transactional
    public List<Notification> notifications() {
        return notificationRepository.findAll();
    }


}
