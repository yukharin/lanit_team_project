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

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository("actionDAO")
public class ActionDAOImp implements ActionDAO {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @Transactional
   public void save(Action action) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
      sessionFactory.getCurrentSession()
              .save(action); //saveOrUpdate() save(), persist(), merge()
   }

   @Override
   @Transactional
   public void saveOrUpdate(Action action) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
      sessionFactory.getCurrentSession()
              .saveOrUpdate(action);
   }

   @Override
   @Transactional
   public void update(Action action) {
      sessionFactory.getCurrentSession()
              .update(action);
   }

   @Override
   @Transactional
   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();

      Action action = session.load(Action.class, id);
      if (action != null)
         session.delete(action);
   }

   @Override
//   @Transactional(readOnly = true)
   public Action getById(int id) {
      try (final Session session = sessionFactory.openSession();) {
         Action action = session.get(Action.class, id);
         return action;
      }
   }

   @Override
   public List<Action> list() {
      try (final Session session = sessionFactory.openSession();) {
         List<Action> actions = session.createQuery("from Action", Action.class).list();
         return actions;
      }
   }

   public List<Action> listByIdNotification(Notification notification) {
      try (final Session session = sessionFactory.openSession();) {

         Query<Action> query = session.createQuery("FROM Action AS a WHERE a.notification = :notification", Action.class);
         query.setParameter("notification", notification);

         List<Action> actions = query.list();
         return actions;
      }
   }

   public Pagination<Action> filter_Notific_Order_Pagination(
           Notification notification,
           String orderFieldName, boolean desc,
           Pagination<Action> actionPagination) {
      try (final Session session = sessionFactory.openSession();) {
         CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

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
         }else{
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
         Query<Action> q = session.createQuery(criteriaQuery);


         criteriaQuery_COUNT
                 .select(
                         criteriaBuilder.count(rootAction_COUNT)
                 )
                 .where(conditionsList_COUNT.toArray(new Predicate[]{}))
         ;
         Query<Long> q_COUNT = session.createQuery(criteriaQuery_COUNT);
         long count_COUNT = q_COUNT.getSingleResult();

         Pagination<Action> result = actionPagination.initQuery(q, count_COUNT);
         return result;
      }
   }
}