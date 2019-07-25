package com.lanit.satonin18.mvc.dao;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.entity.Action;
import com.lanit.satonin18.mvc.entity.Notification;
import com.lanit.satonin18.mvc.entity.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository("actionDAO")
//@Transactional
public class ActionDAOImp implements ActionDAO {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void saveOrUpdate(Action action) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
      //Session session = sessionFactory.getCurrentSession();
      try (final Session session = sessionFactory.openSession();) {
         Transaction tx1 = session.beginTransaction();

         session.saveOrUpdate(action);

         tx1.commit();
      }
   }
   @Override
   public void save(Action action) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
//      Session session = sessionFactory.getCurrentSession();
      try (final Session session = sessionFactory.openSession();) {
         Transaction tx1 = session.beginTransaction();

         session.save(action); //saveOrUpdate() save(), persist(), merge()

         //session.flush();
         tx1.commit();
      }
   }

   @Override
   public void update(Action action) {
      //Session session = sessionFactory.getCurrentSession();
      try (final Session session = sessionFactory.openSession();) {
         Transaction tx1 = session.beginTransaction();

         session.update(action);

         tx1.commit();
      }
   }

   @Override
   public void delete(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try (final Session session = sessionFactory.openSession();) {
         Transaction tx1 = session.beginTransaction();

         Action action = session.load(Action.class, id);

         if (action != null)
            session.delete(action);

         tx1.commit();
      }
   }

   @Override
   public Action getById(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try (final Session session = sessionFactory.openSession();) {
//         Transaction tx1 = session.beginTransaction();

         Action action = session.get(Action.class, id);

//         tx1.commit();
         return action;
      }
   }

   @Override
   public List<Action> list() {
      //Session session = sessionFactory.getCurrentSession();
      try (final Session session = sessionFactory.openSession();) {
//         Transaction tx1 = session.beginTransaction();

         List<Action> actions = session.createQuery("from Action", Action.class).list();

//         tx1.commit();
         return actions;
      }
   }

   public List<Action> listByIdNotification(Notification notification) {
      //Session session = sessionFactory.getCurrentSession();
      try (final Session session = sessionFactory.openSession();) {
//         Transaction tx1 = session.beginTransaction();

         Query<Action> query = session.createQuery("FROM Action AS a WHERE a.notification = :notification", Action.class);

         query.setParameter("notification", notification);

         List<Action> actions = query.list();

//         tx1.commit();
         return actions;
      }
   }

   public Pagination<Action> filter_Notific_Order_Pagination(
           Notification notification,
           String orderFieldName, boolean desc,
           Pagination<Action> actionPagination) {
      //Session session = sessionFactory.getCurrentSession();
      try (final Session session = sessionFactory.openSession();) {
//         Transaction tx1 = session.beginTransaction();

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

         if(desc) {
//            criteriaQuery.orderBy(
            order = criteriaBuilder.desc(
                            rootAction.get(orderFieldName));
         }else{
//            criteriaQuery.orderBy(
            order = criteriaBuilder.asc(
                            rootAction.get(orderFieldName));
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

//         tx1.commit();
         return result;
      }
   }
}