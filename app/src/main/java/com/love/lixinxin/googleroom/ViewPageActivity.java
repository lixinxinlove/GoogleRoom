package com.love.lixinxin.googleroom;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ViewPageActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager mViewPager;

    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        mViewPager = findViewById(R.id.vp);

        mAdapter = new MyAdapter(this);

        mViewPager.setAdapter(mAdapter);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vp:
                Toast.makeText(ViewPageActivity.this, "点击了哈哈", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
