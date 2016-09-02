package com.lzy.verticalslideview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lzy.verticalslideview.fragment.BaseFragment;
import com.lzy.verticalslideview.fragment.Fragment_GridView;
import com.lzy.verticalslideview.fragment.Fragment_ListView;
import com.lzy.verticalslideview.fragment.Fragment_RecyclerView;
import com.lzy.verticalslideview.fragment.Fragment_ScrollView;
import com.lzy.verticalslideview.fragment.Fragment_ViewPager;
import com.lzy.verticalslideview.fragment.Fragment_WebView;
import com.lzy.widget.VerticalSlide;

public class DragActivity extends AppCompatActivity implements View.OnClickListener {

    private VerticalSlide verticalSlide;
    private BaseFragment topFragment;
    private BaseFragment bottomFragment;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        verticalSlide = (VerticalSlide) findViewById(R.id.dragLayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        Intent intent = getIntent();
        String top = intent.getStringExtra("top");
        String bottom = intent.getStringExtra("bottom");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if ("ScrollView".equals(top)) topFragment = new Fragment_ScrollView();
        if ("ListView".equals(top)) topFragment = new Fragment_ListView();
        if ("GridView".equals(top)) topFragment = new Fragment_GridView();
        if ("WebView".equals(top)) topFragment = new Fragment_WebView();
        if ("RecyclerView".equals(top)) topFragment = new Fragment_RecyclerView();
        if ("ViewPager".equals(top)) topFragment = new Fragment_ViewPager();
        transaction.replace(R.id.first, topFragment);

        if ("ScrollView".equals(bottom)) bottomFragment = new Fragment_ScrollView();
        if ("ListView".equals(bottom)) bottomFragment = new Fragment_ListView();
        if ("GridView".equals(bottom)) bottomFragment = new Fragment_GridView();
        if ("RecyclerView".equals(bottom)) bottomFragment = new Fragment_RecyclerView();
        if ("ViewPager".equals(bottom)) bottomFragment = new Fragment_ViewPager();
        if ("WebView".equals(bottom)) {
            bottomFragment = new Fragment_WebView();
            verticalSlide.setOnShowNextPageListener(new VerticalSlide.OnShowNextPageListener() {
                @Override
                public void onShowNextPage() {
                    ((Fragment_WebView) bottomFragment).initView();
                }
            });
        }
        transaction.replace(R.id.second, bottomFragment);

        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        /**
         * 返回顶部分三步
         * 1.第二页滚动到第二页的顶部
         * 2.VerticalSlide从第二页返回第一页
         * 3.第一页滚动到第一页的顶部
         * OnGoTopListener 表示第一页滚动到顶部 的方法,这个由于采用什么布局,库内部并不知道,所以一般是自己实现
         * 也可以不实现,直接传null
         */
        bottomFragment.goTop();
        verticalSlide.goTop(new VerticalSlide.OnGoTopListener() {
            @Override
            public void goTop() {
                topFragment.goTop();
            }
        });
    }
}