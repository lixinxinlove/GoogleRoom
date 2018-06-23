package com.love.lixinxin.googleroom.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.love.lixinxin.googleroom.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity... users);

    @Update
    void update(UserEntity... users);

    @Delete
    void delete(UserEntity... users);

    @Delete
    int deleteAll(List<UserEntity> users);

    //RxJava
    @Query("SELECT * FROM UserEntity ")
    Maybe<List<UserEntity>> getUsers0();

    @Query("SELECT * FROM UserEntity ")
    Single<List<UserEntity>> getUsers1();

    @Query("SELECT * FROM UserEntity ")
    Flowable<List<UserEntity>>  getUsers2();

}
