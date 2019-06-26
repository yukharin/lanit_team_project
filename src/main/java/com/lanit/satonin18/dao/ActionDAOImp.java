package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("actionDAO")
public class ActionDAOImp implements CrudDAO<Action> {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void saveOrUpdate(Action action) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         session.saveOrUpdate(action);

         tx1.commit();
      }
   }

   @Override
   public void update(Action action) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         session.update(action);

         tx1.commit();
      }
   }
   /*
       @Override
       public List<Action> searchActions(String theSearchName) {
           Session currentSession = sessionFactory.getCurrentSession();
   
           Query theQuery = null;
           // only search by name if theSearchName is not empty
           if (theSearchName != null && theSearchName.trim().length() > 0) {
               // search for firstName or lastName ... case insensitive
               theQuery = currentSession.createQuery("from Action where name like :theName", Action.class);
               theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
           }
           else {
               // theSearchName is empty ... so just get all customers
               theQuery = currentSession.createQuery("from Action", Action.class);
           }
           List<Action> list = theQuery.getResultList();
           return list;
       }
   */
   @Override
   public void delete(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         Action action = session.load(Action.class, id);

         if(action != null)
            session.delete(action);

         tx1.commit();
      }
   }

   @Override
   public Action getById(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         Action action = session.get(Action.class, id);

         tx1.commit();
         session.close();
         return action;
      }
   }

   @Override
   public List<Action> list() {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         List<Action> actions = session.createQuery("from Action", Action.class).list();

         tx1.commit();
         return actions;
      }
   }
}