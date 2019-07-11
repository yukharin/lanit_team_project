package com.lanit.satonin18.mvc.service;

import com.lanit.satonin18.mvc.entity.Action;
import com.lanit.satonin18.mvc.entity.Notification;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface ActionService extends CrudService<Action> {

    @Override
    void saveOrUpdate(Action action) ;

    @Override
    void update(Action action) ;

    @Override
    void delete(int id) ;

    @Override
    Action getById(int id) ;

    @Override
    List<Action> list() ;

    List<Action> listByIdNotification(Notification notification) ;
}
