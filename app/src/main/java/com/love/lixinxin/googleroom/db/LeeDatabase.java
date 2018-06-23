package com.love.lixinxin.googleroom.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.love.lixinxin.googleroom.data.dao.UserDao;
import com.love.lixinxin.googleroom.data.entity.UserEntity;


@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class LeeDatabase extends RoomDatabase {

    private static final String DB_NAME = "LeeDatabase.db";
    private static volatile LeeDatabase instance;

    public static synchronized LeeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static LeeDatabase create(final Context context) {
        return Room.databaseBuilder(context, LeeDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
    }

    public abstract UserDao getUserDao();

}
