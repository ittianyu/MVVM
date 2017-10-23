package com.ittianyu.mvvm.application.c_cache.common.repository.local.service;

import android.arch.lifecycle.LiveData;

import com.ittianyu.mvvm.application.c_cache.common.bean.User;


/**
 * Created by 86839 on 2017/10/7.
 */

public interface UserService {
    LiveData<Long> add(User user);

    LiveData<User> queryByUsername(String username);
}
