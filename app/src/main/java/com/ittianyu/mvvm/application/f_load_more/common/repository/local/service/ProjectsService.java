package com.ittianyu.mvvm.application.f_load_more.common.repository.local.service;

import android.arch.lifecycle.LiveData;

import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;


/**
 * Created by 86839 on 2017/10/7.
 */

public interface ProjectsService {
    LiveData<Long> add(Projects projects);

    /**
     *
     * @param page start from 1
     * @return
     */
    LiveData<Projects> queryProjects(int page);
}
