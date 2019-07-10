package com.lanit.satonin18.mvc.dao;


import com.lanit.satonin18.mvc.entity.ActionType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("actionTypeDAO")
public class ActionTypeDAOImp implements CrudDAO<ActionType> {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void saveOrUpdate(ActionType actionType) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         session.saveOrUpdate(actionType);

         tx1.commit();
      }
   }

   @Override
   public void update(ActionType actionType) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         session.update(actionType);

         tx1.commit();
      }
   }
   @Override
   public void delete(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         ActionType actionType = session.load(ActionType.class, id);

         if(actionType != null)
            session.delete(actionType);

         tx1.commit();
      }
   }

   @Override
   public ActionType getById(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         ActionType actionType = session.get(ActionType.class, id);

         tx1.commit();
         return actionType;
      }
   }

   @Override
   public List<ActionType> list() {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         List<ActionType> actionTypes = session.createQuery("from ActionType", ActionType.class).list();

         tx1.commit();
         return actionTypes;
      }
   }
}