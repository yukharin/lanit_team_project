package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.*;
import com.lanit.lkz_project.repositories.entitity_repositories.NotificationRepository;
import com.lanit.lkz_project.service.entities_service.ActionService;
import com.lanit.lkz_project.service.entities_service.NotificationService;
import com.lanit.lkz_project.service.entities_service.OrganizationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.Random;

@Service
public class PersonalAccountService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int BOUND_TO_GENERATE_NUMBER = 10;


    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public PersonalAccountPage<Notification> getPage(User user,
                                                     String pageParam,
                                                     String sizeParam,
                                                     String filterNew,
                                                     String filterInProcessing,
                                                     String filterApproved,
                                                     String filterRejected,
                                                     String timeFilter) {
        PersonalAccountPage<Notification> pageState = createPageState(
                filterNew, filterInProcessing, filterApproved, filterRejected, timeFilter);

        Pageable pageable = createPageRequest(pageParam, sizeParam);
        notificationRepository.setStateOfPage(pageState, pageable, user);
        return pageState;
    }

    private PersonalAccountPage<Notification> createPageState(String filterNew,
                                                              String filterInProcessing,
                                                              String filterApproved,
                                                              String filterRejected, String timeFilter) {
        PersonalAccountPage<Notification> accountPage = new PersonalAccountPage<>();
        if (filterApproved != null && filterApproved.equals("true")) {
            accountPage.addFilters(NotificationStatus.APPROVED);
        }
        if (filterInProcessing != null && filterInProcessing.equals("true")) {
            accountPage.addFilters(NotificationStatus.IN_PROCESSING);
        }
        if (filterNew != null && filterNew.equals("true")) {
            accountPage.addFilters(NotificationStatus.NEW);
        }
        if (filterRejected != null && filterRejected.equals("true")) {
            accountPage.addFilters(NotificationStatus.REJECTED);
        }
        if (timeFilter != null) {
            switch (timeFilter) {
                case "3_days":
                    accountPage.setTimeFilter(PersonalAccountPage.TimeFilter.First);
                    break;
                case "10_days":
                    accountPage.setTimeFilter(PersonalAccountPage.TimeFilter.Second);
                    break;
                case "30_days":
                    accountPage.setTimeFilter(PersonalAccountPage.TimeFilter.Third);
                    break;
                default:
                    accountPage.setTimeFilter(PersonalAccountPage.TimeFilter.Off);
            }
        }
        return accountPage;
    }

    private Pageable createPageRequest(String pageParam, String sizeParam) {
        int pageNumber = DEFAULT_PAGE_NUMBER;
        int pageSize = DEFAULT_PAGE_SIZE;
        if (pageParam != null && !pageParam.isEmpty()) {
            pageNumber = Integer.parseInt(pageParam) - 1;
        }

        if (sizeParam != null && !sizeParam.isEmpty()) {
            pageSize = Integer.parseInt(sizeParam);
        }
        return PageRequest.of(pageNumber, pageSize);
    }


    public EnumSet<ActionType> getAppropriateActions(@NonNull Notification notification) {
        NotificationStatus status = notification.getStatus();
        return defineActions(status);
    }


    public void addNotification(@NonNull User user,
                                @NonNull String notificationType,
                                @NonNull String dateResponse,
                                @NonNull String orgId) throws ParseException {
        Notification notification = new Notification();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfResponse = format.parse(dateResponse);
        Organization organization = organizationService.getOrganization(Long.valueOf(orgId));
        notification.setOrganization(organization);
        notification.setNotificationType(notificationType);
        notification.setDateResponse(dateOfResponse);
        notification.setDateReceived(new Date());
        notification.setStatus(NotificationStatus.NEW);
        notification.setLetterNumber(generateLetterNumber());
        notification.setUserNotificationAuthor(user);
        notificationService.addNotification(notification);
    }

    public void addAction(@NonNull User userImplementor,
                          @NonNull String actionTypeParam,
                          @NonNull String idNotification,
                          @NonNull String comment) {
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
