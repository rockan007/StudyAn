package com.study.an.WebList;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by admin on 2016/1/28.
 */
public class MyWebView extends WebView {
    private int mMaxHeight = -1;

    public MyWebView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyle,
                     boolean privateBrowsing) {
        super(context, attrs, defStyle, privateBrowsing);
        // TODO Auto-generated constructor stub
    }

    public void setMaxHeight(int height){
        mMaxHeight = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(mMaxHeight>-1 && getMeasuredHeight()>mMaxHeight){
            setMeasuredDimension(getMeasuredWidth(), mMaxHeight);
        }
    }
}
