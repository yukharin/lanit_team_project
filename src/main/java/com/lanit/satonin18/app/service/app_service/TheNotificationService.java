package com.lanit.satonin18.app.service.app_service;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;
import com.lanit.satonin18.app.objects.the_notification.TheNotificationState;
import com.lanit.satonin18.app.objects.the_notification.TheNotification4renderHtml;
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
public class TheNotificationService {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    public void executeQuery(TheNotification4renderHtml render, Notification currentNotification) {
        TheNotificationState state = render.getState();
        PaginationDto paginationDto = state.getPaginationDto();
        OrderByDto orderByDto = state.getOrderByDto();

        render.setPageImpl(
                actionService.filter_Notific_Order_Pagination(
                        currentNotification,
                        orderByDto.getOrderFieldName(), orderByDto.getDesc(),
                        PageRequest.of(
                                paginationDto.getPage(),
                                paginationDto.getMaxResult()
//                                , render.getPageImpl().getMaxNavigationPage()
                        )
                )
        );
        List<Action> list = render.getPageImpl().getContent();
        if( ! list.isEmpty()) {
            //todo need go in db
            render.setLatestAction(
                    Collections.max(list, (o1, o2) -> o1.getDate().compareTo(o2.getDate()))
            );
        }
//        render.calcNavigationPages();
    }
}
