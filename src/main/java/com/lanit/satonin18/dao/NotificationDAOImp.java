package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.Notification;
import com.lanit.satonin18.model.NotificationStatus;
import com.lanit.satonin18.model.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Repository("notificationDAO")
public class NotificationDAOImp implements NotificationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Notification> filterOrgAndNotificStatuses(Organization organization, List<NotificationStatus> listNotificStatus){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Query theQuery = null;
            theQuery = session.createQuery(
                    "FROM Notification n WHERE n.organization = :organization AND n.notificationStatus IN(:listNotificStatus)",
                    Notification.class);
            theQuery.setParameter("organization", organization);
            theQuery.setParameterList("listNotificStatus", listNotificStatus);

            List<Notification> list = theQuery.getResultList();
            tx1.commit();
            return list;
        }
    }

    @Override
    public List<Notification> filterCurrentsAndNotificStatuses(List<Notification> currentNotifications, List<NotificationStatus> listNotificStatus){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();
//           //ONLY NEED JOIN(or use List) AND HQL-request
//            StringBuilder sql = new StringBuilder();
//            for (int i=0; i<ids.length; i++) {
//                if(i==0){
//                    sql.append("SELECT * FROM notifications WHERE id_notification_status = :theNotificationStatusId#"+i);
//                }
//                sql.append("OR id_notification_status = :theNotificationStatusId#"+i);
//            }
//            Query theQuery = null;
//            theQuery = session.createNativeQuery(sql.toString());
//            for (int i=0; i<ids.length; i++) {
//                theQuery.setParameter(
//                        "theNotificationStatusId#"+i,
//                        Integer.parseInt(ids[i])
//                );
//            }

//            List<NotificationStatus> listNotificStatus = new ArrayList<>();
//            for (int i=0; i<ids.length; i++) {
////                ids[i];
//            }
//            Query theQuery = null;
//            theQuery = session.createQuery(
//                    "FROM Notification "+
////                            Notification.class.getSimpleName()+", "+
////                            NotificationStatus.class.getSimpleName()+" "+
//                            "WHERE Notification.notificationStatus IN (:listNotificStatus)",
//                    Notification.class);
//            theQuery.setParameterList("listNotificStatus", listNotificStatus);

            //--------------------------------------------
//            StringBuilder sql = new StringBuilder();
//            for (int i=0; i<listNotificStatus.size(); i++) {
//                if(i==0){
//                    sql.append("FROM Notification WHERE Notification.notificationStatus = :theNotificationStatus"+i);
//                }
//                sql.append(" OR Notification.notificationStatus = :theNotificationStatus"+i);
//            }

            Query theQuery = null;
            theQuery = session.createQuery(
                    "FROM Notification n WHERE n IN (:currentNotifications) AND n.notificationStatus IN(:listNotificStatus)",
                    Notification.class);
            theQuery.setParameterList("listNotificStatus", listNotificStatus);
            theQuery.setParameterList("currentNotifications", currentNotifications);
            //theQuery.setParameterList("listNotificStatus", listNotificStatus);
//            for (int i=0; i<listNotificStatus.size(); i++) {
//                theQuery.setParameter(
//                        "theNotificationStatus"+i,
//                        listNotificStatus.get(i)
//                );
//            }

            List<Notification> list = theQuery.getResultList();
            tx1.commit();
            return list;
        }
    }

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