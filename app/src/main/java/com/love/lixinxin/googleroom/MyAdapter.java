package com.love.lixinxin.googleroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends PagerAdapter {


    private List<View> viewList;

    private Context mContext;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        viewList = new ArrayList<>();
        //初始化10个View
        //init();

        for (int i = 0; i < 10; i++) {
            View view;
            if (i % 2 == 0) {
                view = View.inflate(mContext, getResource(mContext, "item_view"), null);
            } else {
                view = View.inflate(mContext, getResource(mContext, "item_view1"), null);
            }

            viewList.add(view);
        }

    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = viewList.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "item点击", Toast.LENGTH_LONG).show();
            }
        });

        ImageView imageView = view.findViewById(R.id.iv);

        imageView.setImageResource(R.mipmap.mm);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }


    public static int getResource(Context mContext, String imageName) {
        int layoutId = mContext.getResources().getIdentifier(imageName, "layout", mContext.getPackageName());
        return layoutId;
    }


}
