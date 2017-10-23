package com.ittianyu.mvvm.application.f_load_more.common.repository.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;
import com.ittianyu.mvvm.application.f_load_more.common.repository.local.dao.ProjectsDao;

/**
 * Created by 86839 on 2017/10/7.
 */
@Database(entities = {Projects.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {
    public abstract ProjectsDao getProjectsDao();

}
