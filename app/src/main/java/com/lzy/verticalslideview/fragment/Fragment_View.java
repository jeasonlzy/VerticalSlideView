package com.lzy.verticalslideview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.verticalslideview.R;
import com.lzy.widget.vertical.ObservableView;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/4/11
 * 描    述：我的Github地址  https://github.com/jeasonlzy0216
 * 修订历史：
 * ================================================
 */
public class Fragment_View extends Fragment implements ObservableView {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_view, null);
        return view;
    }

    @Override
    public boolean isTop() {
        return true;
    }

    @Override
    public boolean isBottom() {
        return true;
    }
}
