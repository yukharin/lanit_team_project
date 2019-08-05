package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDAO")
public class UserDAOImp implements UserDAO{

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @Transactional
   public void saveOrUpdate(User user) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
      sessionFactory.getCurrentSession()
              .saveOrUpdate(user);
   }

   @Override
   @Transactional
   public void update(User user) {
      sessionFactory.getCurrentSession()
              .update(user);
   }

   @Override
   @Transactional
   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();

      User user = session.load(User.class, id);
      if(user != null)
         session.delete(user);
   }

   @Override
   public User getById(int id) {
      try(final Session session = sessionFactory.openSession();){
         User user = session.get(User.class, id);
         return user;
      }
   }

   @Override
   public List<User> list() {
      try(final Session session = sessionFactory.openSession();){
         List<User> users = session.createQuery("from User", User.class).list();
         return users;
      }
   }
   @Override
   public List<User> searchUserByLastName(String theSearchName) {
      try(final Session session = sessionFactory.openSession();){

         Query theQuery = null;
         // only search by name if theSearchName is not empty
         if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = session.createQuery("from User where lastName like :theName", User.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
         }
         else {
            // theSearchName is empty ... so just get all customers
            theQuery = session.createQuery("from User", User.class);
         }
         List<User> list = theQuery.getResultList();
         return list;
      }
   }
}