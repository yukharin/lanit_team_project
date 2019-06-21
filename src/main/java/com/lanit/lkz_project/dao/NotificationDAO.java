package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotificationDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public void addNotification(Notification notification) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(notification);
    }

    public void updateNotification(Notification notification) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(notification);
    }

    public void deleteNotification(int id) {
        Session session = sessionFactory.getCurrentSession();
        Notification notification = session.load(Notification.class, id);
        if (notification != null) {
            session.delete(notification);
        }
    }

    public Notification getNotification(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Notification.class, id);
    }

    public List<Notification> notifications() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Notification ").list();
    }
}
