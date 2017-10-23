package com.ittianyu.mvvm.application.c_cache.features.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ittianyu.mvvm.application.c_cache.common.bean.User;
import com.ittianyu.mvvm.application.c_cache.common.repository.UserRepository;

/**
 * Created by 86839 on 2017/10/4.
 */

public class UserViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private LiveData<User> user;

    public LiveData<User> getUser(String username) {
        if (null == user)
            user = userRepository.getUser(username);
        return user;
    }
}
