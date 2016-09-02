package com.lzy.verticalslideview.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.verticalslideview.R;
import com.lzy.widget.vertical.VerticalScrollView;

public class Fragment_ScrollView extends BaseFragment {

    private VerticalScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scrollview, container, false);
        scrollView = (VerticalScrollView) rootView.findViewById(R.id.scrollView);
        TextView oldTextView = (TextView) rootView.findViewById(R.id.old_textview);
        //设置删除线
        oldTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return rootView;
    }

    @Override
    public void goTop() {
        scrollView.goTop();
    }
}
