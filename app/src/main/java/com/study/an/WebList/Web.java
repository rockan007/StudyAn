package com.study.an.WebList;

import java.util.UUID;

/**
 * Created by admin on 2016/1/27.
 */
public class Web {
    private String mWebSite;

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    private String webTitle;
    UUID mId;
    public Web(){
        mId=UUID.randomUUID();
    }
    public String getWebSite() {
        return mWebSite;
    }

    public void setWebSite(String webSite) {
        mWebSite = webSite;
    }

    public UUID getId() {
        return mId;
    }

        public String getA() {
                return a;
        }

       public static final String a ="\u003cdiv class=\"shijuItem\"\u003e\u003cbr /\u003e\u003cspan style=\"font-family:微软雅黑;\"\u003e给下面句中的“寄”字选择解释，正确的一项是(      )\u003cbr /\u003e他从小寄养在姑母家。\u003c/span\u003e  \u003cbr /\u003e\u003clabel\u003e\u003cinput type=\"radio\" value=\"A\"  name=\"TopicRadio1\"  /\u003e\u003cspan style=\"font-family:微软雅黑;\"\u003eA.托付，寄托。\u003c/span\u003e  \u003cbr /\u003e\u003c/label\u003e\u003clabel\u003e\u003cinput type=\"radio\" value=\"B\"  name=\"TopicRadio1\" /\u003e\u003cspan style=\"font-family:微软雅黑;\"\u003eB.依靠，依附。\u003c/span\u003e  \u003cbr /\u003e\u003c/label\u003e\u003clabel\u003e\u003cinput type=\"radio\" value=\"C\"  name=\"TopicRadio1\" /\u003e\u003cspan style=\"font-family:微软雅黑;\"\u003eC.托人传送。   \u003c/span\u003e  \u003cbr /\u003e\u003c/label\u003e\u003clabel\u003e\u003cinput type=\"radio\" value=\"D\"  name=\"TopicRadio1\" /\u003e\u003cspan style=\"font-family:微软雅黑;\"\u003eD. \u003c/span\u003e  \u003cbr /\u003e\u003c/label\u003e\u003c/div\u003e";

}
