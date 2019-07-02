package com.lanit.satonin18.dao;


import com.lanit.satonin18.model.NotificationStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("notificationStatusDAO")
public class NotificationStatusDAOImp implements CrudDAO<NotificationStatus> {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void saveOrUpdate(NotificationStatus notificationStatus) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         session.saveOrUpdate(notificationStatus);

         tx1.commit();
      }
   }

   @Override
   public void update(NotificationStatus notificationStatus) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         session.update(notificationStatus);

         tx1.commit();
      }
   }

   @Override
   public void delete(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         NotificationStatus notificationStatus = session.load(NotificationStatus.class, id);

         if(notificationStatus != null)
            session.delete(notificationStatus);

         tx1.commit();
      }
   }

   @Override
   public NotificationStatus getById(int id) {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         NotificationStatus notificationStatus = session.get(NotificationStatus.class, id);

         tx1.commit();
         session.close();
         return notificationStatus;
      }
   }

   @Override
   public List<NotificationStatus> list() {
      //Session session = sessionFactory.getCurrentSession();
      try(final Session session = sessionFactory.openSession();){
         Transaction tx1 = session.beginTransaction();

         List<NotificationStatus> notificationStatuss = session.createQuery("from NotificationStatus", NotificationStatus.class).list();

         tx1.commit();
         return notificationStatuss;
      }
   }
}