package com.study.an.WebList;

import android.content.Context;

import com.study.an.CriminalIntent.Crime;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by admin on 2016/1/27.
 */
public class WebLab {
    private static WebLab sWebLab;
    private Context mAppContext;
    private ArrayList<Web> mWebs;
    public WebLab (Context appContext){
        mAppContext=appContext;
        mWebs=new ArrayList<>();

    }
    public static WebLab get(Context appContext){
        if(sWebLab==null){
            sWebLab=new WebLab(appContext.getApplicationContext());
        }
        return sWebLab;
    }
    public ArrayList<Web> getWebs(){
        return mWebs;
    }
    public void  addWeb(Web web){
        mWebs.add(web);
    }
    public Web getWeb(UUID id){
        for(Web web:mWebs){
          if(web.getId().equals(id)){
              return web;
          }
        }
        return null;
    }
}
