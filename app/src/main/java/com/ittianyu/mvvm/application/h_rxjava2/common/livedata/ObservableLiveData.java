package com.ittianyu.mvvm.application.h_rxjava2.common.livedata;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 86839 on 2017/10/21.
 */

class ObservableLiveData<T> extends LiveData<T> {
    private WeakReference<Disposable> mDisposableRef;
    private final Observable<T> mObservable;
    private final Object mLock = new Object();

    ObservableLiveData(@NonNull final Observable<T> observable) {
        mObservable = observable;
    }

    @Override
    protected void onActive() {
        super.onActive();

        mObservable.subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                // Don't worry about backpressure. If the stream is too noisy then
                // backpressure can be handled upstream.
                synchronized (mLock) {
                    mDisposableRef = new WeakReference<>(d);
                }
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull T t) {
                postValue(t);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable t) {
                synchronized (mLock) {
                    mDisposableRef = null;
                }
                // Errors should be handled upstream, so propagate as a crash.
                throw new RuntimeException(t);
            }

            @Override
            public void onComplete() {
                synchronized (mLock) {
                    mDisposableRef = null;
                }
            }
        });

    }

    @Override
    protected void onInactive() {
        super.onInactive();
        synchronized (mLock) {
            WeakReference<Disposable> subscriptionRef = mDisposableRef;
            if (subscriptionRef != null) {
                Disposable subscription = subscriptionRef.get();
                if (subscription != null) {
                    subscription.dispose();
                }
                mDisposableRef = null;
            }
        }
    }
}
