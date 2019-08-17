package com.lanit.satonin18.app.service.entities_service;

import java.util.List;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.repository.NotificationRepository;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @PersistenceContext
    EntityManager em;

    @Override
//    @Transactional
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

    @Override
    public Notification findById(int id) {
        return notificationRepository.findById(id).get();
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public PageImpl<Notification> _CRITERIA_filter_Org_NotificStatuses_Archive_Order_Pagination(
            Organization organization,
            List<Status> listNotificStatus,
            boolean showArchive, List<Status> listArchiveStatus,
            String orderFieldName, boolean desc,
            Pageable pagination
    ){
        return notificationRepository.getPaginationByfilter_Org_NotificStatuses_Archive_Order_Pagination(
                organization,
                listNotificStatus,
                showArchive, listArchiveStatus,
                orderFieldName,  desc,
                pagination
        );
    }
}