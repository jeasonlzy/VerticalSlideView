package com.lzy.widget.vertical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/3/1
 * 描    述：当ScrollView在最顶部或者最底部的时候，不消费事件
 * 修订历史：
 * ================================================
 */
public class VerticalScrollView extends ScrollView implements ObservableView {

    private float downX;
    private float downY;

    public VerticalScrollView(Context context) {
        this(context, null);
    }

    public VerticalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.scrollViewStyle);
    }

    public VerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                //如果滑动到了最底部，就允许继续向上滑动加载下一页，否者不允许
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = ev.getX() - downX;
                float dy = ev.getY() - downY;
                boolean allowParentTouchEvent;
                if (Math.abs(dy) > Math.abs(dx)) {
                    if (dy > 0) {
                        //位于顶部时下拉，让父View消费事件
                        allowParentTouchEvent = isTop();
                    } else {
                        //位于底部时上拉，让父View消费事件
                        allowParentTouchEvent = isBottom();
                    }
                } else {
                    //水平方向滑动
                    allowParentTouchEvent = true;
                }
                getParent().requestDisallowInterceptTouchEvent(!allowParentTouchEvent);
        }
        return super.dispatchTouchEvent(ev);
    }

    @SuppressLint("NewApi")
    @Override
    public boolean isTop() {
        if (Build.VERSION.SDK_INT >= 14) {
            return !canScrollVertically(-1);
        } else {
            return getScrollY() <= 0;
        }
    }

    @Override
    public boolean isBottom() {
        if (Build.VERSION.SDK_INT >= 14) {
            return !canScrollVertically(1);
        } else {
            return getScrollY() + getHeight() >= computeVerticalScrollRange();
        }
    }

    @Override
    public void goTop() {
        scrollTo(0, 0);
    }
}
