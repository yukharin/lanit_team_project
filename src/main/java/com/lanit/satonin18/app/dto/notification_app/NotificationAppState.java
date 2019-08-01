package com.lanit.satonin18.app.dto.notification_app;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import lombok.*;

import java.util.List;


@Data
//@NoArgsConstructor
@AllArgsConstructor
public class NotificationAppState {

    private Pagination<Action> pagination = Default_NotificationApp_var.pagination;
    private List<Action> showListActions;
    private Action latestAction;

    private NotificationAppModel model = new NotificationAppModel();

    public NotificationAppState(NotificationAppModel model) {
        this.model = model;
    }
}
