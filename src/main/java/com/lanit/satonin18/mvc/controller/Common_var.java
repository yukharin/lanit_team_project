package com.lanit.satonin18.mvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Common_var {
    public static final boolean DEFAULT_DESC = true;
    public static final int DEFAULT_MAX_RESULT = 10;
    public static final int DEFAULT_NAVIGATION_PAGES = 10;
    public static final List<Integer> selectShowListMaxResult =  new ArrayList<>(Arrays.asList( new Integer[]{
            1,2,5,10,25,50,100
    }));
}
