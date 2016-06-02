package com.study.an.WebList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder{
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private final static String TAG="ViewHolder";
//	private BitmapUtils bitmapUtils;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,int position){
//		bitmapUtils = JSYSignApplication.getInstance().bitmap;
        this.mPosition = position;
        Log.d(TAG,position+"");
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,ViewGroup parent, int layoutId, int position){
        if (convertView == null){
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView(){
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对应的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text){
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

//	}




    public int getPosition(){
        return mPosition;
    }

}
