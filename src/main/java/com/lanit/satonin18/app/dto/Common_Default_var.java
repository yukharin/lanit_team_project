package com.lanit.satonin18.app.dto;

import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Common_Default_var {
    public static final boolean DESC = true;
    public static final int PAGE = 0;
    public static final int MAX_RESULT = 10;
    public static final int MAX_NAVIGATION_PAGES = 10;
    public static final List<Integer> selectShowListMaxResult =  new ArrayList<>(Arrays.asList( new Integer[]{
            1,2,5,10,25,50,100
    }));

    public static final Pageable PAGEABLE = PageRequest.of(
            Common_Default_var.PAGE,
            Common_Default_var.MAX_RESULT
//            ,
//            MAX_NAVIGATION_PAGES
    );

    public static final PageImpl<Action> EMPTY_PAGE_IMPL_ACTION = new PageImpl<Action>(
            Collections.EMPTY_LIST,
            PAGEABLE,
            0
    );

    public static final PageImpl<Notification> EMPTY_PAGE_IMPL_NOTIFICATION = new PageImpl<Notification>(
            Collections.EMPTY_LIST,
            PAGEABLE,
            0
    );
}