package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_in_db.Status;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationDAO extends CrudDAO<Notification> {
   @Override
   void save(Notification entity);

   @Override
   void update(Notification entity) ;


   @Override
   void delete(Notification entity) ;

   @Override
   Notification getById(int id) ;

   @Override
   List<Notification> list() ;

   void deleteById(int id) ;

   Pagination<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
           Organization organization,
           List<Status> listNotificStatus,
           boolean showArchive, List<Status> listArchiveStatus,
           String orderFieldName, boolean desc,
           Pagination<Notification> pagination
   );
}
