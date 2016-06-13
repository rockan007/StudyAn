package com.study.an.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.an.all.R;

import java.util.ArrayList;

/**
 * www.lostbug.com
 * Created by admin on 2016/6/12.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList mData;
    private LayoutInflater mLayoutInflater;

    public static enum ITEM_TYPE {
        ITEM_TYPE_TOP_IMAGE,
        ITEM_TYPE_TEXT,
        ITEM_TYPE_RIGHT_IMAGE
    }

    public void setData(ArrayList data) {
        mData = data;
    }

    public RecyclerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ITEM_TYPE whichView = ITEM_TYPE.values()[viewType];
        switch (whichView) {
            case ITEM_TYPE_RIGHT_IMAGE:
                return new RightImageViewHolder(mLayoutInflater.inflate(R.layout.item_recycler_right_image, parent, false));

            case ITEM_TYPE_TEXT:
                return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_recycler,parent,false));

            case ITEM_TYPE_TOP_IMAGE:
                return  new TopImageViewHolder(mLayoutInflater.inflate(R.layout.item_recycler_top_image,parent,false));

            default:
                return null;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (position%3){
            case 0:
                return ITEM_TYPE.ITEM_TYPE_RIGHT_IMAGE.ordinal();
            case 1:
                return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
            case 2:
                return ITEM_TYPE.ITEM_TYPE_TOP_IMAGE.ordinal();
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
     if(holder instanceof TopImageViewHolder){
         ((TopImageViewHolder)holder).mTopImage.setImageResource(R.drawable.ic_favorite_red_400_18dp);
         ((TopImageViewHolder)holder).tv.setText((String)mData.get(position));
     }else if(holder instanceof RightImageViewHolder){
         ((RightImageViewHolder)holder).mRightImage.setImageResource(R.drawable.ic_pets_cyan_a200_18dp);
         ((RightImageViewHolder)holder).tv.setText((String)mData.get(position) );

     }else {
         ((NormalTextViewHolder)holder).tv.setText((String)mData.get(position));
     }

    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_textView);
        }
    }

    public static class TopImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mTopImage;
        TextView tv;

        public TopImageViewHolder(View itemView) {
            super(itemView);
            mTopImage=(ImageView) itemView.findViewById(R.id.item_topImage);
            tv=(TextView)itemView.findViewById(R.id.item_textView);

        }
    }

    public static class RightImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mRightImage;
        TextView tv;
        public RightImageViewHolder(View itemView) {
            super(itemView);
            mRightImage=(ImageView)itemView.findViewById(R.id.item_rightView);
            tv=(TextView)itemView.findViewById(R.id.item_textView);
        }
    }
}
