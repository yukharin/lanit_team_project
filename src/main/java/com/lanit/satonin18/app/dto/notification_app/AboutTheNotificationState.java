package com.lanit.satonin18.app.dto.notification_app;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import lombok.*;

import java.util.List;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class AboutTheNotificationState {

    private Pagination<Action> pagination = Default_AboutTheNotification_var.pagination;
    private List<Action> showListActions;
    private Action latestAction;

    private AboutTheNotificationDto dto;

    public AboutTheNotificationState(AboutTheNotificationDto dto) {
        this.dto = dto;
    }
}
