package com.ittianyu.mvvm.application.e_simple_data_source.common.repository.remote;

import com.ittianyu.mvvm.application.e_simple_data_source.common.bean.User;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.RetrofitFactory;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.UserDataSource;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.LocalUserDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 86839 on 2017/10/6.
 */

public class RemoteUserDataSource implements UserDataSource {
    private static final RemoteUserDataSource instance = new RemoteUserDataSource();
    private RemoteUserDataSource() {
    }
    public static RemoteUserDataSource getInstance() {
        return instance;
    }


    private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

    @Override
    public void queryUserByUsername(String username, final Result<User> result) {
        userApi.queryUserByUsername(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        result.onSuccess(user);
                        // update cache
                        LocalUserDataSource.getInstance().addUser(user);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                        result.onFailed(t);
                    }
                });
    }
}
