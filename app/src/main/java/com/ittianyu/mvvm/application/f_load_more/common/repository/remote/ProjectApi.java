package com.ittianyu.mvvm.application.f_load_more.common.repository.remote;


import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by 86839 on 2017/10/6.
 */

public interface ProjectApi {
    @GET("/search/repositories?q=tetris+language:assembly&sort=stars&order=desc")
    Call<Projects> queryProjects(@Query("page") int page);
}
