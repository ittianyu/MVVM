package com.ittianyu.mvvm.application.f_load_more.common.repository.local.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;
import com.ittianyu.mvvm.application.f_load_more.common.repository.local.dao.ProjectsDao;
import com.ittianyu.mvvm.application.f_load_more.common.repository.local.db.DBHelper;

/**
 * Created by 86839 on 2017/10/7.
 */

public class ProjectsServiceImpl implements ProjectsService {
    private static final ProjectsServiceImpl instance = new ProjectsServiceImpl();

    private ProjectsServiceImpl() {
    }

    public static ProjectsServiceImpl getInstance() {
        return instance;
    }


    private ProjectsDao projectsDao = DBHelper.getInstance().getDb().getProjectsDao();

    @Override
    public LiveData<Long> add(final Projects projects) {
        // transfer long to LiveData<Long>
        final MutableLiveData<Long> data = new MutableLiveData<>();
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return projectsDao.add(projects);
            }

            @Override
            protected void onPostExecute(Long rowId) {
                data.setValue(rowId);
            }
        }.execute();
        return data;
    }

    @Override
    public LiveData<Projects> queryProjects(int page) {
        return projectsDao.queryProjects(page);
    }

}
