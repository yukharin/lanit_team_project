package com.lanit.lkz_project.repositories.application_repositories;


import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.NotificationStatus;
import com.lanit.lkz_project.entities.PersonalAccountStateOfPage;
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
    public void getAccountNotifications(PersonalAccountStateOfPage page, Pageable pageable) {
        if (page.getFiltration() == true) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Notification> criteriaQuery = criteriaBuilder.createQuery(Notification.class);
            Root<Notification> root = criteriaQuery.from(Notification.class);
            Path<NotificationStatus> status = root.get("status");
            List<Predicate> filterStatusPredicates = new ArrayList<>();
            Set<NotificationStatus> set = page.getActiveFiltersByStatus();
            for (NotificationStatus notificationStatus : set) {
                filterStatusPredicates.add(criteriaBuilder.equal(root.get("status"), notificationStatus));
            }
            criteriaQuery.select(root).distinct(true).where(criteriaBuilder.or(filterStatusPredicates.toArray(new Predicate[filterStatusPredicates.size()])));
            TypedQuery<Notification> typedQuery = entityManager.createQuery(criteriaQuery);
            int totalNotifications = typedQuery.getResultList().size();
            typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            typedQuery.setMaxResults(pageable.getPageSize());
            List<Notification> resultList = typedQuery.getResultList();
            page.setPageData(resultList);
            page.setTotal(totalNotifications);
        } else {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Notification> criteriaQuery = criteriaBuilder.createQuery(Notification.class);
            Root<Notification> root = criteriaQuery.from(Notification.class);
            criteriaQuery.select(root).distinct(true);
            TypedQuery<Notification> typedQuery = entityManager.createQuery(criteriaQuery);
            int totalNotifications = typedQuery.getResultList().size();
            typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            typedQuery.setMaxResults(pageable.getPageSize());
            List<Notification> resultList = typedQuery.getResultList();
            page.setPageData(resultList);
            page.setTotal(totalNotifications);
        }
    }

}
