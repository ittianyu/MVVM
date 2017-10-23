package com.ittianyu.mvvm.implementation.a_my_livedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.mvvm.implementation.R;

public class MyLiveDataActivity extends AppCompatActivity {

    private MyLiveData<String> ldName;
    private TextView tvName;
    private View vRoot;
    private OnValueChangeListener<String> nameObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ldName.unobserver(nameObserver);
    }

    private void initView() {
        setContentView(R.layout.a_my_livedata);
        tvName = (TextView) findViewById(R.id.tv_name);
        vRoot = findViewById(R.id.v_root);
    }

    private void initData() {
        ldName = new MyLiveData<>();
        nameObserver = new OnValueChangeListener<String>() {
            @Override
            public void onChange(String value) {
                tvName.setText(value);
            }
        };
        ldName.observer(nameObserver);
    }

    private void initEvent() {
        vRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetValue();
            }
        });
    }

    private void resetValue() {
        int randomInt = (int) (Math.random() * 1000);
        ldName.setValue(randomInt + "");
    }
}
