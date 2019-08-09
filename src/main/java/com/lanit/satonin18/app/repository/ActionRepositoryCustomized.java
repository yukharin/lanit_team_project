package com.lanit.satonin18.app.repository;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepositoryCustomized
{
   public PageImpl<Action> filter_Notific_Order_Pagination(
           Notification notification,
           String orderFieldName, boolean desc,
           Pageable actionPagination);
}