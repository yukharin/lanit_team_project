package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;
import java.util.List;

@Repository("notificationDAO")
public class NotificationDAOImp implements NotificationDAO {

    @Autowired
    private SessionFactory sessionFactory;

//    @Override
//    public List<Notification> getNotificationsByIdOrg(int theOrgId){
//        Session currentSession = sessionFactory.getCurrentSession();
//
//        Query theQuery = null;
//        theQuery = currentSession.createQuery("from Notification where id_org = :theOrgId", Notification.class);
//        theQuery.setParameter("theOrgId", theOrgId);
//        List<Notification> list = theQuery.getResultList();
//        return list;
//    }

    @Override
    public void saveOrUpdate(Notification notification) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            session.saveOrUpdate(notification);

            tx1.commit();
        }
    }

    @Override
    public void update(Notification notification) {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            session.update(notification);

            tx1.commit();
        }
    }

    @Override
    public void delete(int id) {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Notification notification = session.load(Notification.class, id);

            if(notification != null)
                session.delete(notification);

            tx1.commit();
        }
    }

    @Override
    public Notification getById(int id) {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Notification notification = session.get(Notification.class, id);

            tx1.commit();
            session.close();
            return notification;
        }
    }

    @Override
    public List<Notification> list() {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            List<Notification> notifications = session.createQuery("from Notification", Notification.class).list();

            tx1.commit();
            return notifications;
        }
    }
}