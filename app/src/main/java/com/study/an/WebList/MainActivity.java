package com.study.an.WebList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.study.an.all.R;


/**
 * Created by admin on 2016/1/27.
 */
public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_list_main);
//        FrameLayout webListHead=(FrameLayout)findViewById(R.id.web_list_title);
//        FrameLayout webListContent=(FrameLayout)findViewById(R.id.web_list_content);
        FragmentManager fm=getSupportFragmentManager();
//        FragmentManager fm1=getSupportFragmentManager();
//        Fragment webListHead=fm.findFragmentById(R.id.web_list_head);
        Fragment webListContent=fm.findFragmentById(R.id.web_list_content);
//        if(webListHead==null){
//            webListHead=new WebFragment();
//            fm.beginTransaction().add(R.id.web_list_head,webListHead).commit();
//        }
        if(webListContent==null){
            webListContent=new WebFragment();
            fm.beginTransaction().add(R.id.web_list_content,webListContent).commit();
            moveTaskToBack(false);
        }

    }
}
