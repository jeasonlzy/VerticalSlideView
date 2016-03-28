package com.lzy.verticalslideview;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzy.widget.VerticalSlide;
import com.lzy.verticalslideview.fragment.Fragment_GridView;
import com.lzy.verticalslideview.fragment.Fragment_ListView;
import com.lzy.verticalslideview.fragment.Fragment_RecyclerView;
import com.lzy.verticalslideview.fragment.Fragment_ScrollView;
import com.lzy.verticalslideview.fragment.Fragment_WebView;

public class DragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        VerticalSlide dragLayout = (VerticalSlide) findViewById(R.id.dragLayout);

        Intent intent = getIntent();
        String top = intent.getStringExtra("top");
        String bottom = intent.getStringExtra("bottom");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (top) {
            case "ScrollView":
                transaction.replace(R.id.first, new Fragment_ScrollView());
                break;
            case "ListView":
                transaction.replace(R.id.first, new Fragment_ListView());
                break;
            case "GridView":
                transaction.replace(R.id.first, new Fragment_GridView());
                break;
            case "WebView":
                transaction.replace(R.id.first, new Fragment_WebView());
                break;
            case "RecyclerView":
                transaction.replace(R.id.first, new Fragment_RecyclerView());
                break;
        }
        switch (bottom) {
            case "ScrollView":
                transaction.replace(R.id.second, new Fragment_ScrollView());
                break;
            case "ListView":
                transaction.replace(R.id.second, new Fragment_ListView());
                break;
            case "GridView":
                transaction.replace(R.id.second, new Fragment_GridView());
                break;
            case "WebView":
                final Fragment_WebView fragment_webView = new Fragment_WebView();
                transaction.replace(R.id.second, fragment_webView);
                dragLayout.setOnShowNextPageListener(new VerticalSlide.OnShowNextPageListener() {
                    @Override
                    public void onShowNextPage() {
                        fragment_webView.initView();
                    }
                });
                break;
            case "RecyclerView":
                transaction.replace(R.id.second, new Fragment_RecyclerView());
                break;
        }
        transaction.commit();
    }
}
