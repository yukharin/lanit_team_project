package com.lanit.satonin18.mvc.dao;

import com.lanit.satonin18.mvc.entity.Action;
import com.lanit.satonin18.mvc.entity.Notification;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface ActionDAO extends CrudDAO<Action> {

   @Override
   void saveOrUpdate(Action entity);

   @Override
   void update(Action entity) ;


   @Override
   void delete(int id) ;

   @Override
   Action getById(int id) ;

   @Override
   List<Action> list() ;

   List<Action> listByIdNotification(Notification notification) ;

}
