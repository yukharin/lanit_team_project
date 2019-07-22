package com.lanit.satonin18.mvc.service;

import com.lanit.satonin18.mvc.entity.NotificationStatus;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface NotificationStatusService extends CrudService<NotificationStatus> {

    List<NotificationStatus> filterIds(String[] ids) ;

    @Override
    void saveOrUpdate(NotificationStatus organization) ;

    @Override
    void update(NotificationStatus organization) ;

    @Override
    void delete(int id) ;

    @Override
    NotificationStatus getById(int id) ;

    @Override
    List<NotificationStatus> list() ;

    List<NotificationStatus> listByIds(List<Integer> ids);
}
