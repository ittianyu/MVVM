package com.ittianyu.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ittianyu.mvvm.application.ApplicationActivity;
import com.ittianyu.mvvm.implementation.ImplementationActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_application: {
                startActivity(new Intent(this, ApplicationActivity.class));
                break;
            }
            case R.id.btn_implementation: {
                startActivity(new Intent(this, ImplementationActivity.class));
                break;
            }
        }
    }
}
