package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.NotificationStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class NotificationStatusDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addNotificationStatus(@NotNull NotificationStatus status) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(status);
    }

    public void updateNotificationStatus(@NotNull NotificationStatus status) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(status);
    }

    public void deleteNotificationStatus(@NotNull NotificationStatus status) {
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
