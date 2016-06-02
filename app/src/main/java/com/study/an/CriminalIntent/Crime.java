package com.study.an.CriminalIntent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2016/1/22.
 */
public class Crime {
    private static final String JSON_ID="id";
    private static final String JSON_TITLE="title";
    private static final String JSON_DATE="date";
    private static final String JSON_SOLVED="solved";
    private String mTitle;
    private UUID mId;
    private Date mDate;
    private boolean mIsSolved;


    public void setDate(Date date) {
        mDate = date;
    }

    public Crime(){
        mId=UUID.randomUUID();
        mDate=new Date();
    }
    public Crime(JSONObject object)throws JSONException{
        mId=UUID.fromString(object.getString(JSON_ID));
        if(object.has(JSON_TITLE)){
            mTitle=object.getString(JSON_TITLE);
        }
        mDate=new Date(object.getLong(JSON_DATE));
        mIsSolved=object.getBoolean(JSON_SOLVED);
    }
    public JSONObject toJSON()throws JSONException{
        JSONObject object=new JSONObject();
        object.put(JSON_ID,mId.toString());
        object.put(JSON_TITLE,mTitle);
        object.put(JSON_DATE,mDate.getTime());
        object.put(JSON_SOLVED,mIsSolved);
        return object;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    public Date getDate() {
        return mDate;
    }
    public boolean isSolved() {
        return mIsSolved;
    }

    public void setIsSolved(boolean isSolved) {
        mIsSolved = isSolved;
    }
    @Override
    public String toString(){
        return mTitle;
    }

}
