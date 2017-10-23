package com.ittianyu.mvvm.application.c_cache.common.repository;

import android.arch.lifecycle.LiveData;

import com.ittianyu.mvvm.application.c_cache.common.bean.User;

/**
 * Created by 86839 on 2017/10/6.
 */

public interface UserDataSource {
    LiveData<User> queryUserByUsername(String username);
}
