package com.love.lixinxin.googleroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.love.lixinxin.googleroom.app.App;
import com.love.lixinxin.googleroom.data.entity.UserEntity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Date;
import java.util.List;
import java.util.Observer;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd;
    private Button btnQuery;
    private Button btnDel;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btn_add);
        btnQuery = findViewById(R.id.btn_query);
        btnDel = findViewById(R.id.btn_del);
        tvInfo = findViewById(R.id.tv_info);


        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnDel.setOnClickListener(this);


        startActivity(new Intent(this, ViewPageActivity.class));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                UserEntity userEntity = new UserEntity();
                userEntity.setSex(true);
                userEntity.setUserAge(18);
                userEntity.setUserName("leee");
                add(userEntity);
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_del:
                delAll();
                break;
            default:
                break;
        }
    }

    private void query() {

        App.db.getUserDao().getUsers0().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new MaybeObserver<List<UserEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<UserEntity> userEntities) {
                for (UserEntity entity : userEntities) {
                    Log.e("lee", entity.toString());
                }
                tvInfo.setText(userEntities.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void add(UserEntity userEntity) {
        Flowable
                .create((FlowableOnSubscribe<UserEntity>) e -> {
                    App.db.getUserDao().insert(userEntity);
                    e.onComplete();
                }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserEntity>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(UserEntity note) {
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void delAll() {
        Flowable
                .create((FlowableOnSubscribe<Integer>) e -> {
                    int count = App.db.getUserDao().deleteAll(App.db.getUserDao().getAllUsers());
                    e.onNext(count);
                    e.onComplete();
                }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer count) {
                        Log.e("lee", count + "-----");
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "del_ok", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
