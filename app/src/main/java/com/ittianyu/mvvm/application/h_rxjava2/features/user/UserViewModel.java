package com.ittianyu.mvvm.application.h_rxjava2.features.user;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.ittianyu.mvvm.application.h_rxjava2.common.bean.Lcee;
import com.ittianyu.mvvm.application.h_rxjava2.common.bean.User;
import com.ittianyu.mvvm.application.h_rxjava2.common.repository.UserRepository;

/**
 * Created by 86839 on 2017/10/4.
 */

public class UserViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private MutableLiveData<String> ldUsername;;
    private LiveData<Lcee<User>> ldUser;

    public LiveData<Lcee<User>> getUser() {
        if (null == ldUser) {
            ldUsername = new MutableLiveData<>();
            ldUser = Transformations.switchMap(ldUsername, new Function<String, LiveData<Lcee<User>>>() {
                @Override
                public LiveData<Lcee<User>> apply(String username) {
                    return userRepository.getUser(username);
                }
            });
        }
        return ldUser;
    }

    public void reload(String username) {
        ldUsername.setValue(username);
    }

}
