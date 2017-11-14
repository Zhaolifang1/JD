package com.example.jd.home.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author 赵利芳
 *         类的作用：在onTouchEvent和onInterceptTouchEvent 中返回 false,就是不处理,滑动和事件分发都不处理,滑动就等于消失
 * @date
 */

public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 设置其是否能滑动换页
     * @param  false 不能换页， true 可以滑动换页
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
