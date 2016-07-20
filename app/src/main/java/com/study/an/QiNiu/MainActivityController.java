package com.study.an.QiNiu;

import android.content.Context;

import com.google.gson.Gson;
import com.study.an.EventBusUtils.EventBusUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * an
 * Created by admin on 2016/7/20.
 */
public class MainActivityController  {
    private Context mContext;
    private static MainActivityController newInstance;
    public static MainActivityController getNewInstance(){
        if(newInstance==null){
            newInstance=new MainActivityController();
        }
        return newInstance;
    }
    public MainActivityController setContext(Context context){
        mContext=context;
        return this;
    }

    /**
     * 获取上传token
     */
    public void getUploadToken(){
        OkHttpClient client = new OkHttpClient();
        //根据请求URL创建一个Request对象
        Request request = new Request.Builder().url(QiNiuConstant.UPLOADTOKENURL).build();
        //根据Request对象发起Get同步Http请求
        Call call =client.newCall(request);
        NewCallBack callBack=new NewCallBack();
        callBack.setTag(QiNiuConstant.UPLOADTOKEN);
        call.enqueue(callBack);
    }

    /**
     * 获取下载token
     * @param fileName
     */
    public void getDownLoadToken(String fileName){
        OkHttpClient client =new OkHttpClient();
        Request request=new Request.Builder().url(QiNiuConstant.DOWNLOADTOKENURL+fileName).build();
        Call call=client.newCall(request);
        NewCallBack callBack=new NewCallBack();
        callBack.setTag(QiNiuConstant.DOWNLOADTOKEN);
        call.enqueue(callBack);
    }
    private class NewCallBack implements Callback{
        private int mTag;

        @Override
        public void onFailure(Call call, IOException e) {

        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            ArrayList<Object> list=new ArrayList<>();
            list.add(mTag);
                switch (mTag){
                    case QiNiuConstant.UPLOADTOKEN:
                        Gson gson=new Gson();
                        UploadTokenModel uploadToken= gson.fromJson(response.body().string(),UploadTokenModel.class);
                        list.add(uploadToken);
                        break;
                    case QiNiuConstant.DOWNLOADTOKEN:
                        String downloadToken=response.body().string();
                        list.add(downloadToken);
                        break;
                    default:
                        break;
                }
            EventBusUtil.post(list);
        }

        public void setTag(int tag) {
            mTag = tag;
        }
    }
}
