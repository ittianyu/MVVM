package com.ittianyu.mvvm.application.f_load_more.common.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.ittianyu.mvvm.application.f_load_more.common.bean.Lcee;
import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;
import com.ittianyu.mvvm.application.f_load_more.common.repository.ProjectDataSource;
import com.ittianyu.mvvm.application.f_load_more.common.repository.local.LocalProjectDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 86839 on 2017/10/6.
 */

public class RemoteProjectDataSource implements ProjectDataSource {
    private static final RemoteProjectDataSource instance = new RemoteProjectDataSource();

    private RemoteProjectDataSource() {
    }

    public static RemoteProjectDataSource getInstance() {
        return instance;
    }


    private ProjectApi projectApi = RetrofitFactory.getInstance().create(ProjectApi.class);


    @Override
    public LiveData<Lcee<Projects>> queryProjects(final int page) {
        final MutableLiveData<Lcee<Projects>> data = new MutableLiveData<>();
        data.setValue(Lcee.<Projects>loading());

        projectApi.queryProjects(page)
                .enqueue(new Callback<Projects>() {
                    @Override
                    public void onResponse(Call<Projects> call, Response<Projects> response) {
                        Projects projects = response.body();
                        if (null == projects) {
                            data.setValue(Lcee.<Projects>empty());
                            return;
                        }
                        data.setValue(Lcee.content(projects));
                        // update cache
                        projects.setPage(page);// must set page before save to db
                        LocalProjectDataSource.getInstance().addProjects(projects);
                    }

                    @Override
                    public void onFailure(Call<Projects> call, Throwable t) {
                        t.printStackTrace();
                        data.setValue(Lcee.<Projects>error(t));
                    }
                });
        return data;
    }
}
