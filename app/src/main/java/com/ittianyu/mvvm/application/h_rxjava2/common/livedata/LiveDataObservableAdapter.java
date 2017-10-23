package com.ittianyu.mvvm.application.h_rxjava2.common.livedata;

import android.arch.lifecycle.LiveData;

import com.ittianyu.mvvm.application.h_rxjava2.common.bean.Lcee;

import io.reactivex.Observable;

/**
 * Created by 86839 on 2017/10/21.
 */

public class LiveDataObservableAdapter {
    public static <T> LiveData<T> fromObservable(final Observable<T> observable) {
        return new ObservableLiveData<>(observable);
    }

    public static <T> LiveData<Lcee<T>> fromObservableLcee(final Observable<T> observable) {
        return new ObservableLceeLiveData<>(observable);
    }

}
