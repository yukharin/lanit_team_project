package com.lanit.satonin18.app.service.app_service;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dao.CrudDAO;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.dto.cabinet.CabinetModel;
import com.lanit.satonin18.app.dto.cabinet.CabinetState;
import com.lanit.satonin18.app.entity.no_db.NotificationStatus;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service("cabinetService")
public class CabinetService {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
//    @Autowired
//    private CrudDAO<ActionType> actionTypeService;
//    @Autowired
//    private NotificationStatusService statusService;
    @Autowired
    private OrganizationService organizationService;

    public void initCommonVar4CabinetState(CabinetState cabinetState) {
//        cabinetState.setStatuses4selectFilter(statusService.listByIds(IdStatus.getAllId()));
        cabinetState.setStatuses4selectFilter( Arrays.asList(NotificationStatus.values()) );
        cabinetState.setListArchiveStatus(statusService.listByIds(IdStatus.getArchiveStatusesId()));
        cabinetState.setCheckedMainListNotificStatuses(CabinetState.getStatuses4selectFilter());
    }
    public void executeQuery(CabinetState state, User currentUser) {
        CabinetModel model = state.getModel();
        if ( ! model.getIdFilterStatus().isEmpty()) {
            state.setCheckedMainListNotificStatuses(
                    statusService.listByIds(model.getIdFilterStatus())
            );
            state.setPagination(
                    notificationService._CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
                            currentUser.getOrganization(),
                            state.getCheckedMainListNotificStatuses(),
                            model.isShowArchive(), state.getListArchiveStatus(),
                            model.getOrderFieldName(), model.isDesc(),
                            new Pagination<Notification>(
                                    model.getPage(),
                                    model.getMaxResult(),
                                    state.getPagination().getMaxNavigationPage())
                    )
            );
            state.setShowListNotifications(
                    state.getPagination().getList()
            );
        }else{
            state.setCheckedMainListNotificStatuses(Collections.EMPTY_LIST);
            state.setPagination(
                    new Pagination.EmptyPagination<Notification>( state.getPagination() )
            );
            state.setShowListNotifications(Collections.EMPTY_LIST);
        }


    }

    public void editStatus(Integer idNotification, Integer idNewStatus) {
        Notification notification = notificationService.getById(idNotification);
        NotificationStatus status = statusService.getById(idNewStatus);

        notification.setNotificationStatus(status);

        notificationService.saveOrUpdate(notification);
    }
}
