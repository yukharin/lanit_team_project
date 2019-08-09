package com.lanit.satonin18.app.dto.notification_app;

import com.lanit.satonin18.app.dto.CommonDefaultVar;
import lombok.Data;

@Data
public class AboutTheNotificationDto {
    private String orderFieldName = DefaultAboutTheNotificationVar.ORDER_FIELD_NAME;

    private int maxResult = CommonDefaultVar.MAX_RESULT;
    private int page = CommonDefaultVar.FIRST_PAGE;
    private boolean desc = CommonDefaultVar.DESC;

    private boolean flagNeedSetFirstPage = false;
//    private boolean flagNeedSetFirstPage = false;
}
