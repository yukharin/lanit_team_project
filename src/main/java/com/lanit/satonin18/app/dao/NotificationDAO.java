package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.NotificationStatus;
import com.lanit.satonin18.app.entity.Organization;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationDAO extends CrudDAO<Notification> {
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

   Pagination<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
           Organization organization,
           List<NotificationStatus> listNotificStatus,
           boolean showArchive, List<NotificationStatus> listArchiveStatus,
           String orderFieldName, boolean desc,
           Pagination<Notification> pagination
   );
}
