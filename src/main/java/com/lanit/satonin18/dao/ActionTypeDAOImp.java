package com.lanit.satonin18.dao;


import com.lanit.satonin18.model.ActionType;
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
   /*
       @Override
       public List<ActionType> searchActionTypes(String theSearchName) {
           Session currentSession = sessionFactory.getCurrentSession();
   
           Query theQuery = null;
           // only search by name if theSearchName is not empty
           if (theSearchName != null && theSearchName.trim().length() > 0) {
               // search for firstName or lastName ... case insensitive
               theQuery = currentSession.createQuery("from ActionType where name like :theName", ActionType.class);
               theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
           }
           else {
               // theSearchName is empty ... so just get all customers
               theQuery = currentSession.createQuery("from ActionType", ActionType.class);
           }
           List<ActionType> notific_list = theQuery.getResultList();
           return notific_list;
       }
   */
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
         session.close();
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