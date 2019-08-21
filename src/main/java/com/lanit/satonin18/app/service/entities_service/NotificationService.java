package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.service.CrudService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService
        extends CrudService<Notification> {

    @Override
    void save(Notification notification) ;

    @Override
    void delete(Notification notification) ;

    @Override
    Notification findById(int id) ;

    @Override
    List<Notification> findAll() ;

    void deleteById(int id) ;

    PageImpl<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(Organization organization, List<Status> checkedStatusList, boolean showArchive, List<Status> listArchiveStatus, String orderFieldName, boolean desc, Pageable notificationPagination);
}
