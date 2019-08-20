package com.lanit.satonin18.app.service.app_service;

import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.objects.input.form.FilterForm;
import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import com.lanit.satonin18.app.objects.output.Cabinet4renderHtml;
import com.lanit.satonin18.app.objects.state4session.CabinetState;

import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("cabinetService")
public class CabinetService {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    public void executeQuery(Cabinet4renderHtml render, User currentUser) {
        CabinetState state = render.getState();
        FilterForm filterForm = state.getFilterForm();
        PaginationForm paginationForm = state.getPaginationForm();
        OrderByForm orderByForm = state.getOrderByForm();

        Pageable pageable = PageRequest.of(
                paginationForm.getPage(),
                paginationForm.getMaxResult()
//              , render.getPageImpl().getMaxNavigationPage()
        );

        if ( ! filterForm.getIdFilterStatus().isEmpty()) {
            render.setCheckedMainListNotificStatuses(
                    Status.getByIds(filterForm.getIdFilterStatus())
            );
            render.setPageImpl(
                    notificationService._CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
                            currentUser.getOrganization(),
                            render.getCheckedMainListNotificStatuses(),
                            filterForm.getShowArchive(), render.getListArchiveStatus(),
                            orderByForm.getOrderFieldName(), orderByForm.getDesc(),
                            pageable
                    )
            );
        }else{
            render.setCheckedMainListNotificStatuses(Collections.EMPTY_LIST);
            render.setPageImpl(
                    new PageImpl(Collections.EMPTY_LIST, pageable, 0)
            );
        }
//        render.calcNavigationPages();
    }

    public void editStatus(Integer idNotification, Integer idNewStatus) {
        Notification notification = notificationService.findById(idNotification);
        notification.setStatus(Status.getById(idNewStatus));
        
        notificationService.save(notification);//update

    }
}
