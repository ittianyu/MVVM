package com.ittianyu.mvvm.application.f_load_more.common.utils;

/**
 * Created by 86839 on 2017/10/19.
 */

public class PageUtils {
    private static final int COUNT_PER_PAGE = 30;

    public static int getPage(int count) {
        int page = count / COUNT_PER_PAGE + 1;
        if (count % COUNT_PER_PAGE > 0)
            page++;
        return page;
    }
}
