package com.ittianyu.mvvm.application.f_load_more.common.repository.local.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by 86839 on 2017/10/7.
 */

public class DBHelper {
    private static final DBHelper instance = new DBHelper();
    private static final String DATABASE_NAME = "f_load_more";

    private DBHelper() {

    }

    public static DBHelper getInstance() {
        return instance;
    }

    private DB db;

    public void init(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(), DB.class, DATABASE_NAME).build();
    }

    public DB getDb() {
        return db;
    }
}
