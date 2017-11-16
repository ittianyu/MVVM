package com.ittianyu.mvvm.application.i_dagger2.common.repository.local.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.ittianyu.mvvm.application.i_dagger2.common.bean.User;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.local.dao.UserDao;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 86839 on 2017/10/7.
 */
@Singleton
public class UserServiceImpl implements UserService {
/*
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }
*/


    private UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public LiveData<Long> add(final User user) {
        // transfer long to LiveData<Long>
        final MutableLiveData<Long> data = new MutableLiveData<>();
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return userDao.add(user);
            }

            @Override
            protected void onPostExecute(Long rowId) {
                data.setValue(rowId);
            }
        }.execute();
        return data;
    }

    @Override
    public LiveData<User> queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }

}
