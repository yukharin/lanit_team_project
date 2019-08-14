package com.lanit.satonin18.app.service.app_service;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dto.FilterDto;
import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;
import com.lanit.satonin18.app.objects.cabinet.*;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.no_in_db.Status;
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
        FilterDto filterDto = state.getFilterDto();
        PaginationDto paginationDto = state.getPaginationDto();
        OrderByDto orderByDto = state.getOrderByDto();

        Pageable pageable = PageRequest.of(
                paginationDto.getPage(),
                paginationDto.getMaxResult()
//              , render.getPageImpl().getMaxNavigationPage()
        );

        if ( ! filterDto.getIdFilterStatus().isEmpty()) {
            render.setCheckedMainListNotificStatuses(
                    Status.getByIds(filterDto.getIdFilterStatus())
            );
            render.setPageImpl(
                    notificationService._CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
                            currentUser.getOrganization(),
                            render.getCheckedMainListNotificStatuses(),
                            filterDto.getShowArchive(), render.getListArchiveStatus(),
                            orderByDto.getOrderFieldName(), orderByDto.getDesc(),
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
