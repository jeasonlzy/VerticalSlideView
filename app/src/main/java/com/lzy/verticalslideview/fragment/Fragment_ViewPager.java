package com.lzy.verticalslideview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.verticalslideview.R;
import com.lzy.widget.tab.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Fragment_ViewPager extends Fragment {

    private LinkedHashMap<String, Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        PagerSlidingTabStrip tab = (PagerSlidingTabStrip) rootView.findViewById(R.id.tab);
        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
        tab.setViewPager(viewPager);
        return rootView;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<String> titles;

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new LinkedHashMap<>();
            fragments.put("ListView", new Fragment_ListView());
            fragments.put("GridView", new Fragment_GridView());
            fragments.put("WebView", new Fragment_WebView());
            fragments.put("View", new Fragment_View());

            titles = new ArrayList<>();
            titles.addAll(fragments.keySet());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(titles.get(position));
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
