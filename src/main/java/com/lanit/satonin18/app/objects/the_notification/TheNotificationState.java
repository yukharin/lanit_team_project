package com.lanit.satonin18.app.objects.the_notification;

import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;
import lombok.Data;

@Data
public class TheNotificationState {
    PaginationDto paginationDto;
    OrderByDto orderByDto;
}
