package com.ittianyu.mvvm.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ittianyu.mvvm.implementation.a_my_livedata.MyLiveDataActivity;

/**
 * Created by 86839 on 2017/10/23.
 */

public class ImplementationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implementation);

    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_a) {
            startActivity(new Intent(this, MyLiveDataActivity.class));
        }

    }
}
