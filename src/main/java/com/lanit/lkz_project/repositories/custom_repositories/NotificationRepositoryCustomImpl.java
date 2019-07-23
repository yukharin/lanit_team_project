package com.lanit.lkz_project.repositories.custom_repositories;


import com.lanit.lkz_project.entities.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;


@Repository
public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void setStateOfPage(PersonalAccountStateOfPage<Notification> page, Pageable pageable, User user) {
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
        if (!page.getActiveFiltersByStatus().isEmpty()) {
            Path<NotificationStatus> status = table.get("status");
            List<Predicate> statusPredicates = new ArrayList<>();
            Set<NotificationStatus> set = page.getActiveFiltersByStatus();
            for (NotificationStatus notificationStatus : set) {
                statusPredicates.add(builder.equal(status, notificationStatus));
            }
            statusPredicate = builder.or(statusPredicates.toArray(new Predicate[0]));
        }

        PersonalAccountStateOfPage.TimeFilter timeFilter = page.getTimeFilter();
        if (timeFilter != null && timeFilter != PersonalAccountStateOfPage.TimeFilter.Off) {
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
        page.setPageData(resultList);
        page.setTotal(count);
    }

}
