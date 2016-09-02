package com.lzy.verticalslideview.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.verticalslideview.ColorUtil;
import com.lzy.verticalslideview.R;
import com.lzy.widget.vertical.VerticalRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_RecyclerView extends BaseFragment {

    private VerticalRecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView = (VerticalRecyclerView) rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MyAdapter());
        return rootView;
    }

    @Override
    public void goTop() {
        recyclerView.goTop();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.SimpleViewHolder> {

        private List<String> strings;

        public MyAdapter() {
            strings = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                strings.add("RecyclerView: 条目" + i);
            }
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleViewHolder(View.inflate(getActivity(), android.R.layout.simple_list_item_1, null));
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        protected class SimpleViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public SimpleViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView;
            }

            public void bindData(int position) {
                textView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
                textView.setText(strings.get(position));
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundColor(ColorUtil.generateBeautifulColor());
            }
        }
    }
}
