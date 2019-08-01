package com.lanit.satonin18.app.service.app_service;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dao.CrudDAO;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.dto.notification_app.NotificationAppModel;
import com.lanit.satonin18.app.dto.notification_app.NotificationAppState;
import com.lanit.satonin18.app.service.entities_service.ActionService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;

@Service("notificationAppService")
public class NotificationAppService {

    @Autowired
    private com.lanit.satonin18.app.service.entities_service.NotificationService notificationService;
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

//    public void initCommonVar4NotificationAppState(NotificationAppState notificationState, Notification currentNotification) {
//    }

    public void executeQuery(NotificationAppState state, Notification currentNotification) {
        NotificationAppModel model = state.getModel();

        state.setPagination(
                actionService.filter_Notific_Order_Pagination(
                        currentNotification,
                        model.getOrderFieldName(), model.isDesc(),
                        new Pagination<Action>(
                                model.getPage(),
                                model.getMaxResult(),
                                state.getPagination().getMaxNavigationPage())
                )
        );
        state.setShowListActions(
                state.getPagination().getList()
        );

        //todo need go in db
        if( ! state.getShowListActions().isEmpty()) {
            state.setLatestAction(
                    Collections.max(state.getShowListActions(), new Comparator<Action>() {
                        @Override
                        public int compare(Action o1, Action o2) {
                            return o1.getDate().compareTo(o2.getDate());
                        }
                    })
            );
        }
    }
}
