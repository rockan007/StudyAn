//package com.study.an.WebList;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import com.example.admin.myapplication.R;
//import com.study.an.CriminalIntent.Crime;
//
//import java.util.ArrayList;
//
///**
// * Created by admin on 2016/1/27.
// */
//public class WebAdapter extends ArrayAdapter<Web> {
//    private static Context sAppContext;
//    public WebAdapter(Context context,ArrayList<Web> webs){
//
//        super(context,0,webs);
//        sAppContext=context;
//
//    }
//
//    @Override
//    public int getCount() {
//        return super.getCount();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView==null){
//            convertView=getLayoutInflater().inflate(R.layout.crime_list_item,null);
//        }
//        Crime c=getItem(position);
//        TextView mTitle=(TextView)convertView.findViewById(R.id.crime_list_item_title);
//        mTitle.setText(c.getTitle());
//        TextView mDate=(TextView)convertView.findViewById(R.id.crime_list_item_date);
//        mDate.setText(simpleDateFormat.format(c.getDate()));
//        CheckBox mSolved=(CheckBox)convertView.findViewById(R.id.crime_list_item_solved);
//        mSolved.setChecked(c.isSolved());
//        return convertView;
//    }
//    }
//}
