package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.NotificationDAO;
import com.lanit.lkz_project.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    @Transactional
    public void addNotification(@NotNull Notification notification) {
        notificationDAO.addNotification(notification);
    }

    @Transactional
    public void updateNotification(@NotNull Notification notification) {
        notificationDAO.updateNotification(notification);
    }

    @Transactional
    public void removeNotification(@NotNull Notification notification) {
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

    public Page<Notification> findPaginated(Pageable pageable) {
        List<Notification> notifications = notificationDAO.notifications();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Notification> resultList;

        if (notifications.size() < startItem) {
            resultList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, notifications.size());
            resultList = notifications.subList(startItem, toIndex);
        }
        return new PageImpl<>(resultList, PageRequest.of(currentPage, pageSize), notifications.size());
    }

}
