package com.ittianyu.mvvm.application.i_dagger2.common.dagger.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.ittianyu.mvvm.application.i_dagger2.common.repository.UserRepository;
import com.ittianyu.mvvm.application.i_dagger2.features.user.UserActivity;
import com.ittianyu.mvvm.application.i_dagger2.features.user.UserViewModel;

import java.lang.reflect.InvocationTargetException;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 86839 on 2017/10/30.
 */

@Module
public class UserActivityModule {
    @Provides
    UserViewModel provideUserViewModel(UserActivity activity, final UserRepository userRepository) {
        return ViewModelProviders.of(activity, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                try {
                    return modelClass.getConstructor(UserRepository.class).newInstance(userRepository);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (InstantiationException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                }
            }
        }).get(UserViewModel.class);
    }
}

