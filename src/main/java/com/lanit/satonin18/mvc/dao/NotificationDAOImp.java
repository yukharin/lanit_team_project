package com.lanit.satonin18.mvc.dao;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.entity.Notification;
import com.lanit.satonin18.mvc.entity.NotificationStatus;
import com.lanit.satonin18.mvc.entity.Organization;
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
    @Override
    public Pagination<Notification> listByFilterOrg_Order_Pagination(
            Organization organization,
            String orderFieldName, boolean desc,
            Pagination<Notification> pagination){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

//            List<Notification> notifications = session.createQuery("from Notification", Notification.class).listByFilterOrg_Order_Pagination();
            StringBuilder sql = new StringBuilder("FROM Notification n WHERE n.organization = :organization ORDER BY n."+orderFieldName);
            if(desc) sql.append(" DESC");
            Query<Notification> query = session.createQuery(sql.toString()
                    , Notification.class);
            query.setParameter("organization", organization);
//            query.setParameter("orderFieldName", orderFieldName);

            Pagination<Notification> result = pagination.initQuery(query);

            tx1.commit();
            return result;
        }
    }

    @Override
    public List<Notification> filterDataAndNoArchiveNotifications(List<Notification> currentNotifications, List<NotificationStatus> sendedStatuses){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Query query = null;
            query = session.createQuery(
                    "FROM Notification as n WHERE n.notificationStatus IN(:sendedStatuses)",
                    Notification.class);
//            query.setParameterList("currentNotifications", currentNotifications);
            query.setParameterList("sendedStatuses", sendedStatuses);

            List<Notification> list = query.getResultList();
            tx1.commit();
            return list;
        }
    }

    @Override
    public List<Notification> filterOrgAndNotificStatuses(Organization organization, List<NotificationStatus> listNotificStatus){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Query query = null;
            query = session.createQuery(
                    "FROM Notification n WHERE n.organization = :organization AND n.notificationStatus IN(:listNotificStatus)",
                    Notification.class);
            query.setParameter("organization", organization);
            query.setParameterList("listNotificStatus", listNotificStatus);

            List<Notification> list = query.getResultList();
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
//            Query query = null;
//            query = session.createNativeQuery(sql.toString());
//            for (int i=0; i<ids.length; i++) {
//                query.setParameter(
//                        "theNotificationStatusId#"+i,
//                        Integer.parseInt(ids[i])
//                );
//            }

//            List<NotificationStatus> listNotificStatus = new ArrayList<>();
//            for (int i=0; i<ids.length; i++) {
////                ids[i];
//            }
//            Query query = null;
//            query = session.createQuery(
//                    "FROM Notification "+
////                            Notification.class.getSimpleName()+", "+
////                            NotificationStatus.class.getSimpleName()+" "+
//                            "WHERE Notification.notificationStatus IN (:listNotificStatus)",
//                    Notification.class);
//            query.setParameterList("listNotificStatus", listNotificStatus);

            //--------------------------------------------
//            StringBuilder sql = new StringBuilder();
//            for (int i=0; i<listNotificStatus.size(); i++) {
//                if(i==0){
//                    sql.append("FROM Notification WHERE Notification.notificationStatus = :theNotificationStatus"+i);
//                }
//                sql.append(" OR Notification.notificationStatus = :theNotificationStatus"+i);
//            }

            Query query = null;
            query = session.createQuery(
                    "FROM Notification n WHERE n IN (:currentNotifications) AND n.notificationStatus IN(:listNotificStatus)",
                    Notification.class);
            query.setParameterList("listNotificStatus", listNotificStatus);
            query.setParameterList("currentNotifications", currentNotifications);
            //query.setParameterList("listNotificStatus", listNotificStatus);
//            for (int i=0; i<listNotificStatus.size(); i++) {
//                query.setParameter(
//                        "theNotificationStatus"+i,
//                        listNotificStatus.get(i)
//                );
//            }

            List<Notification> list = query.getResultList();
            tx1.commit();
            return list;
        }
    }

    @Override
    public List<Notification> filterOrg(Organization organization){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Query query = null;
            query = session.createQuery(
                    "FROM Notification n WHERE n.organization = :organization",
                    Notification.class);
            query.setParameter("organization", organization);

            List<Notification> list = query.getResultList();
            tx1.commit();
            return list;
        }
    }

    @Override
    public List<Notification> filter_Org_NotificStatuses_Archive(Organization organization, List<NotificationStatus> listNotificStatus, List<NotificationStatus> listArchiveStatus){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Query query = null;
            query = session.createQuery(
                    "FROM Notification n WHERE n.organization = :organization AND n.notificationStatus IN(:listNotificStatus) AND NOT (n.notificationStatus IN (:listArchiveStatus) AND n.dateResponse <= current_date + 15 )",
                    Notification.class);
            query.setParameter("organization", organization);
            query.setParameterList("listNotificStatus", listNotificStatus);
            query.setParameterList("listArchiveStatus", listArchiveStatus);

            List<Notification> list = query.getResultList();
            tx1.commit();
            return list;
        }
    }

    @Override
    public Pagination<Notification>  filter_Org_NotificStatuses_Archive_Order_Pagination(
            Organization organization,
            List<NotificationStatus> listNotificStatus,
            boolean showArchive, List<NotificationStatus> listArchiveStatus,
            String orderFieldName, boolean desc,
            Pagination<Notification> pagination
    ){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            //TODO REPLASE ON Simple Join string
            boolean flagContinuationCondition = false;

            StringBuilder sql = new StringBuilder(
                    "FROM Notification n");
            if( ! organization.isGovernment()) {
                sql.append(" WHERE");
                sql.append(" n.organization = :organization");
                flagContinuationCondition = true;
            }
            if (listNotificStatus != null) {
                if(listNotificStatus.size()==0) return new Pagination.EmptyPagination<Notification>(pagination);
                if(flagContinuationCondition) {sql.append(" AND"); flagContinuationCondition=true;}
                else {sql.append(" WHERE"); flagContinuationCondition = true;}
                sql.append(" n.notificationStatus IN(:listNotificStatus)");
            }
            if ( ! showArchive) {
                if(flagContinuationCondition) {sql.append(" AND"); flagContinuationCondition=true;}
                else {sql.append(" WHERE"); flagContinuationCondition = true;}
                sql.append(" NOT (n.notificationStatus IN (:listArchiveStatus) AND n.dateResponse <= current_date + 15 )");
            }
            
            sql.append(" ORDER BY n."+orderFieldName);
            if(desc) sql.append(" DESC");

            Query<Notification> query = session.createQuery(
                    sql.toString(),
                    Notification.class);
            if( ! organization.isGovernment()) query.setParameter("organization", organization);
            if (listNotificStatus != null) query.setParameterList("listNotificStatus", listNotificStatus);
            if ( ! showArchive) query.setParameterList("listArchiveStatus", listArchiveStatus);
//            query.setParameter("orderFieldName", orderFieldName);

            Pagination<Notification> result = pagination.initQuery(query);

            tx1.commit();
            return result;
        }
    }


}


