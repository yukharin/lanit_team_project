package com.lanit.satonin18.mvc.service;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.entity.Notification;
import com.lanit.satonin18.mvc.entity.NotificationStatus;
import com.lanit.satonin18.mvc.entity.Organization;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationService extends CrudService<Notification> {
//    List<Notification> filterDataAndNoArchiveNotifications(List<Notification> currentNotifications, List<NotificationStatus> sendedStatuses);
//
//    List<Notification> filterOrgAndNotificStatuses(Organization organization, List<NotificationStatus> listCheckedNotificStatus);
//
//    List<Notification> filterCurrentsAndNotificStatuses(/*String[] ids*/ List<Notification> currentNotifications, List<NotificationStatus> listNotificStatus);

    @Override
    void saveOrUpdate(Notification notification) ;

    @Override
    void update(Notification notification) ;

    @Override
    void delete(int id) ;

    @Override
    Notification getById(int id) ;

    @Override
    List<Notification> list() ;
//-----------------------------------------------
//    List<Notification> filterOrg(Organization organization);
//
//    Pagination<Notification> listByFilterOrg_Order_Pagination(Organization organization, String orderFieldName, boolean desc, Pagination<Notification> pagination);
//
//    List<Notification> filter_Org_NotificStatuses_Archive(Organization organization, List<NotificationStatus> checkedStatusList, List<NotificationStatus> listArchiveStatus);
//
//    Pagination<Notification> filter_Org_NotificStatuses_Archive_Order_Pagination(Organization organization, List<NotificationStatus> checkedStatusList, boolean showArchive, List<NotificationStatus> listArchiveStatus, String orderFieldName, boolean desc, Pagination<Notification> notificationPagination);
    Pagination<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(Organization organization, List<NotificationStatus> checkedStatusList, boolean showArchive, List<NotificationStatus> listArchiveStatus, String orderFieldName, boolean desc, Pagination<Notification> notificationPagination);
}
