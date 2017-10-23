package com.ittianyu.mvvm.application.b_repository.features.user;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ittianyu.mvvm.R;
import com.ittianyu.mvvm.application.b_repository.common.bean.User;

/**
 * Created by 86839 on 2017/10/4.
 */

public class UserActivity extends AppCompatActivity {
    private static final String TAG = UserActivity.class.getName();
    private UserViewModel userViewModel;
    private TextView tvId;
    private TextView tvName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_user);
        initView();
        initData();
    }

    private void initView() {
        tvId = (TextView) findViewById(R.id.tv_id);
        tvName = (TextView) findViewById(R.id.tv_name);
    }

    private void initData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser("ittianyu").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateView(user);
            }
        });
    }

    private void updateView(User user) {
        Log.d(TAG, user.toString());
        tvId.setText(user.getId() + "");
        tvName.setText(user.getName());
    }

}
