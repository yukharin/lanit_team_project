package com.lanit.lkz_project.repositories.custom_repositories;


import com.lanit.lkz_project.entities.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void setStateOfPage(PersonalAccountStateOfPage<Notification> page, Pageable pageable, User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Notification> notificationsQuery = builder.createQuery(Notification.class);
        CriteriaQuery<Long> totalNotifications = builder.createQuery(Long.class);
        Root<Notification> table = notificationsQuery.from(Notification.class);


        // predicates initialization
        Predicate orgPredicate = builder.conjunction();
        Predicate statusPredicate = builder.conjunction();
        Order byDate = builder.asc(table.get("dateReceived"));

        if (user.getRole() == Role.EMPLOYEE) {
            orgPredicate = builder.equal(table.get("organization"), user.getOrganization());
        }
        if (page.isFiltered()) {
            Path<NotificationStatus> status = table.get("status");
            List<Predicate> statusPredicates = new ArrayList<>();
            Set<NotificationStatus> set = page.getActiveFiltersByStatus();
            for (NotificationStatus notificationStatus : set) {
                statusPredicates.add(builder.equal(status, notificationStatus));
            }
            statusPredicate = builder.or(statusPredicates.toArray(new Predicate[0]));
        }

        Predicate resultingPredicate = builder.and(orgPredicate, statusPredicate);
        notificationsQuery
                .select(table)
                .distinct(true)
                .where(builder.and(resultingPredicate))
                .orderBy(byDate);
        TypedQuery<Notification> typedQuery = entityManager.createQuery(notificationsQuery);
        totalNotifications.select(builder.count(totalNotifications.from(Notification.class))).where(builder.and(statusPredicate, orgPredicate));
        long count = entityManager.createQuery(totalNotifications).getSingleResult();
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<Notification> resultList = typedQuery.getResultList();
        page.setPageData(resultList);
        page.setTotal(count);
    }

}
