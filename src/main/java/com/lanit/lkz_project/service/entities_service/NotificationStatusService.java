package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.NotificationStatusDAO;
import com.lanit.lkz_project.entities.NotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationStatusService {

    @Autowired
    private NotificationStatusDAO dao;

    @Transactional
    public NotificationStatus getNotificationStatus(long id) {
        return dao.getNotificationStatus(id);
    }

}
