package com.lanit.satonin18.app.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Common_Default_var {
    public static final boolean DESC = true;
    public static final int PAGE = 1;
    public static final int MAX_RESULT = 10;
    public static final int NAVIGATION_PAGES = 10;
    public static final List<Integer> selectShowListMaxResult =  new ArrayList<>(Arrays.asList( new Integer[]{
            1,2,5,10,25,50,100
    }));
}
