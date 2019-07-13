package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceholderFragment extends Fragment {

    private AnimatorSet animatorSet;


    ListView list;
    private String[] mName = { "AAAAA", "BBBBB", "CCCCC", "DDDDD" };
    private String[] mNum = { "1111", "2222", "3333", "4444" };
    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        View view = inflater.inflate(R.layout.fragment_placeholder, null);
        list = (ListView)view.findViewById(R.id.collect_view);
        for(int i = 0; i < mNum.length; i++){
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("name", mName[i]);
            item.put("num", mNum[i]);
            mData.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),mData,android.R.layout.simple_expandable_list_item_2,
                new String[]{"name","num"},new int[]{android.R.id.text1,android.R.id.text2});
        list.setAdapter(adapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

                View animator = getView().findViewById(R.id.animation_view);
                list.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimator1 = ObjectAnimator.ofFloat(animator, "alpha", 1.0f,0);

                transAnimator1.setInterpolator(new LinearInterpolator());
                transAnimator1.setDuration(1000);

                View listview = getView().findViewById(R.id.collect_view);
                ObjectAnimator transAnimator2 = ObjectAnimator.ofFloat(listview, "alpha", 0,1.0f);

                transAnimator2.setInterpolator(new LinearInterpolator());
                transAnimator2.setDuration(1000);

                animatorSet = new AnimatorSet();
                animatorSet.playTogether(transAnimator1,transAnimator2);
                animatorSet.start();

            }
        }, 5000);
    }

}
