package com.love.lixinxin.googleroom.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//收货地址
@Entity
public class AddressEntity {

    @PrimaryKey
    private long id;
    private long userId;
    private String city;
    private String number;
    private String phone;

}
