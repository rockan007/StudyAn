package com.study.an.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by admin on 2016/6/1.
 */
public class BoxDrawingView extends View {
    private Box mCurrBox;
    private ArrayList<Box> mBoxes = new ArrayList<>();
    private Paint mCurrPaint;
    private Paint mBackGroundPaint;

    public BoxDrawingView(Context context) {
        this(context, null);
    }

    public BoxDrawingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mCurrPaint = new Paint();
        mCurrPaint.setColor(0x22ff0000);
        mBackGroundPaint = new Paint();

        mBackGroundPaint.setColor(0xfff8efe0);
    }

    public boolean onTouchEvent(MotionEvent event) {
        PointF curr = new PointF(event.getX(), event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrBox = new Box(curr);
                mBoxes.add(mCurrBox);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurrBox != null) {
                    mCurrBox.setCurr(curr);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                mCurrBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                mCurrBox = null;
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(mBackGroundPaint);
        for (Box box : mBoxes) {
            float left = Math.min(box.getCurr().x, box.getOrigin().x);
            float right = Math.max(box.getCurr().x, box.getOrigin().x);
            float top = Math.min(box.getCurr().y, box.getOrigin().y);
            float bottom = Math.max(box.getCurr().y, box.getOrigin().y);
            canvas.drawRect(left, top, right, bottom, mCurrPaint);
        }
    }
}
