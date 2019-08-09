package com.lanit.satonin18.app.service.app_service;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dto.notification_app.AboutTheNotificationDto;
import com.lanit.satonin18.app.dto.notification_app.AboutTheNotificationState;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.service.entities_service.ActionService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("notificationAppService")
public class AboutTheNotificationService {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    public void executeQuery(AboutTheNotificationState state, Notification currentNotification) {
        AboutTheNotificationDto dto = state.getDto();

        state.setPageImpl(
                actionService.filter_Notific_Order_Pagination(
                        currentNotification,
                        dto.getOrderFieldName(), dto.isDesc(),
                        PageRequest.of(
                                dto.getPage(),
                                dto.getMaxResult()
//                                , state.getPageImpl().getMaxNavigationPage()
                        )
                )
        );
        List<Action> list = state.getPageImpl().getContent();
        if( ! list.isEmpty()) {
            //todo need go in db
            state.setLatestAction(
                    Collections.max(list, (o1, o2) -> o1.getDate().compareTo(o2.getDate()))
            );
        }
        state.calcNavigationPages();
    }
}
