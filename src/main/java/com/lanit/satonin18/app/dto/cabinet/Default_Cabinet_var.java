package com.lanit.satonin18.app.dto.cabinet;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.dto.Common_Default_var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Default_Cabinet_var {
    public static final String ORDER_FIELD_NAME = "dateResponse";
    public static final boolean SHOW_ARCHIVE = false;
    public static final List<FastFilter> list4FastFilter = new ArrayList<>(Arrays.asList(FastFilter.values()));
    public static final Pagination<Notification> pagination = new Pagination<Notification>(
            1,
            Common_Default_var.MAX_RESULT,
            Common_Default_var.NAVIGATION_PAGES
    );
}
