package com.ittianyu.mvvm.application.f_load_more.common.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.ittianyu.mvvm.application.f_load_more.common.bean.Lcee;
import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;
import com.ittianyu.mvvm.application.f_load_more.common.repository.local.LocalProjectDataSource;
import com.ittianyu.mvvm.application.f_load_more.common.repository.remote.RemoteProjectDataSource;
import com.ittianyu.mvvm.application.f_load_more.common.utils.NetworkUtils;

/**
 * Created by 86839 on 2017/10/6.
 */

public class ProjectsRepository {
    private static final ProjectsRepository instance = new ProjectsRepository();
    private ProjectsRepository() {
    }
    public static ProjectsRepository getInstance() {
        return instance;
    }


    private Context context;
    private ProjectDataSource remoteProjectDataSource = RemoteProjectDataSource.getInstance();
    private ProjectDataSource localProjectDataSource = LocalProjectDataSource.getInstance();

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<Lcee<Projects>> getProjects(int page) {
        if (NetworkUtils.isConnected(context)) {
            return remoteProjectDataSource.queryProjects(page);
        } else {
            return localProjectDataSource.queryProjects(page);
        }
    }

}
