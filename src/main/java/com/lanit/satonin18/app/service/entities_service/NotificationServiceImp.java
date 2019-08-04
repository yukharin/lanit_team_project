package com.lanit.satonin18.app.service.entities_service;

import java.util.List;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dao.NotificationDAO;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationServiceImp implements NotificationService {
    @Autowired
    private NotificationDAO notificationDAO;

//    @Override
//    public List<Notification> filterDataAndNoArchiveNotifications(List<Notification> currentNotifications, List<Status> sendedStatuses){
//        return notificationDAO.filterDataAndNoArchiveNotifications(currentNotifications, sendedStatuses);
//    }
//    @Override
//    public List<Notification> filterOrgAndNotificStatuses(Organization organization, List<Status> listNotificStatus){
//        return notificationDAO.filterOrgAndNotificStatuses(organization, listNotificStatus);
//    }
//
//    @Override
//    public List<Notification> filterCurrentsAndNotificStatuses(List<Notification> currentNotifications, /*String[] ids*/List<Status> listNotificStatus){
//        return notificationDAO.filterCurrentsAndNotificStatuses(currentNotifications, /*ids*/ listNotificStatus);
//    }

    @Override
    public void saveOrUpdate(Notification notification) {
        notificationDAO.saveOrUpdate(notification);
    }

    @Override
    public void update(Notification notification) {
        notificationDAO.update(notification);
    }

    @Override
    public void delete(int id) {
        notificationDAO.delete(id);
    }

    @Override
    public Notification getById(int id) {
        return notificationDAO.getById(id);
    }

    @Override
    public List<Notification> list() {
        return notificationDAO.list();
    }

//    @Override
//    public Pagination<Notification> listByFilterOrg_Order_Pagination(Organization organization, String orderFieldName, boolean desc, Pagination<Notification> pagination){
//        return notificationDAO.listByFilterOrg_Order_Pagination(organization, orderFieldName, desc, pagination);
//    }
    //---------------------------------------------------------

//    @Override
//    public List<Notification> filterOrg(Organization organization){
//        return notificationDAO.filterOrg(organization);
//    }
//
//    @Override
//    public List<Notification> filter_Org_NotificStatuses_Archive(Organization organization, List<Status> checkedStatusList, List<Status> listArchiveStatus){
//        return notificationDAO.filter_Org_NotificStatuses_Archive(organization, checkedStatusList, listArchiveStatus);
//    }

//    @Override
//    public Pagination<Notification>  filter_Org_NotificStatuses_Archive_Order_Pagination(
//            Organization organization,
//            List<Status> listNotificStatus,
//            boolean showArchive, List<Status> listArchiveStatus,
//            String orderFieldName, boolean desc,
//            Pagination<Notification> pagination
//    ){
//        return notificationDAO.filter_Org_NotificStatuses_Archive_Order_Pagination(
//                organization,
//                listNotificStatus,
//                showArchive, listArchiveStatus,
//                orderFieldName,  desc,
//                pagination
//        );
//    }

    @Override
    public Pagination<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
            Organization organization,
            List<Status> listNotificStatus,
            boolean showArchive, List<Status> listArchiveStatus,
            String orderFieldName, boolean desc,
            Pagination<Notification> pagination
    ){
        return notificationDAO._CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
                organization,
                listNotificStatus,
                showArchive, listArchiveStatus,
                orderFieldName,  desc,
                pagination
        );
    }
}