package com.lzy.verticalslideview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.lzy.verticalslideview.R;

public class Fragment_WebView extends Fragment {

    private View progressBar;
    private WebView webview;
    private boolean hasInited = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_webview, container, false);
        webview = (WebView) rootView.findViewById(R.id.webView);
        progressBar = rootView.findViewById(R.id.progressbar);
        return rootView;
    }

    public void initView() {
        if (null != webview && !hasInited) {
            hasInited = true;
            progressBar.setVisibility(View.GONE);
            webview.loadUrl("https://github.com/jeasonlzy0216");
        }
    }
}
