package com.lanit.satonin18.app.dto.notification_app;

import com.lanit.satonin18.app.dto.Common_Default_var;
import lombok.Data;

@Data
public class NotificationAppModel {
    private String orderFieldName = Default_NotificationApp_var.ORDER_FIELD_NAME;

    private int maxResult = Common_Default_var.MAX_RESULT;
    private int page = Common_Default_var.PAGE;
    private boolean desc = Common_Default_var.DESC;

    private boolean selectedNewResultAndNeedSetFirstPage = false;
}
