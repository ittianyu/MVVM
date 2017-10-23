package com.ittianyu.mvvm.application.g_data_binding.features.user;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ittianyu.mvvm.R;
import com.ittianyu.mvvm.databinding.GActivityUserBinding;
import com.ittianyu.mvvm.application.g_data_binding.common.bean.Lcee;
import com.ittianyu.mvvm.application.g_data_binding.common.bean.User;
import com.ittianyu.mvvm.application.g_data_binding.common.repository.UserRepository;
import com.ittianyu.mvvm.application.g_data_binding.common.repository.local.db.DBHelper;

/**
 * Created by 86839 on 2017/10/4.
 */

public class UserActivity extends AppCompatActivity {
    private static final String TAG = UserActivity.class.getName();
    // vm
    private UserViewModel userViewModel;

    // view
    private GActivityUserBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.g_activity_user);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        bind = DataBindingUtil.setContentView(this, R.layout.g_activity_user);
        bind.setUser(new User());

    }

    private void initData() {
        DBHelper.getInstance().init(this);
        UserRepository.getInstance().init(this);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<Lcee<User>>() {
            @Override
            public void onChanged(@Nullable Lcee<User> data) {
                updateView(data);
            }
        });

        reload();
    }

    private void initEvent() {
        View.OnClickListener reloadClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                reload();
            }
        };
        bind.vError.setOnClickListener(reloadClickListener);
        bind.vEmpty.setOnClickListener(reloadClickListener);

        findViewById(R.id.btn_search).setOnClickListener(reloadClickListener);

        bind.etUsername.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    hideKeyboard();
                    reload();
                    return true;
                }
                return false;
            }
        });

    }

    private void hideKeyboard() {
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(UserActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void reload() {
        // reload
        userViewModel.reload(getUsername());
    }

    private void updateView(Lcee<User> lcee) {
        switch (lcee.status) {
            case Content: {
                showContent();
                bind.setUser(lcee.data);
                break;
            }
            case Empty: {
                showEmpty();
                break;
            }
            case Error: {
                showError();
                break;
            }
            case Loading: {
                showLoading();
                break;
            }
        }
    }

    private void showContent() {
        bind.vContent.setVisibility(View.VISIBLE);
        bind.vEmpty.setVisibility(View.GONE);
        bind.vError.setVisibility(View.GONE);
        bind.vLoading.setVisibility(View.GONE);
    }

    private void showEmpty() {
        bind.vContent.setVisibility(View.GONE);
        bind.vEmpty.setVisibility(View.VISIBLE);
        bind.vError.setVisibility(View.GONE);
        bind.vLoading.setVisibility(View.GONE);
    }

    private void showError() {
        bind.vContent.setVisibility(View.GONE);
        bind.vEmpty.setVisibility(View.GONE);
        bind.vError.setVisibility(View.VISIBLE);
        bind.vLoading.setVisibility(View.GONE);
    }

    private void showLoading() {
        bind.vContent.setVisibility(View.GONE);
        bind.vEmpty.setVisibility(View.GONE);
        bind.vError.setVisibility(View.GONE);
        bind.vLoading.setVisibility(View.VISIBLE);
    }

    private String getUsername() {
        return bind.etUsername.getText().toString();
    }
}
