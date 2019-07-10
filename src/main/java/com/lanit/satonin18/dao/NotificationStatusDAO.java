package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.NotificationStatus;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationStatusDAO extends CrudDAO<NotificationStatus> {

   List<NotificationStatus> filterIds(String[] ids) ;

   @Override
   void saveOrUpdate(NotificationStatus entity);

   @Override
   void update(NotificationStatus entity) ;


   @Override
   void delete(int id) ;

   @Override
   NotificationStatus getById(int id) ;

   @Override
   List<NotificationStatus> list() ;
}