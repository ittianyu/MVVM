package com.ittianyu.mvvm.application.a_hello;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by 86839 on 2017/10/4.
 */

public class UserViewModel extends ViewModel {
    private MutableLiveData<User> user;

    public LiveData<User> getUser() {
        if (user == null)
            user = new MutableLiveData<>();
        return user;
    }

    public void setUsername(String username) {
        user.setValue(new User(1, username));
    }
}
