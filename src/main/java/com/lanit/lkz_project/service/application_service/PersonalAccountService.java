package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PersonalAccountService {


    public Page<Notification> getPaginatedNotifications(Pageable pageable, User user) {
        List<Notification> notifications = user.getOrganization().getNotifications();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Notification> resultList;
        if (notifications.size() < startItem) {
            resultList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, notifications.size());
            resultList = notifications.subList(startItem, toIndex);
        }
        return new PageImpl<>(resultList, PageRequest.of(currentPage, pageSize), notifications.size());
    }

}
