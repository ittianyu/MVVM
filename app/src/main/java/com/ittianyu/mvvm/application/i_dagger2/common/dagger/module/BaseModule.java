package com.ittianyu.mvvm.application.i_dagger2.common.dagger.module;

import android.content.Context;

import com.ittianyu.mvvm.BaseApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 86839 on 2017/11/6.
 */
@Module
public class BaseModule {

    @Provides
    Context provideContext() {
        return BaseApplication.getAppContext();
    }
}
