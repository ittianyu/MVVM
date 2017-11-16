package com.ittianyu.mvvm.application.i_dagger2.common.dagger.module;

import android.content.Context;

import com.ittianyu.mvvm.application.i_dagger2.common.dagger.qualifier.Local;
import com.ittianyu.mvvm.application.i_dagger2.common.dagger.qualifier.Remote;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.UserDataSource;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.UserRepository;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.local.LocalUserDataSource;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.local.dao.UserDao;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.local.db.DBHelper;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.local.service.UserService;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.local.service.UserServiceImpl;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.remote.RemoteUserDataSource;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.remote.RetrofitFactory;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.remote.UserApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 86839 on 2017/11/6.
 */
@Module
public class UserViewModelModule {

    @Singleton
    @Provides
    UserRepository provideUserRepository(Context context, @Remote UserDataSource remoteUserDataSource,
                                         @Local UserDataSource localUserDataSource) {
        return new UserRepository(context, remoteUserDataSource, localUserDataSource);
    }

    @Remote
    @Singleton
    @Provides
    UserDataSource provideRemoteUserDataSource(UserApi userApi, LocalUserDataSource localUserDataSource) {
        return new RemoteUserDataSource(userApi, localUserDataSource);
    }

    @Singleton
    @Provides
    UserApi provideUserApi() {
        return RetrofitFactory.getInstance().create(UserApi.class);
    }

    @Local
    @Singleton
    @Provides
    UserDataSource provideLocalUserDataSource(UserService userService) {
        return new LocalUserDataSource(userService);
    }

    @Singleton
    @Provides
    UserService provideUserService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    @Singleton
    @Provides
    UserDao provideUserDao() {
        return DBHelper.getInstance().getDb().getUserDao();
    }

}
