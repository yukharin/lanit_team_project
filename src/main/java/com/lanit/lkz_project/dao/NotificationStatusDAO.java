package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.NotificationStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationStatusDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addNotificationStatus(NotificationStatus status) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(status);
    }

    public void updateNotificationStatus(NotificationStatus status) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(status);
    }

    public void deleteNotificationStatus(NotificationStatus status) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(status);
    }

    public NotificationStatus getNotificationStatus(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(NotificationStatus.class, id);
    }

    public List<NotificationStatus> notificationStatuses() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM NotificationStatus ").list();
    }

}
