package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.*;
import com.lanit.lkz_project.service.entities_service.ActionService;
import com.lanit.lkz_project.service.entities_service.NotificationService;
import com.lanit.lkz_project.service.entities_service.OrganizationService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PersonalAccountService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ActionService actionService;

    public Page<Notification> getPage(@NotNull User user,
                                      String pageParam,
                                      String sizeParam) {
        int page = DEFAULT_PAGE_NUMBER;
        int size = DEFAULT_PAGE_SIZE;

        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam) - 1;
        }

        if (sizeParam != null && !sizeParam.isEmpty()) {
            size = Integer.parseInt(sizeParam);
        }
        List<Notification> notifications = defineUserNotifications(user);
        int startItem = page * size;
        List<Notification> resultList;
        if (notifications.size() < startItem) {
            resultList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + size, notifications.size());
            resultList = notifications.subList(startItem, toIndex);
        }
        return new PageImpl<>(resultList, PageRequest.of(page, size), notifications.size());
    }

    private List<Notification> defineUserNotifications(@NotNull User user) {
        Role role = user.getRole();
        switch (role) {
            case ЧИНОВНИК:
                return notificationService.notifications();
            case РАБОТНИК_ОРГАНИЗАЦИИ:
                return user.getOrganization().getNotifications();
            default:
                throw new IllegalStateException("Invalid user role");
        }
    }


    public List<ActionType> getAppropriateActions(Notification notification) {
        NotificationStatus status = notification.getStatus();
        return defineActions(status);
    }


    public void addNotification(@NotEmpty String notificationType,
                                @NotEmpty String dateResponse,
                                @NotEmpty String orgId) throws ParseException {
        Notification notification = new Notification();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfResponse = format.parse(dateResponse);
        Organization organization = organizationService.getOrganization(Long.valueOf(orgId));
        notification.setOrganization(organization);
        notification.setNotificationType(notificationType);
        notification.setDateResponse(dateOfResponse);
        notification.setDateReceived(new Date());
        notification.setStatus(NotificationStatus.НОВОЕ);
        notificationService.addNotification(notification);
    }

    public void addAction(User userImplementor, String actionTypeParam, String idNotification, String comment) {
        ActionType actionType = Enum.valueOf(ActionType.class, actionTypeParam);
        Notification notification = notificationService.getNotification(Long.valueOf(idNotification));
        Action action = new Action();
        action.setNotification(notification);
        action.setActionType(actionType);
        action.setContent(comment);
        action.setDate(new Date());
        action.setImplementor(userImplementor);
        NotificationStatus status = defineStatus(actionType);
        action.setStatusAfterAction(status);
        notification.setStatus(status);
        actionService.addAction(action);
        notificationService.updateNotification(notification);
    }

    private NotificationStatus defineStatus(ActionType actionType) {
        NotificationStatus status;
        switch (actionType) {
            case SEND_TO_PROCESSING:
                status = NotificationStatus.В_РАБОТЕ;
                break;
            case APPROVE:
                status = NotificationStatus.ОДОБРЕНО;
                break;
            case REJECT:
                status = NotificationStatus.ОТКЛОНЕНО;
                break;
            default:
                throw new IllegalStateException("Invalid action type");
        }
        return status;
    }

    private List<ActionType> defineActions(NotificationStatus status) {
        List<ActionType> types = new ArrayList<>();
        switch (status) {
            case НОВОЕ:
                types.add(ActionType.SEND_TO_PROCESSING);
                break;
            case В_РАБОТЕ:
                types.add(ActionType.APPROVE);
                types.add(ActionType.REJECT);
            case ОДОБРЕНО:
            case ОТКЛОНЕНО:
                break;
            default:
                throw new IllegalStateException("There is no such status");
        }
        return types;
    }


}
