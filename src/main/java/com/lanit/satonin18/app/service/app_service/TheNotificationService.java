package com.lanit.satonin18.app.service.app_service;

//import com.lanit.satonin18.app.PaginationForm;
import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import com.lanit.satonin18.app.objects.output.TheNotification4renderHtml;
import com.lanit.satonin18.app.objects.state4session.TheNotificationState;
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
        PaginationForm paginationForm = state.getPaginationForm();
        OrderByForm orderByForm = state.getOrderByForm();

        render.setPageImpl(
                actionService.filter_Notific_Order_Pagination(
                        currentNotification,
                        orderByForm.getOrderFieldName(), orderByForm.getDesc(),
                        PageRequest.of(
                                paginationForm.getPage(),
                                paginationForm.getMaxResult()
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
