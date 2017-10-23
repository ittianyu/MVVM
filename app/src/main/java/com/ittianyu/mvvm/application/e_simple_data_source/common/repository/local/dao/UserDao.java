package com.ittianyu.mvvm.application.e_simple_data_source.common.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ittianyu.mvvm.application.e_simple_data_source.common.bean.User;


/**
 * Created by 86839 on 2017/10/7.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)// cache need update
    Long add(User user);

    @Query("select * from user where login = :username")
    User queryByUsername(String username) throws Exception;
}
