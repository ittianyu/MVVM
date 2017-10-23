package com.ittianyu.mvvm.application.g_data_binding.common.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.ittianyu.mvvm.application.g_data_binding.common.bean.Lcee;
import com.ittianyu.mvvm.application.g_data_binding.common.bean.User;
import com.ittianyu.mvvm.application.g_data_binding.common.repository.UserDataSource;
import com.ittianyu.mvvm.application.g_data_binding.common.repository.local.service.UserService;
import com.ittianyu.mvvm.application.g_data_binding.common.repository.local.service.UserServiceImpl;

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
    public LiveData<Lcee<User>> queryUserByUsername(String username) {
        final MediatorLiveData<Lcee<User>> data = new MediatorLiveData<>();
        data.setValue(Lcee.<User>loading());

        data.addSource(userService.queryByUsername(username), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (null == user) {
                    data.setValue(Lcee.<User>empty());
                } else {
                    data.setValue(Lcee.content(user));
                }
            }
        });
        return data;
    }
    
    public LiveData<Long> addUser(User user) {
        return userService.add(user);
    }
}
