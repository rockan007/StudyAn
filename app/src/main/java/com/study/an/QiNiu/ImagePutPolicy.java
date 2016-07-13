package com.study.an.QiNiu;

import java.io.Serializable;

/**
 * Created by admin on 2016/7/11.
 */
public class ImagePutPolicy implements Serializable {
    private String scope;
    private long deadline;
    private ReturnBody returnBody;
    public ImagePutPolicy(String fileName) {
        scope = QiNiuConstant.SAVESPACE+":"+fileName;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = System.currentTimeMillis()/1000+deadline;
    }

    public ReturnBody getReturnBody() {
        return returnBody;
    }

    public void setReturnBody(ReturnBody returnBody) {
        this.returnBody = returnBody;
    }
}
