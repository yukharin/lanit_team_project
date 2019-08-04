package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.dto.PersonalAccountPage;
import com.lanit.lkz_project.entities.jpa_entities.*;
import com.lanit.lkz_project.repositories.entitity_repositories.NotificationRepository;
import com.lanit.lkz_project.service.jpa_entities_service.ActionService;
import com.lanit.lkz_project.service.jpa_entities_service.NotificationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.EnumSet;
import java.util.Random;

@Service
public class PersonalAccountService {


    private static final int BOUND_TO_GENERATE_NUMBER = 10;


    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public void setAccountPageState(PersonalAccountPage<Notification> page,
                                    User user) {
        PageImpl<Notification> accountPage = notificationRepository.getAccountPage(page, user);
        page.setPage(accountPage);
    }

    public EnumSet<ActionType> getAppropriateActions(@NonNull Notification notification) {
        NotificationStatus status = notification.getStatus();
        return defineActions(status);
    }


    public void addNotification(@NonNull Notification notification,
                                @NonNull User user) {
        notification.setDateReceived(new Date());
        notification.setStatus(NotificationStatus.NEW);
        notification.setLetterNumber(generateLetterNumber());
        notification.setUserNotificationAuthor(user);
        notificationService.addNotification(notification);
    }

    public void addAction(@NonNull User userImplementor,
                          @NonNull Action action) {
        Notification notification = notificationService.getNotification(action.getNotification().getId());
        action.setNotification(notification);
        action.setDate(new Date());
        action.setImplementor(userImplementor);
        NotificationStatus status = defineStatus(action.getActionType());
        action.setStatusAfterAction(status);
        notification.setStatus(status);
        notificationService.addNotification(notification);
        actionService.addAction(action);
    }

    private NotificationStatus defineStatus(@NonNull ActionType actionType) {
        NotificationStatus status;
        switch (actionType) {
            case SEND_TO_PROCESSING:
                status = NotificationStatus.IN_PROCESSING;
                break;
            case APPROVE:
                status = NotificationStatus.APPROVED;
                break;
            case REJECT:
                status = NotificationStatus.REJECTED;
                break;
            default:
                throw new IllegalStateException("Invalid action type");
        }
        return status;
    }

    private EnumSet<ActionType> defineActions(@NonNull NotificationStatus status) {
        EnumSet<ActionType> types;
        switch (status) {
            case NEW:
                types = EnumSet.of(ActionType.SEND_TO_PROCESSING);
                break;
            case IN_PROCESSING:
                types = EnumSet.of(ActionType.APPROVE, ActionType.REJECT);
                break;
            case APPROVED:
            case REJECTED:
                types = EnumSet.noneOf(ActionType.class);
                break;
            default:
                throw new IllegalStateException("There is no such status");
        }
        return types;
    }

    private String generateLetterNumber() {
        // Не очень выглядит, но сделал так, чтоб была хоть какая то генерация.
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        stringBuilder
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER))
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER))
                .append("-")
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER))
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER))
                .append("-")
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER))
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER))
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER))
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER))
                .append("/")
                .append(random.nextInt(BOUND_TO_GENERATE_NUMBER));
        return stringBuilder.toString();
    }


}
