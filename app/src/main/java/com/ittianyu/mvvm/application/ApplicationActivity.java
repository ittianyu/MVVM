package com.ittianyu.mvvm.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ittianyu.mvvm.R;

/**
 * Created by 86839 on 2017/10/23.
 */

public class ApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_a: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.a_hello.UserActivity.class));
                break;
            }
            case R.id.btn_b: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.b_repository.features.user.UserActivity.class));
                break;
            }
            case R.id.btn_c: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.c_cache.features.user.UserActivity.class));
                break;
            }
            case R.id.btn_d: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.d_state_lcee.features.user.UserActivity.class));
                break;
            }
            case R.id.btn_e: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.e_simple_data_source.features.user.UserActivity.class));
                break;
            }
            case R.id.btn_f: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.f_load_more.features.projects.ProjectsActivity.class));
                break;
            }
            case R.id.btn_g: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.g_data_binding.features.user.UserActivity.class));
                break;
            }
            case R.id.btn_h: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.h_rxjava2.features.user.UserActivity.class));
                break;
            }
            case R.id.btn_i: {
                startActivity(new Intent(this, com.ittianyu.mvvm.application.i_dagger2.features.user.UserActivity.class));
                break;
            }

        }
    }
}
