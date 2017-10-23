package com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.service;

import com.ittianyu.mvvm.application.e_simple_data_source.common.bean.User;


/**
 * Created by 86839 on 2017/10/7.
 */

public interface UserService {
    Long add(User user);

    User queryByUsername(String username) throws Exception;
}
