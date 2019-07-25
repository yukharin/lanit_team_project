package com.lanit.lkz_project.repositories.custom_repositories;


import com.lanit.lkz_project.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Repository
public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<Notification> getAccountPage(final PersonalAccountPage<Notification> page, final Pageable pageable, final User user) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Notification> notificationsQuery = builder.createQuery(Notification.class);
        final CriteriaQuery<Long> totalNotifications = builder.createQuery(Long.class);
        final Root<Notification> table = notificationsQuery.from(Notification.class);

        // predicates initialization
        Predicate orgPredicate = builder.conjunction();
        Predicate statusPredicate = builder.conjunction();
        Predicate datePredicate = builder.conjunction();
        Order byDate = builder.asc(table.get("dateReceived"));

        if (user.getRole() == Role.EMPLOYEE) {
            orgPredicate = builder.equal(table.get("organization"), user.getOrganization());
        }

        List<Predicate> statusPredicates = generateFilterPredicates(builder, table, page);
        if (!statusPredicates.isEmpty()) {
            statusPredicate = builder.or(statusPredicates.toArray(new Predicate[0]));
        }

        PersonalAccountPage.TimeFilter timeFilter = page.getTimeFilter();
        if (timeFilter != null && timeFilter != PersonalAccountPage.TimeFilter.NO_FILTER) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, timeFilter.days());
            datePredicate = builder.lessThan(table.get("dateResponse"), calendar.getTime());
        }

        Predicate resultingPredicate = builder.and(orgPredicate, statusPredicate, datePredicate);
        notificationsQuery.select(table).distinct(true)
                .where(builder.and(resultingPredicate))
                .orderBy(byDate);
        totalNotifications.select(builder.count(totalNotifications
                .from(Notification.class)))
                .where(resultingPredicate);


        TypedQuery<Notification> notificationsTQ = entityManager.createQuery(notificationsQuery);
        notificationsTQ.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        notificationsTQ.setMaxResults(pageable.getPageSize());
        List<Notification> resultList = notificationsTQ.getResultList();

        final long count = entityManager.createQuery(totalNotifications).getSingleResult();
        return new PageImpl<>(resultList, pageable, count);
    }

    private List<Predicate> generateFilterPredicates(final CriteriaBuilder builder,
                                                     final Root<Notification> table, final PersonalAccountPage<Notification> page) {
        Path<NotificationStatus> status = table.get("status");
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