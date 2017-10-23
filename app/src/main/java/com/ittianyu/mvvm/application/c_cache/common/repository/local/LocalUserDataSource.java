package com.ittianyu.mvvm.application.c_cache.common.repository.local;

import android.arch.lifecycle.LiveData;

import com.ittianyu.mvvm.application.c_cache.common.bean.User;
import com.ittianyu.mvvm.application.c_cache.common.repository.UserDataSource;
import com.ittianyu.mvvm.application.c_cache.common.repository.local.service.UserService;
import com.ittianyu.mvvm.application.c_cache.common.repository.local.service.UserServiceImpl;

/**
 * Created by 86839 on 2017/10/6.
 */

public class LocalUserDataSource implements UserDataSource {
    private static final LocalUserDataSource instance = new LocalUserDataSource();
    private LocalUserDataSource() {
    }
    public static LocalUserDataSource getInstance() {
        return instance;
    }


    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public LiveData<User> queryUserByUsername(String username) {
        return userService.queryByUsername(username);
    }

    public LiveData<Long> addUser(User user) {
        return userService.add(user);
    }
}
