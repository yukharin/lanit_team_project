package com.lanit.satonin18.app.repository;

//import com.lanit.satonin18.app.PaginationForm;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.enum_type.Status;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationRepositoryCustomized
{
    public PageImpl<Notification> getPaginationByfilter_Org_NotificStatuses_Archive_Order_Pagination(
            Organization organization,
            List<Status> listNotificStatus,
            boolean showArchive, List<Status> listArchiveStatus,
            String orderFieldName, boolean desc,
            Pageable pagination
            );
}
