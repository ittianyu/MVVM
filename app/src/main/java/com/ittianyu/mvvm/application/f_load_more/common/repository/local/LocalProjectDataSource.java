package com.ittianyu.mvvm.application.f_load_more.common.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.ittianyu.mvvm.application.f_load_more.common.bean.Lcee;
import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;
import com.ittianyu.mvvm.application.f_load_more.common.repository.ProjectDataSource;
import com.ittianyu.mvvm.application.f_load_more.common.repository.local.service.ProjectsService;
import com.ittianyu.mvvm.application.f_load_more.common.repository.local.service.ProjectsServiceImpl;

/**
 * Created by 86839 on 2017/10/6.
 */

public class LocalProjectDataSource implements ProjectDataSource {
    private static final LocalProjectDataSource instance = new LocalProjectDataSource();

    private LocalProjectDataSource() {
    }

    public static LocalProjectDataSource getInstance() {
        return instance;
    }


    private ProjectsService projectsService = ProjectsServiceImpl.getInstance();

    public LiveData<Long> addProjects(Projects projects) {
        // format items to json
        projects.itemsToJson();
        return projectsService.add(projects);
    }

    @Override
    public LiveData<Lcee<Projects>> queryProjects(int page) {
        final MediatorLiveData<Lcee<Projects>> data = new MediatorLiveData<>();
        data.setValue(Lcee.<Projects>loading());

        data.addSource(projectsService.queryProjects(page), new Observer<Projects>() {
            @Override
            public void onChanged(@Nullable Projects projects) {
                if (null == projects) {
                    data.setValue(Lcee.<Projects>empty());
                } else {
                    // parse items from json
                    projects.itemsFromJson();
                    data.setValue(Lcee.content(projects));
                }
            }
        });
        return data;
    }
}
