package com.lanit.satonin18.mvc.dao;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.entity.Notification;
import com.lanit.satonin18.mvc.entity.NotificationStatus;
import com.lanit.satonin18.mvc.entity.Organization;

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

   Pagination<Notification> listByFilterOrg_Order_Pagination(Organization organization, String orderFieldName, boolean desc, Pagination<Notification> pagination);

//----------------------------------------------
   List<Notification> filterOrg(Organization organization);

    List<Notification> filter_Org_NotificStatuses_Archive(Organization organization, List<NotificationStatus> listNotificStatus, List<NotificationStatus> listArchiveStatus);

   Pagination<Notification>  filter_Org_NotificStatuses_Archive_Order_Pagination(
           Organization organization,
           List<NotificationStatus> listNotificStatus,
           boolean showArchive, List<NotificationStatus> listArchiveStatus,
           String orderFieldName, boolean desc,
           Pagination<Notification> pagination
   );
}