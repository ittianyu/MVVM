package com.ittianyu.mvvm.application.f_load_more.features.projects;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.ittianyu.mvvm.application.f_load_more.common.bean.Lcee;
import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;
import com.ittianyu.mvvm.application.f_load_more.common.repository.ProjectsRepository;
import com.ittianyu.mvvm.application.f_load_more.common.utils.PageUtils;

/**
 * Created by 86839 on 2017/10/4.
 */

public class ProjectsViewModel extends ViewModel {
    private ProjectsRepository projectsRepository = ProjectsRepository.getInstance();
    private MutableLiveData<Integer> ldPage;;
    private LiveData<Lcee<Projects>> ldProjects;

    public LiveData<Lcee<Projects>> getProjects() {
        if (null == ldProjects) {
            ldPage = new MutableLiveData<>();
            ldProjects = Transformations.switchMap(ldPage, new Function<Integer, LiveData<Lcee<Projects>>>() {
                @Override
                public LiveData<Lcee<Projects>> apply(Integer page) {
                    return projectsRepository.getProjects(page);
                }
            });
        }
        return ldProjects;
    }

    public void reload() {
        ldPage.setValue(1);
    }

    public void loadMore(int currentCount) {
        ldPage.setValue(PageUtils.getPage(currentCount));
    }
}
