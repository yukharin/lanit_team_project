package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface ActionService extends CrudService<Action> {

    @Override
    void save(Action action) ;

    @Override
    void update(Action action) ;

    @Override
    void delete(Action action) ;

    @Override
    Action getById(int id) ;

    @Override
    List<Action> list() ;

    void deleteById(int id) ;

    List<Action> listByIdNotification(Notification notification) ;

    Pagination<Action> filter_Notific_Order_Pagination(Notification notification, String orderFieldName, boolean desc, Pagination<Action> actionPagination);
}
