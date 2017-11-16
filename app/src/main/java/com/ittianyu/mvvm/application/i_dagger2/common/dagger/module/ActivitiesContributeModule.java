package com.ittianyu.mvvm.application.i_dagger2.common.dagger.module;

import com.ittianyu.mvvm.application.i_dagger2.features.user.UserActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by 86839 on 2017/11/5.
 */
@Module
public abstract class ActivitiesContributeModule {
    @ContributesAndroidInjector(modules = {UserActivityModule.class})
    abstract UserActivity userActivityInjector();

}
