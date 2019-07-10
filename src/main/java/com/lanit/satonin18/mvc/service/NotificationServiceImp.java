package com.lanit.satonin18.mvc.service;

import java.util.List;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.dao.NotificationDAO;
import com.lanit.satonin18.mvc.entity.Notification;
import com.lanit.satonin18.mvc.entity.NotificationStatus;
import com.lanit.satonin18.mvc.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("notificationService")
@Transactional
public class NotificationServiceImp implements NotificationService  {
    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public List<Notification> filterDataAndNoArchiveNotifications(List<Notification> currentNotifications, List<NotificationStatus> sendedStatuses){
        return notificationDAO.filterDataAndNoArchiveNotifications(currentNotifications, sendedStatuses);
    }
    @Override
    public List<Notification> filterOrgAndNotificStatuses(Organization organization, List<NotificationStatus> listNotificStatus){
        return notificationDAO.filterOrgAndNotificStatuses(organization, listNotificStatus);
    }

    @Override
    public List<Notification> filterCurrentsAndNotificStatuses(List<Notification> currentNotifications, /*String[] ids*/List<NotificationStatus> listNotificStatus){
        return notificationDAO.filterCurrentsAndNotificStatuses(currentNotifications, /*ids*/ listNotificStatus);
    }

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

    @Override
    public Pagination<Notification> listByFilterOrg_Order_Pagination(Organization organization, String orderFieldName, boolean desc, Pagination<Notification> pagination){
        return notificationDAO.listByFilterOrg_Order_Pagination(organization, orderFieldName, desc, pagination);
    }
    //---------------------------------------------------------

    @Override
    public List<Notification> filterOrg(Organization organization){
        return notificationDAO.filterOrg(organization);
    }
}