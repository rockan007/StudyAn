package com.study.an.TrueFalse;

import java.util.UUID;

/**
 * Created by admin on 2016/1/20.
 */
public class TrueFalse {


    private boolean mIsTrue;
    private UUID mId;
    private int  mQuestion;
    public TrueFalse(int question,boolean isTrue){
        mId=UUID.randomUUID();
        mQuestion=question;
        mIsTrue=isTrue;
    }
    public UUID getId() {
        return mId;
    }


    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }
    public boolean isTrue() {
        return mIsTrue;
    }

    public void setIsTrue(boolean isTrue) {
        mIsTrue = isTrue;
    }


}
