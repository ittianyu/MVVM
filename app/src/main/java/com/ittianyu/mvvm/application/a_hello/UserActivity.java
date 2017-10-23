package com.ittianyu.mvvm.application.a_hello;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ittianyu.mvvm.R;

/**
 * Created by 86839 on 2017/10/4.
 */

public class UserActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private TextView tvId;
    private TextView tvName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_user);
        initView();
        initData();
    }

    private void initView() {
        tvId = (TextView) findViewById(R.id.tv_id);
        tvName = (TextView) findViewById(R.id.tv_name);
    }

    private void initData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateView(user);
            }
        });

        userViewModel.setUsername("ittianyu");
    }

    private void updateView(User user) {
        tvId.setText(user.getId() + "");
        tvName.setText(user.getName());
    }

}
