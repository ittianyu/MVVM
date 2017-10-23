package com.ittianyu.mvvm.application.h_rxjava2.common.repository;

import android.arch.lifecycle.LiveData;

import com.ittianyu.mvvm.application.h_rxjava2.common.bean.Lcee;
import com.ittianyu.mvvm.application.h_rxjava2.common.bean.User;

/**
 * Created by 86839 on 2017/10/6.
 */

public interface UserDataSource {
    LiveData<Lcee<User>> queryUserByUsername(String username);
}
