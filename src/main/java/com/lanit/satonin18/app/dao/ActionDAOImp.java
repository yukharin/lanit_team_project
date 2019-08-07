package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;

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
import javax.transaction.TransactionManager;
import java.util.ArrayList;
import java.util.List;

@Repository("actionDAO")
public class ActionDAOImp implements ActionDAO {
//    @Autowired
//    private SessionFactory sessionFactory;
//    protected Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void save(Action action) {  //TODO need save @NotNull final IN ARG //throws Exc
        em.persist(action);
    }

    @Override
    @Transactional
    public void update(Action action) {
        em.merge(action);
    }

    @Override
    @Transactional
    public void delete(Action action) {
        em.remove(action);
    }

    @Override
    public Action getById(int id) {
        return em.find(Action.class, id);
    }

    @Override
    public List<Action> list() {
        return em.createQuery("from Action", Action.class)
                .getResultList();
    }
    @Override
    @Transactional
    public void deleteById(int id) {
        Action action = em.find(Action.class, id);
        if (action != null)
            em.remove(action);
    }

    public List<Action> listByIdNotification(Notification notification) {
        TypedQuery<Action> query = em.createQuery("FROM Action AS a WHERE a.notification = :notification", Action.class);
        query.setParameter("notification", notification);

        return query.getResultList();
    }

    public Pagination<Action> filter_Notific_Order_Pagination(
            Notification notification,
            String orderFieldName, boolean desc,
            Pagination<Action> actionPagination) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

//            SELECT * WHERE ...
        CriteriaQuery<Action> criteriaQuery = criteriaBuilder.createQuery(Action.class);
        Root<Action> rootAction = criteriaQuery.from(Action.class);
//            SELECT COUNT(*)
        CriteriaQuery<Long> criteriaQuery_COUNT = criteriaBuilder.createQuery(Long.class);
        Root<Action> rootAction_COUNT = criteriaQuery_COUNT.from(Action.class);

        List<Predicate> conditionsList = new ArrayList<Predicate>();
        List<Predicate> conditionsList_COUNT = new ArrayList<Predicate>();
        Order order = null;
//---------------------------------------------
        conditionsList.add(
                criteriaBuilder.equal(
                        rootAction.<Notification>get("notification"),
                        notification
                )
        );
        conditionsList_COUNT.add(
                criteriaBuilder.equal(
                        rootAction_COUNT.<Notification>get("notification"),
                        notification
                )
        );

        //Еxample: orderFieldName = organization.name
        //todo can be replace on loop
        if(orderFieldName.contains(".")){
            String[] mas = orderFieldName.split("\\.");
            if(desc) {
                order = criteriaBuilder.desc(
                        rootAction.get(mas[0]).get(mas[1])
                );
            }else{
                order = criteriaBuilder.asc(
                        rootAction.get(mas[0]).get(mas[1])
                );
            }
        } else{
            if(desc) {
                order = criteriaBuilder.desc(
                        rootAction.get(orderFieldName));
            }else{
                order = criteriaBuilder.asc(
                        rootAction.get(orderFieldName));
            }
        }
//---------------------------------------------------
        criteriaQuery
                .select(rootAction)
                .where(conditionsList.toArray(new Predicate[]{}))
                .orderBy(order);
        TypedQuery<Action> q = em.createQuery(criteriaQuery);

        criteriaQuery_COUNT
                .select(
                        criteriaBuilder.count(rootAction_COUNT)
                )
                .where(conditionsList_COUNT.toArray(new Predicate[]{}))
        ;
        TypedQuery<Long> q_COUNT = em.createQuery(criteriaQuery_COUNT);
        long count_COUNT = q_COUNT.getSingleResult();
//
        return actionPagination.initQuery(q, count_COUNT);
    }
}