package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.service.CrudService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActionService
        extends CrudService<Action> {

    @Override
    void save(Action action) ;

    @Override
    void delete(Action action) ;

    @Override
    Action findById(int id) ;

    @Override
    List<Action> findAll() ;

    void deleteById(int id) ;

    List<Action> listByIdNotification(Notification notification) ;

    PageImpl<Action> filter_Notific_Order_Pagination(Notification notification, String orderFieldName, boolean desc, Pageable actionPagination);

}
