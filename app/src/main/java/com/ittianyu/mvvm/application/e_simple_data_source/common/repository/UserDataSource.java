package com.ittianyu.mvvm.application.e_simple_data_source.common.repository;

import com.ittianyu.mvvm.application.e_simple_data_source.common.bean.User;

/**
 * Created by 86839 on 2017/10/6.
 */

public interface UserDataSource {
    interface Result<T> {
        void onSuccess(T data);
        void onFailed(Throwable throwable);
    }

    void queryUserByUsername(String username, Result<User> result);
}
