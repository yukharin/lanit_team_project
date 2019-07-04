package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.Notification;
import com.lanit.satonin18.model.NotificationStatus;
import com.lanit.satonin18.model.Organization;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationDAO extends CrudDAO<Notification> {
   List<Notification> filterDataAndNoArchiveNotifications(List<Notification> currentNotifications, List<NotificationStatus> sendedStatuses);

   List<Notification> filterOrgAndNotificStatuses(Organization organization, List<NotificationStatus> listNotificStatus);

   List<Notification> filterCurrentsAndNotificStatuses(List<Notification> currentNotifications, /*String[] ids*/ List<NotificationStatus> listNotificStatus);

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

//----------------------------------------------
   List<Notification> filterOrg(Organization organization);
}
