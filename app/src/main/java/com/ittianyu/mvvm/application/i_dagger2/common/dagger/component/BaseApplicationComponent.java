package com.ittianyu.mvvm.application.i_dagger2.common.dagger.component;

import com.ittianyu.mvvm.BaseApplication;
import com.ittianyu.mvvm.application.i_dagger2.common.dagger.module.ActivitiesContributeModule_UserActivityInjector;
import com.ittianyu.mvvm.application.i_dagger2.common.dagger.module.BaseModule;
import com.ittianyu.mvvm.application.i_dagger2.common.dagger.module.UserViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by 86839 on 2017/10/30.
 */
@Singleton
@Component(modules = {
        UserViewModelModule.class,
        BaseModule.class,
        ActivitiesContributeModule_UserActivityInjector.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
})
public interface BaseApplicationComponent {
    void inject(BaseApplication application);

}
