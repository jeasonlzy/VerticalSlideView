package com.lzy.widget.vertical;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;
import android.widget.Scroller;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/3/1
 * 描    述：当GridView在最顶部或者最底部的时候，不消费事件
 * 修订历史：
 * ================================================
 */
public class VerticalGridView extends GridView implements ObservableView {

    private float downX;
    private float downY;
    private Scroller mScroller;

    public VerticalGridView(Context context) {
        this(context, null);
    }

    public VerticalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.gridViewStyle);
    }

    public VerticalGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
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

    @Override
    public boolean isTop() {
        if (getChildCount() <= 0) return false;
        final int firstTop = getChildAt(0).getTop();
        return getFirstVisiblePosition() == 0 && firstTop >= getListPaddingTop();
    }

    @Override
    public boolean isBottom() {
        final int childCount = getChildCount();
        if (childCount <= 0) return false;
        final int itemsCount = getCount();
        final int firstPosition = getFirstVisiblePosition();
        final int lastPosition = firstPosition + childCount;
        final int lastBottom = getChildAt(childCount - 1).getBottom();
        return lastPosition >= itemsCount && lastBottom <= getHeight() - getListPaddingBottom();
    }

    @Override
    public void goTop() {
        setSelection(0);
    }
}