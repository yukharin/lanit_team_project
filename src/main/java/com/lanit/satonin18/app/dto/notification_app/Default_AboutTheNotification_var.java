package com.lanit.satonin18.app.dto.notification_app;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.dto.Common_Default_var;

public interface Default_AboutTheNotification_var {
    public static final String ORDER_FIELD_NAME = "date";
    public static final Pagination<Action> pagination = new Pagination<Action>(
            1,
            Common_Default_var.MAX_RESULT,
            Common_Default_var.NAVIGATION_PAGES
    );
}
