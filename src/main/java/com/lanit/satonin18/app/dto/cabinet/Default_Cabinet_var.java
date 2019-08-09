package com.lanit.satonin18.app.dto.cabinet;

import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.dto.Common_Default_var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Default_Cabinet_var {
    public static final String ORDER_FIELD_NAME = "dateResponse";
    public static final boolean SHOW_ARCHIVE = false;
    public static final List<FastFilter> list4FastFilter = Arrays.asList(FastFilter.values());
}
