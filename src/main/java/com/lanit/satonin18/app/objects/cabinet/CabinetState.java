package com.lanit.satonin18.app.objects.cabinet;

import com.lanit.satonin18.app.dto.FilterDto;
import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;

public class CabinetState {
    FilterDto filterDto;
    PaginationDto paginationDto;
    OrderByDto orderByDto;

    public FilterDto getFilterDto() {
        return filterDto;
    }

    public void setFilterDto(FilterDto filterDto) {
        this.filterDto = filterDto;
    }

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
