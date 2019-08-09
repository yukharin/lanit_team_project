package com.lanit.satonin18.app.repository;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActionRepositoryImpl
        implements ActionRepositoryCustomized
{
    @PersistenceContext
    EntityManager em;

    public PageImpl<Action> filter_Notific_Order_Pagination(
            Notification notification,
            String orderFieldName, boolean desc,
            Pageable actionPagination) {
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
        long countAll = q_COUNT.getSingleResult();
//
//        return actionPagination.initQuery(q, countAll);
        int pageNumber = actionPagination.getPageNumber();
        int pageSize = actionPagination.getPageSize();

        q.setFirstResult((pageNumber)*pageSize);
        q.setMaxResults(pageSize);

        List<Action> list = q.getResultList();

        return new PageImpl(list, actionPagination, countAll);
    }
}