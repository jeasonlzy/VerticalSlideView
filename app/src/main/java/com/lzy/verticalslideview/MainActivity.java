package com.lzy.verticalslideview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DragActivity.class);
        switch (position) {
            case 0:
                intent.putExtra("top", "ScrollView");
                intent.putExtra("bottom", "ListView");
                break;
            case 1:
                intent.putExtra("top", "ScrollView");
                intent.putExtra("bottom", "GridView");
                break;
            case 2:
                intent.putExtra("top", "ScrollView");
                intent.putExtra("bottom", "WebView");
                break;
            case 3:
                intent.putExtra("top", "ScrollView");
                intent.putExtra("bottom", "RecyclerView");
                break;
            case 4:
                intent.putExtra("top", "ListView");
                intent.putExtra("bottom", "GridView");
                break;
            case 5:
                intent.putExtra("top", "ListView");
                intent.putExtra("bottom", "WebView");
                break;
            case 6:
                intent.putExtra("top", "GridView");
                intent.putExtra("bottom", "WebView");
                break;
            case 7:
                intent.putExtra("top", "RecyclerView");
                intent.putExtra("bottom", "WebView");
                break;
        }
        startActivity(intent);
    }

    private class MyAdapter extends BaseAdapter {

        private List<String> strings;

        public MyAdapter() {
            strings = new ArrayList<>();
            strings.add("ScrollView与ListView");
            strings.add("ScrollView与GridView");
            strings.add("ScrollView与WebView");
            strings.add("ScrollView与RecyclerView");
            strings.add("ListView与GridView");
            strings.add("ListView与WebView");
            strings.add("GridView与WebView");
            strings.add("RecyclerView与WebView");
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public String getItem(int position) {
            return strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            }
            TextView textView = (TextView) convertView;
            textView.setText(getItem(position));
            return convertView;
        }
    }
}
