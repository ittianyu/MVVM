package com.ittianyu.mvvm.application.e_simple_data_source.common.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.ittianyu.mvvm.application.e_simple_data_source.common.bean.Lcee;
import com.ittianyu.mvvm.application.e_simple_data_source.common.bean.User;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.UserDataSource;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.LocalUserDataSource;
import com.ittianyu.mvvm.application.e_simple_data_source.common.utils.NetworkUtils;

/**
 * Created by 86839 on 2017/10/6.
 */

public class UserRepository {
    private static final UserRepository instance = new UserRepository();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }


    private Context context;
    private UserDataSource remoteUserDataSource = RemoteUserDataSource.getInstance();
    private UserDataSource localUserDataSource = LocalUserDataSource.getInstance();

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<Lcee<User>> getUser(String username) {
        if (NetworkUtils.isConnected(context)) {
            return getUserFromRemote(username);
        } else {
            return getUserFromLocal(username);
        }
    }

    private LiveData<Lcee<User>> getUserFromRemote(String username) {
        return getUserFromDataSource(remoteUserDataSource, username);
    }

    private LiveData<Lcee<User>> getUserFromLocal(String username) {
        return getUserFromDataSource(localUserDataSource, username);
    }

    private LiveData<Lcee<User>> getUserFromDataSource(UserDataSource dataSource, String username) {
        final MutableLiveData<Lcee<User>> data = new MutableLiveData<>();
        data.setValue(Lcee.<User>loading());
        dataSource.queryUserByUsername(username, new UserDataSource.Result<User>() {
            @Override
            public void onSuccess(User user) {
                if (null == data)
                    data.setValue(Lcee.<User>empty());
                else
                    data.setValue(Lcee.content(user));
            }

            @Override
            public void onFailed(Throwable throwable) {
                data.setValue(Lcee.<User>error(throwable));
            }
        });
        return data;
    }

}
