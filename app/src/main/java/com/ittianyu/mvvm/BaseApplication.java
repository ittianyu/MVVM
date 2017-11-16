package com.ittianyu.mvvm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.ittianyu.mvvm.application.i_dagger2.common.dagger.component.DaggerBaseApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by 86839 on 2017/11/6.
 */

public class BaseApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    private static Context appContext;
    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        DaggerBaseApplicationComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
