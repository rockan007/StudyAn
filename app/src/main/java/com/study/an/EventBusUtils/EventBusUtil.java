package com.study.an.EventBusUtils;

import de.greenrobot.event.EventBus;

/**
 * EventBus公用模块
 * Created by an on 2016/7/20.
 */
public class EventBusUtil {
    //注册
    public static void register(Object object){
        EventBus.getDefault().register(object);
    }
    //解除注册
    public static void unregister(Object object){
        EventBus.getDefault().unregister(object);
    }
    //发送消息
    public static void post(Object objects){
        EventBus.getDefault().post(objects);
    }
}
