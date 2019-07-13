package com.lanit.satonin18.mvc.dao;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.entity.Action;
import com.lanit.satonin18.mvc.entity.Notification;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface ActionDAO extends CrudDAO<Action> {

   @Override
   void saveOrUpdate(Action entity);

   @Override
   void update(Action entity);


   @Override
   void delete(int id);

   @Override
   Action getById(int id);

   @Override
   List<Action> list();

   void save(Action action) ;

   List<Action> listByIdNotification(Notification notification);

   Pagination<Action> filter_Notific_Order_Pagination(Notification notification, String orderFieldName, boolean desc, Pagination<Action> actionPagination);
}