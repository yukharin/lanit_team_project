package com.lanit.satonin18.app.objects.output;

import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.enum_type.ActionType;
import com.lanit.satonin18.app.entity.enum_type.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class AddAction4renderHtml {
    private User user;
    private Notification currentNotification;
    private List<User> users;
    private final List<ActionType> listActionType = Arrays.asList(ActionType.values());
    private final List<Status> listStatus = Arrays.asList(Status.values());

}
