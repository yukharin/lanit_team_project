package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_db.Status;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationService extends CrudService<Notification> {
//    List<Notification> filterDataAndNoArchiveNotifications(List<Notification> currentNotifications, List<Status> sendedStatuses);
//
//    List<Notification> filterOrgAndNotificStatuses(Organization organization, List<Status> listCheckedNotificStatus);
//
//    List<Notification> filterCurrentsAndNotificStatuses(/*String[] ids*/ List<Notification> currentNotifications, List<Status> listNotificStatus);

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
//    List<Notification> filter_Org_NotificStatuses_Archive(Organization organization, List<Status> checkedStatusList, List<Status> listArchiveStatus);
//
//    Pagination<Notification> filter_Org_NotificStatuses_Archive_Order_Pagination(Organization organization, List<Status> checkedStatusList, boolean showArchive, List<Status> listArchiveStatus, String orderFieldName, boolean desc, Pagination<Notification> notificationPagination);
    Pagination<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(Organization organization, List<Status> checkedStatusList, boolean showArchive, List<Status> listArchiveStatus, String orderFieldName, boolean desc, Pagination<Notification> notificationPagination);
}
