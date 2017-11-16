package com.ittianyu.mvvm.application.i_dagger2.common.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.ittianyu.mvvm.application.i_dagger2.common.bean.Lcee;
import com.ittianyu.mvvm.application.i_dagger2.common.bean.User;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.UserDataSource;
import com.ittianyu.mvvm.application.i_dagger2.common.repository.local.LocalUserDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 86839 on 2017/10/6.
 */
@Singleton
public class RemoteUserDataSource implements UserDataSource {
    private UserApi userApi;
    private LocalUserDataSource localUserDataSource;

    @Inject
    public RemoteUserDataSource(UserApi userApi, LocalUserDataSource localUserDataSource) {
        this.userApi = userApi;
        this.localUserDataSource = localUserDataSource;
    }

    @Override
    public LiveData<Lcee<User>> queryUserByUsername(String username) {
        final MutableLiveData<Lcee<User>> data = new MutableLiveData<>();
        data.setValue(Lcee.<User>loading());

        userApi.queryUserByUsername(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (null == user) {
                            data.setValue(Lcee.<User>empty());
                            return;
                        }
                        data.setValue(Lcee.content(user));
                        // update cache
                        localUserDataSource.addUser(user);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                        data.setValue(Lcee.<User>error(t));
                    }
                });
        return data;
    }
}
