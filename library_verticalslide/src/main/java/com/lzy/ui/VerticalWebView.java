package com.lzy.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/3/1
 * 描    述：当WebView在最顶部或者最底部的时候，不消费事件
 * 修订历史：
 * ================================================
 */
public class VerticalWebView extends WebView {

    private float downY;

    public VerticalWebView(Context context) {
        this(context, null);
    }

    public VerticalWebView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle);
    }

    public VerticalWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                //如果滑动到了最底部，就允许继续向上滑动加载下一页，否者不允许
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = ev.getY() - downY;
                boolean allowParentTouchEvent;
                if (dy > 0) {
                    //位于顶部时下拉，让父View消费事件
                    allowParentTouchEvent = (getScrollY() <= 0);
                } else {
                    //位于底部时上拉，让父View消费事件
                    allowParentTouchEvent = (getHeight() + getScrollY() >= getContentHeight() * getScale());
                }
                getParent().requestDisallowInterceptTouchEvent(!allowParentTouchEvent);
        }
        return super.dispatchTouchEvent(ev);
    }
}
