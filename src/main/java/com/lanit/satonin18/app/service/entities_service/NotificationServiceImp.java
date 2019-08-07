package com.lanit.satonin18.app.service.entities_service;

import java.util.Collections;
import java.util.List;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dao.NotificationDAO;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("notificationService")
public class NotificationServiceImp implements NotificationService {
    @Autowired
    private NotificationDAO notificationDAO;

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Notification notification) {
        notificationDAO.save(notification);
    }

    @Override
    public void update(Notification notification) {
        notificationDAO.update(notification);
    }

    @Override
    public void delete(Notification notification) {
        notificationDAO.delete(notification);
    }

    @Override
    public Notification getById(int id) {
        return notificationDAO.getById(id);
    }

    @Override
    public List<Notification> list() {
        return notificationDAO.list();
    }

    @Override
    public void deleteById(int id) {
        notificationDAO.deleteById(id);
    }

    @Override
    public Pagination<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
            Organization organization,
            List<Status> listNotificStatus,
            boolean showArchive, List<Status> listArchiveStatus,
            String orderFieldName, boolean desc,
            Pagination<Notification> pagination
    ){
        return notificationDAO._CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
                organization,
                listNotificStatus,
                showArchive, listArchiveStatus,
                orderFieldName,  desc,
                pagination
        );
    }
}