package com.lanit.satonin18.app.service.app_service;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dto.cabinet.CabinetDtoOnInput;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.dto.cabinet.CabinetStateOnOutput;
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

    public void executeQuery(CabinetStateOnOutput state, User currentUser) {
        CabinetDtoOnInput model = state.getDto();
        Pageable pageable = PageRequest.of(
                model.getPage(),
                model.getMaxResult()
//              , state.getPageImpl().getMaxNavigationPage()
        );

        if ( ! model.getIdFilterStatus().isEmpty()) {
            state.setCheckedMainListNotificStatuses(
                    Status.getByIds(model.getIdFilterStatus())
            );
            state.setPageImpl(
                    notificationService._CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
                            currentUser.getOrganization(),
                            state.getCheckedMainListNotificStatuses(),
                            model.isShowArchive(), state.getListArchiveStatus(),
                            model.getOrderFieldName(), model.isDesc(),
                            pageable
                    )
            );
        }else{
            state.setCheckedMainListNotificStatuses(Collections.EMPTY_LIST);
            state.setPageImpl(
                    new PageImpl(Collections.EMPTY_LIST, pageable, 0)
            );
        }
        state.calcNavigationPages();
    }

    public void editStatus(Integer idNotification, Integer idNewStatus) {
        Notification notification = notificationService.findById(idNotification);
        notification.setStatus(Status.getById(idNewStatus));

        notificationService.save(notification);//update
    }
}
