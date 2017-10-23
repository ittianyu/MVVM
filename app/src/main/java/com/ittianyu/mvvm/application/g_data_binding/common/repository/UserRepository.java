package com.ittianyu.mvvm.application.g_data_binding.common.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.ittianyu.mvvm.application.g_data_binding.common.bean.Lcee;
import com.ittianyu.mvvm.application.g_data_binding.common.bean.User;
import com.ittianyu.mvvm.application.g_data_binding.common.repository.local.LocalUserDataSource;
import com.ittianyu.mvvm.application.g_data_binding.common.repository.remote.RemoteUserDataSource;
import com.ittianyu.mvvm.application.g_data_binding.common.utils.NetworkUtils;

/**
 * Created by 86839 on 2017/10/6.
 */

public class UserRepository {
    private static final UserRepository instance = new UserRepository();
    private UserRepository() {
    }
    public static UserRepository getInstance() {
        return instance;
    }


    private Context context;
    private UserDataSource remoteUserDataSource = RemoteUserDataSource.getInstance();
    private UserDataSource localUserDataSource = LocalUserDataSource.getInstance();

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<Lcee<User>> getUser(String username) {
        if (NetworkUtils.isConnected(context)) {
            return remoteUserDataSource.queryUserByUsername(username);
        } else {
            return localUserDataSource.queryUserByUsername(username);
        }
    }

}
