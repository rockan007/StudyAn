package com.study.an.Utils;

import android.graphics.PointF;

/**
 * Created by admin on 2016/6/1.
 */
public class Box  {
    private PointF mOrigin;
    private PointF mCurr;
    public Box(PointF pointF){
        mOrigin=mCurr=pointF;
    }

    public PointF getCurr() {
        return mCurr;
    }

    public void setCurr(PointF curr) {
        mCurr = curr;
    }

    public PointF getOrigin() {
        return mOrigin;
    }

    public void setOrigin(PointF origin) {
        mOrigin = origin;
    }
}
