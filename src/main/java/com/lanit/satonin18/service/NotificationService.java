package com.lanit.satonin18.service;

import com.lanit.satonin18.model.Notification;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationService extends CrudService<Notification> {

//    public List<Notification> getNotificationsByIdOrg(int theOrgId);

    @Override
    public void saveOrUpdate(Notification organization) ;

    @Override
    public void update(Notification organization) ;

    @Override
    public void delete(int id) ;

    @Override
    public Notification getById(int id) ;

    @Override
    public List<Notification> list() ;
}
