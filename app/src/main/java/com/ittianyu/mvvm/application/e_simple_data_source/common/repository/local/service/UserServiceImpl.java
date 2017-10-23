package com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.service;

import com.ittianyu.mvvm.application.e_simple_data_source.common.bean.User;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.dao.UserDao;
import com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.db.DBHelper;

/**
 * Created by 86839 on 2017/10/7.
 */

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }


    private UserDao userDao = DBHelper.getInstance().getDb().getUserDao();

    @Override
    public Long add(final User user) {
        return userDao.add(user);
    }

    @Override
    public User queryByUsername(String username) throws Exception{
        return userDao.queryByUsername(username);
    }

}
