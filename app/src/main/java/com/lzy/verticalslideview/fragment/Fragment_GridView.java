package com.lzy.verticalslideview.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lzy.verticalslideview.ColorUtil;
import com.lzy.verticalslideview.R;
import com.lzy.widget.vertical.VerticalGridView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_GridView extends BaseFragment {

    private VerticalGridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gridview, container, false);
        gridView = (VerticalGridView) rootView.findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter());
        return rootView;
    }

    @Override
    public void goTop() {
        gridView.goTop();
    }

    private class MyAdapter extends BaseAdapter {

        private List<String> strings;

        public MyAdapter() {
            strings = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                strings.add("条目" + i);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(getActivity(), android.R.layout.simple_list_item_1, null);
            }
            TextView textView = (TextView) convertView;
            textView.setText(getItem(position));
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(ColorUtil.generateBeautifulColor());
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public String getItem(int position) {
            return strings.get(position);
        }

        @Override
        public int getCount() {
            return strings.size();
        }
    }
}
