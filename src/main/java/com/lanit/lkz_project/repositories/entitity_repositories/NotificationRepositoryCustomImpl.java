package com.lanit.lkz_project.repositories.entitity_repositories;


import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.enums.AuthorityValue;
import com.lanit.lkz_project.entities.enums.NotificationStatus;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.Notification_;
import com.lanit.lkz_project.entities.jpa_entities.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Repository
public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public PageImpl<Notification> getAccountPage(final PersonalAccountPageDto<Notification> page, final User user) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Notification> notificationsQuery = builder.createQuery(Notification.class);
        final CriteriaQuery<Long> totalNotifications = builder.createQuery(Long.class);
        final Root<Notification> table = notificationsQuery.from(Notification.class);

        // sorting set up
        final String sortParameter = page.getSortParameter().getValue();
        final Sort.Direction sortOrder;
        if (page.isReversedOrder()) {
            sortOrder = Sort.Direction.DESC;
        } else {
            sortOrder = Sort.Direction.ASC;
        }

        // page number and page size initialization
        final int pageNumber = page.getNumber() - 1;
        final int pageSize = page.getSize();

        // page request initialization
        final Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortOrder, sortParameter));
        // predicates initialization
        Predicate orgPredicate = builder.conjunction();
        Predicate statusPredicate = builder.conjunction();
        Predicate datePredicate = builder.conjunction();

        // defining sorting order
        Order sortingOrder;
        if (sortOrder == Sort.Direction.ASC) {
            sortingOrder = builder.asc(table.get(sortParameter));
        } else {
            sortingOrder = builder.desc(table.get(sortParameter));
        }


        // defining amount of notification depending on role
        if (user.hasAuthority(AuthorityValue.EMPLOYEE)) {
            orgPredicate = builder.equal(table.get(Notification_.organization), user.getOrganization());
        }

        List<Predicate> statusPredicates = generateFilterPredicates(builder, table, page);
        if (!statusPredicates.isEmpty()) {
            statusPredicate = builder.or(statusPredicates.toArray(new Predicate[0]));
        }

        PersonalAccountPageDto.TimeFilter timeFilter = page.getTimeFilter();
        if (timeFilter != null && timeFilter != PersonalAccountPageDto.TimeFilter.NO_FILTER) {
            datePredicate = builder.lessThan(table.get(Notification_.dateResponse),
                    LocalDate.now().plus(Period.ofDays(timeFilter.days())));
        }

        Predicate resultingPredicate = builder.and(orgPredicate, statusPredicate, datePredicate);
        // 2 запроса:
        // Первый, чтобы получить список уведомлений
        notificationsQuery.select(table).distinct(true)
                .where(builder.and(resultingPredicate))
                .orderBy(sortingOrder);
        // Второй, чтобы узнать точное количество уведомлений (данный параметр нужен для пагинации).
        totalNotifications.select(builder.count(totalNotifications
                .from(Notification.class)))
                .where(resultingPredicate);

        TypedQuery<Notification> notificationsTQ = entityManager.createQuery(notificationsQuery);
        notificationsTQ.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
        notificationsTQ.setMaxResults(pageRequest.getPageSize());
        List<Notification> resultList = notificationsTQ.getResultList();
        final long count = entityManager.createQuery(totalNotifications).getSingleResult();
        return new PageImpl<>(resultList, pageRequest, count);
    }

    private List<Predicate> generateFilterPredicates(final CriteriaBuilder builder,
                                                     final Root<Notification> table, final PersonalAccountPageDto<Notification> page) {
        Path<NotificationStatus> status = table.get(Notification_.status);
        List<Predicate> statusPredicates = new ArrayList<>();
        if (page.isNewFilter()) {
            statusPredicates.add(builder.equal(status, NotificationStatus.NEW));
        }

        if (page.isInProcessingFilter()) {
            statusPredicates.add(builder.equal(status, NotificationStatus.IN_PROCESSING));
        }

        if (page.isApprovedFilter()) {
            statusPredicates.add(builder.equal(status, NotificationStatus.APPROVED));
        }

        if (page.isRejectedFilter()) {
            statusPredicates.add(builder.equal(status, NotificationStatus.REJECTED));
        }
        return statusPredicates;
    }

}
