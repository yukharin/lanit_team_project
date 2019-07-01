package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("notificationDAO")
public class NotificationDAOImp implements CrudDAO<Notification> {

    @Autowired
    private SessionFactory sessionFactory;

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
    /*
        @Override
        public List<Notification> searchNotifications(String theSearchName) {
            Session currentSession = sessionFactory.getCurrentSession();
    
            Query theQuery = null;
            // only search by name if theSearchName is not empty
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                // search for firstName or lastName ... case insensitive
                theQuery = currentSession.createQuery("from Notification where name like :theName", Notification.class);
                theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
            }
            else {
                // theSearchName is empty ... so just get all customers
                theQuery = currentSession.createQuery("from Notification", Notification.class);
            }
            List<Notification> notific_list = theQuery.getResultList();
            return notific_list;
        }
    */
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