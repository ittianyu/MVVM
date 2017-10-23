package com.ittianyu.mvvm.implementation.a_my_livedata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 86839 on 2017/10/23.
 */

public class MyLiveData<T> {
    private List<OnValueChangeListener<T>> observers = new ArrayList<>();

    public void observer(OnValueChangeListener<T> observer) {
        observers.add(observer);
    }

    public void setValue(T value) {
        for (OnValueChangeListener<T> listener :observers) {
            listener.onChange(value);
        }
    }

    public void unobserver(OnValueChangeListener<T> observer) {
        observers.remove(observer);
    }
}
