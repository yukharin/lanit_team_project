package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.repositories.entitity_repositories.NotificationRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public void addNotification(@NonNull Notification notification) {
        notificationRepository.save(notification);
    }

    @Transactional
    public void updateNotification(@NonNull Notification notification) {
        notificationRepository.save(notification);
    }

    @Transactional
    public void removeNotification(@NonNull Notification notification) {
        notificationRepository.delete(notification);
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
    public Iterable<Notification> notifications() {
        return notificationRepository.findAll();
    }


}
