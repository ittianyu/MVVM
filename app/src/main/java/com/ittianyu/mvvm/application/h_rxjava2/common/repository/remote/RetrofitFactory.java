package com.ittianyu.mvvm.application.h_rxjava2.common.repository.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yu on 2017/2/21.
 */

public class RetrofitFactory {
    private static OkHttpClient client;
    private static Retrofit retrofit;

    private static final String HOST = "https://api.github.com";

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(9, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Retrofit getInstance() {
        return retrofit;
    }
}
