package com.ittianyu.mvvm.application.h_rxjava2.common.repository.remote;

import android.arch.lifecycle.LiveData;

import com.ittianyu.mvvm.application.h_rxjava2.common.bean.Lcee;
import com.ittianyu.mvvm.application.h_rxjava2.common.bean.User;
import com.ittianyu.mvvm.application.h_rxjava2.common.livedata.LiveDataObservableAdapter;
import com.ittianyu.mvvm.application.h_rxjava2.common.repository.UserDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 86839 on 2017/10/6.
 */

public class RemoteUserDataSource implements UserDataSource {
    private static final RemoteUserDataSource instance = new RemoteUserDataSource();

    private RemoteUserDataSource() {
    }

    public static RemoteUserDataSource getInstance() {
        return instance;
    }


    private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

    @Override
    public LiveData<Lcee<User>> queryUserByUsername(String username) {
        return LiveDataObservableAdapter.fromObservableLcee(
                userApi.queryUserByUsername(username)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }
}
