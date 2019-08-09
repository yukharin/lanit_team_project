package com.lanit.satonin18.app.repository;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dto.Common_Default_var;
import com.lanit.satonin18.app.dto.notification_app.Default_AboutTheNotification_var;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class NotificationRepositoryImpl
        implements NotificationRepositoryCustomized
{
    @PersistenceContext
    EntityManager em;

    @Override
    public PageImpl<Notification> getPaginationByfilter_Org_NotificStatuses_Archive_Order_Pagination(
            Organization organization,
            List<Status> listNotificStatus,
            boolean showArchive, List<Status> listArchiveStatus,
            String orderFieldName, boolean desc,
            Pageable pagination
    ){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

//            SELECT * WHERE ...
        CriteriaQuery<Notification> criteriaQuery = criteriaBuilder.createQuery(Notification.class);
        Root<Notification> rootNotific = criteriaQuery.from(Notification.class);
//            SELECT COUNT(*)
        CriteriaQuery<Long> criteriaQuery_COUNT = criteriaBuilder.createQuery(Long.class);
        Root<Notification> rootNotific_COUNT = criteriaQuery_COUNT.from(Notification.class);

        List<Predicate> conditionsList = new ArrayList<Predicate>();
        List<Predicate> conditionsList_COUNT = new ArrayList<Predicate>();
        Order order = null;
//---------------------------------------------------
        if( ! organization.isGovernment()) {
            conditionsList.add(
                    criteriaBuilder.equal(
                            rootNotific.<Organization>get("organization"),
                            organization
                    )
            );
            conditionsList_COUNT.add(
                    criteriaBuilder.equal(
                            rootNotific_COUNT.<Organization>get("organization"),
                            organization
                    )
            );
        }
        Expression<Status> expStatus = rootNotific.get("status");
        Expression<Status> expStatus_COUNT = rootNotific_COUNT.get("status");
        if (listNotificStatus != null) {
            if(listNotificStatus.size() == 0)
//                return pageImpl.new EmptyPagination<Notification>(pageImpl);
                return new PageImpl(Collections.EMPTY_LIST, pagination, 0);
            conditionsList.add(
                    expStatus.in(listNotificStatus)
            );
            conditionsList_COUNT.add(
                    expStatus_COUNT.in(listNotificStatus)
            );
        }
        Expression<Date> expDateResponse = rootNotific.get("dateResponse");
        Expression<Date> expDateResponse_COUNT = rootNotific_COUNT.get("dateResponse");

        Date dateNow = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.DATE, -14);
        Date dateNow_minus14day = new Date(calendar.getTimeInMillis());

        if ( ! showArchive) {
            conditionsList.add(
                    criteriaBuilder.not(
                            criteriaBuilder.and(
                                    expStatus.in(listArchiveStatus),
                                    criteriaBuilder.lessThan/*OrEqualTo*/(expDateResponse, dateNow_minus14day)
                            )
                    )
            );
            conditionsList_COUNT.add(
                    criteriaBuilder.not(
                            criteriaBuilder.and(
                                    expStatus.in(listArchiveStatus),
                                    criteriaBuilder.lessThan/*OrEqualTo*/(expDateResponse_COUNT, dateNow_minus14day)
                            )
                    )
            );
        }
        //Еxample: orderFieldName = organization.name
        if(orderFieldName.contains(".")){
            String[] mas = orderFieldName.split("\\.");
            if(desc) {
                order = criteriaBuilder.desc(
                        rootNotific.get(mas[0]).get(mas[1])
                );
            }else{
                order = criteriaBuilder.asc(
                        rootNotific.get(mas[0]).get(mas[1])
                );
            }
        }else{
            if(desc) {
                order = criteriaBuilder.desc(
                        rootNotific.get(orderFieldName));
            }else{
                order = criteriaBuilder.asc(
                        rootNotific.get(orderFieldName));
            }
        }
//---------------------------------------------------
        criteriaQuery
                .select(rootNotific)
                .where(conditionsList.toArray(new Predicate[]{}))
                .orderBy(order)
        ;
        TypedQuery<Notification> q = em.createQuery(criteriaQuery);

        criteriaQuery_COUNT
                .select(
                        criteriaBuilder.count(rootNotific_COUNT)
                )
                .where(
                        conditionsList_COUNT.toArray(new Predicate[]{})
                )
        ;
        TypedQuery<Long> q_COUNT = em.createQuery(criteriaQuery_COUNT);
        long countAll = q_COUNT.getSingleResult();
//
//        return pageImpl.initQuery(q, countAll);
        int pageNumber = pagination.getPageNumber();
        int pageSize = pagination.getPageSize();

        q.setFirstResult((pageNumber)*pageSize);
        q.setMaxResults(pageSize);

        List<Notification> list = q.getResultList();

        return new PageImpl(list, pagination, countAll);
    }
}
