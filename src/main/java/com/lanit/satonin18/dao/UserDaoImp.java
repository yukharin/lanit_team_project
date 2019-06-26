package com.lanit.satonin18.dao;


import com.lanit.satonin18.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("userDAO")
public class UserDAOImp implements CrudDAO<User> {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void saveOrUpdate(User user) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         session.saveOrUpdate(user);

         tx1.commit();
      }
   }

   @Override
   public void update(User user) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         session.update(user);

         tx1.commit();
      }
   }
   /*
       @Override
       public List<User> searchUsers(String theSearchName) {
           Session currentSession = sessionFactory.getCurrentSession();
   
           Query theQuery = null;
           // only search by name if theSearchName is not empty
           if (theSearchName != null && theSearchName.trim().length() > 0) {
               // search for firstName or lastName ... case insensitive
               theQuery = currentSession.createQuery("from User where name like :theName", User.class);
               theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
           }
           else {
               // theSearchName is empty ... so just get all customers
               theQuery = currentSession.createQuery("from User", User.class);
           }
           List<User> list = theQuery.getResultList();
           return list;
       }
   */
   @Override
   public void delete(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         User user = session.load(User.class, id);

         if(user != null)
            session.delete(user);

         tx1.commit();
      }
   }

   @Override
   public User getById(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         User user = session.get(User.class, id);

         tx1.commit();
         session.close();
         return user;
      }
   }

   @Override
   public List<User> list() {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         List<User> users = session.createQuery("from User", User.class).list();

         tx1.commit();
         return users;
      }
   }
}