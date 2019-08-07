package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface ActionDAO extends CrudDAO<Action> {

   @Override
   void save(Action entity);

   @Override
   void update(Action entity);

   @Override
   void delete(Action entity);

   @Override
   Action getById(int id);

   @Override
   List<Action> list();

    void deleteById(int id);

    List<Action> listByIdNotification(Notification notification);

   Pagination<Action> filter_Notific_Order_Pagination(Notification notification, String orderFieldName, boolean desc, Pagination<Action> actionPagination);
}