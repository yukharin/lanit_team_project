package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.Notification;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationDAO extends CrudDAO<Notification> {

//   public List<Notification> getNotificationsByIdOrg(int theOrgId);

   @Override
   void saveOrUpdate(Notification entity);

   @Override
   void update(Notification entity) ;


   @Override
   void delete(int id) ;

   @Override
   Notification getById(int id) ;

   @Override
   List<Notification> list() ;
}
