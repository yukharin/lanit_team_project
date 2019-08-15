package com.lanit.satonin18.app.objects.the_notification;

import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;

public class TheNotificationState {
    PaginationDto paginationDto;
    OrderByDto orderByDto;

    public PaginationDto getPaginationDto() {
        return paginationDto;
    }

    public void setPaginationDto(PaginationDto paginationDto) {
        this.paginationDto = paginationDto;
    }

    public OrderByDto getOrderByDto() {
        return orderByDto;
    }

    public void setOrderByDto(OrderByDto orderByDto) {
        this.orderByDto = orderByDto;
    }
}
