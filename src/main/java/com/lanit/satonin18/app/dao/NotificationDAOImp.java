package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_in_db.Status;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository("notificationDAO")
public class NotificationDAOImp implements NotificationDAO {
//    @Autowired
//    private SessionFactory sessionFactory;
//    protected Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void save(Notification notification) {
        em.persist(notification);
    }

    @Override
    @Transactional
    public void update(Notification notification) {
        em.merge(notification);
    }

    @Override
    @Transactional
    public void delete(Notification notification) {
        em.remove(notification);
    }

    @Override
    public Notification getById(int id) {
        return em.find(Notification.class, id);
    }

    @Override
    public List<Notification> list() {
        return em.createQuery("from Notification", Notification.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Notification notification = em.find(Notification.class, id);
        if(notification != null)
            em.remove(notification);
    }
    @Override
    public Pagination<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
            Organization organization,
            List<Status> listNotificStatus,
            boolean showArchive, List<Status> listArchiveStatus,
            String orderFieldName, boolean desc,
            Pagination<Notification> pagination){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

//            SELECT * WHERE ...
        CriteriaQuery<Notification> criteriaQuery = criteriaBuilder.createQuery(Notification.class);
        Root<Notification> rootNotific = criteriaQuery.from(Notification.class);
//            SELECT COUNT(*)
        CriteriaQuery<Long> criteriaQuery_COUNT = criteriaBuilder.createQuery(Long.class);
        Root<Notification> rootNotific_COUNT = criteriaQuery_COUNT.from(Notification.class);

        List<Predicate> conditionsList = new ArrayList<Predicate>();
        List<Predicate> conditionsList_COUNT = new ArrayList<Predicate>();
        Order order = null;
//---------------------------------------------------
        if( ! organization.isGovernment()) {
            conditionsList.add(
                    criteriaBuilder.equal(
                            rootNotific.<Organization>get("organization"),
                            organization
                    )
            );
            conditionsList_COUNT.add(
                    criteriaBuilder.equal(
                            rootNotific_COUNT.<Organization>get("organization"),
                            organization
                    )
            );
        }
        Expression<Status> expStatus = rootNotific.get("status");
        Expression<Status> expStatus_COUNT = rootNotific_COUNT.get("status");
        if (listNotificStatus != null) {
            if(listNotificStatus.size() == 0) return pagination.new EmptyPagination<Notification>(pagination);
            conditionsList.add(
                    expStatus.in(listNotificStatus)
            );
            conditionsList_COUNT.add(
                    expStatus_COUNT.in(listNotificStatus)
            );
        }
        Expression<Date> expDateResponse = rootNotific.get("dateResponse");
        Expression<Date> expDateResponse_COUNT = rootNotific_COUNT.get("dateResponse");

        Date dateNow = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.DATE, -14);
        Date dateNow_minus14day = new Date(calendar.getTimeInMillis());

        if ( ! showArchive) {
            conditionsList.add(
                    criteriaBuilder.not(
                            criteriaBuilder.and(
                                    expStatus.in(listArchiveStatus),
                                    criteriaBuilder.lessThan/*OrEqualTo*/(expDateResponse, dateNow_minus14day)
                            )
                    )
            );
            conditionsList_COUNT.add(
                    criteriaBuilder.not(
                            criteriaBuilder.and(
                                    expStatus.in(listArchiveStatus),
                                    criteriaBuilder.lessThan/*OrEqualTo*/(expDateResponse_COUNT, dateNow_minus14day)
                            )
                    )
            );
        }
        //Еxample: orderFieldName = organization.name
        if(orderFieldName.contains(".")){
            String[] mas = orderFieldName.split("\\.");
            if(desc) {
                order = criteriaBuilder.desc(
                        rootNotific.get(mas[0]).get(mas[1])
                );
            }else{
                order = criteriaBuilder.asc(
                        rootNotific.get(mas[0]).get(mas[1])
                );
            }
        }else{
            if(desc) {
                order = criteriaBuilder.desc(
                        rootNotific.get(orderFieldName));
            }else{
                order = criteriaBuilder.asc(
                        rootNotific.get(orderFieldName));
            }
        }
//---------------------------------------------------
        criteriaQuery
                .select(rootNotific)
                .where(conditionsList.toArray(new Predicate[]{}))
                .orderBy(order)
        ;
        TypedQuery<Notification> q = em.createQuery(criteriaQuery);

        criteriaQuery_COUNT
                .select(
                        criteriaBuilder.count(rootNotific_COUNT)
                )
                .where(
                        conditionsList_COUNT.toArray(new Predicate[]{})
                )
        ;
        TypedQuery<Long> q_COUNT = em.createQuery(criteriaQuery_COUNT);
        long count_COUNT = q_COUNT.getSingleResult();
//
        return pagination.initQuery(q, count_COUNT);
    }
}


