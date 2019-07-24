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
//@Transactional
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
//            Transaction tx1 = session.beginTransaction();

            Notification notification = session.get(Notification.class, id);

//            tx1.commit();
            return notification;
        }
    }

    @Override
    public List<Notification> list() {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
//            Transaction tx1 = session.beginTransaction();

            List<Notification> notifications = session.createQuery("from Notification", Notification.class).list();

//            tx1.commit();
            return notifications;
        }
    }

    @Override
    public Pagination<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
            Organization organization,
            List<NotificationStatus> listNotificStatus,
            boolean showArchive, List<NotificationStatus> listArchiveStatus,
            String orderFieldName, boolean desc,
            Pagination<Notification> pagination
    ){
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
//            Transaction tx1 = session.beginTransaction();

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


            StringBuilder countStringSql = new StringBuilder(sql);
            countStringSql.insert(0,"SELECT COUNT (*) ");
            Query<Long> queryCount = session.createQuery(
                    countStringSql.toString(),
                    Long.class);
            if( ! organization.isGovernment()) queryCount.setParameter("organization", organization);
            if (listNotificStatus != null) queryCount.setParameterList("listNotificStatus", listNotificStatus);
            if ( ! showArchive) queryCount.setParameterList("listArchiveStatus", listArchiveStatus);
            Long count = (Long) queryCount.uniqueResult();


            Query <Notification> query = session.createQuery(
                    sql.toString(),
                    Notification.class);
            if( ! organization.isGovernment()) query.setParameter("organization", organization);
            if (listNotificStatus != null) query.setParameterList("listNotificStatus", listNotificStatus);
            if ( ! showArchive) query.setParameterList("listArchiveStatus", listArchiveStatus);
//            query.setParameter("orderFieldName", orderFieldName);

            //Pagination<Notification> result = pagination.initQuery(query);


            Pagination<Notification> result = pagination.initQuery(query, count);

//            tx1.commit();
            return result;
        }
    }


}


