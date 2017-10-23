package com.ittianyu.mvvm.application.d_state_lcee.common.repository;

import android.arch.lifecycle.LiveData;

import com.ittianyu.mvvm.application.d_state_lcee.common.bean.Lcee;
import com.ittianyu.mvvm.application.d_state_lcee.common.bean.User;

/**
 * Created by 86839 on 2017/10/6.
 */

public interface UserDataSource {
    LiveData<Lcee<User>> queryUserByUsername(String username);
}
