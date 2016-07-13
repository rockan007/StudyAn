package com.study.an.QiNiu;

import java.io.Serializable;

/**
 * Created by admin on 2016/7/11.
 */
public class ReturnBody implements Serializable {
    private String mName;
    private long mSize;
    private long  mW;
    private long mH;
    private String mHash;

    public long getH() {
        return mH;
    }

    public void setH(long h) {
        mH = h;
    }

    public String getHash() {
        return mHash;
    }

    public void setHash(String hash) {
        mHash = hash;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getW() {
        return mW;
    }

    public void setW(long w) {
        mW = w;
    }

    public long getSize() {
        return mSize;
    }

    public void setSize(long size) {
        mSize = size;
    }
}
