package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.NotificationDAO;
import com.lanit.lkz_project.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    @Transactional
    public void addNotification(Notification notification) {
        notificationDAO.addNotification(notification);
    }

    @Transactional
    public void updateNotification(Notification notification) {
        notificationDAO.updateNotification(notification);
    }

    @Transactional
    public void removeNotification(Notification notification) {
        notificationDAO.removeNotification(notification);
    }

    @Transactional
    public void removeNotification(long id) {
        notificationDAO.removeNotification(id);
    }

    @Transactional
    public Notification getNotification(long id) {
        return notificationDAO.getNotification(id);
    }

    @Transactional
    public List<Notification> notifications() {
        return notificationDAO.notifications();
    }


}
