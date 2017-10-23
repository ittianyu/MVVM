package com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local;

import android.os.AsyncTask;

import com.ittianyu.mvvm.application.e_simple_data_source.common.bean.User;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.UserDataSource;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.service.UserService;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.service.UserServiceImpl;

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

    public void addUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userService.add(user);
                return null;
            }
        }.execute();
    }

    @Override
    public void queryUserByUsername(final String username, final Result<User> result) {
        new AsyncTask<Void, Void, Object>() {

            @Override
            protected Object doInBackground(Void... voids) {
                try {
                    User user = userService.queryByUsername(username);
                    return user;
                } catch (Exception e) {
                    e.printStackTrace();
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object obj) {
                if (obj instanceof User)
                    result.onSuccess((User) obj);
                else if (obj instanceof Exception)
                    result.onFailed((Throwable) obj);
                else
                    result.onFailed(null);
            }
        }.execute();

    }
}
