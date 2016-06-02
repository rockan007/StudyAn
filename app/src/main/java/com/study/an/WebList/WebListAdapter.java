package com.study.an.WebList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.study.an.all.R;

import java.util.List;

/**
 * Created by admin on 2016/2/21.
 */
public class WebListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Web> mWebList;
    private Web mWeb;
    private ViewHolder viewHolder;
    private int pos = -1;

    public WebListAdapter(Context context, List<Web> webList) {
        mContext = context;
        mWebList = webList;
    }

    @Override
    public int getCount() {
        return mWebList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder =ViewHolder.get(mContext,convertView,parent,R.layout.web_list_item,position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.web_list_item, null);
            viewHolder.head = (TextView) convertView.findViewById(R.id.web_list_title);
            viewHolder.contentWeb = (WebView) convertView.findViewById(R.id.web_list_webView);
            convertView.setTag(viewHolder);
            Log.e("没有", "啥都没");
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        TextView head=viewHolder.getView(R.id.web_list_title);
//        WebView contentWeb=viewHolder.getView(R.id.web_list_webView);
        mWeb = mWebList.get(position);
        viewHolder.head.setText(position + "");
        BaseUtils.loadWeb(mContext, viewHolder.contentWeb, mWeb.getWebSite());
//        viewHolder.contentWeb.loadDataWithBaseURL(null, mWeb.getWebSite(), "text/html", "utf-8", null);
//        WebSettings webSetting = viewHolder.contentWeb.getSettings();
//        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSetting.setDefaultTextEncodingName("utf-8");// 避免中文乱码
//        webSetting.setBuiltInZoomControls(true);
//        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        webSetting.setJavaScriptEnabled(true);
//        webSetting.setNeedInitialFocus(false);
//        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT | WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webSetting.setSupportZoom(false);
//        webSetting.setPluginState(WebSettings.PluginState.ON);
//        viewHolder.contentWeb.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        viewHolder.contentWeb.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
////                web.setWebTitle(title);
//            }
//        });
//        viewHolder.contentWeb.setFocusable(false);
        return convertView;
    }

    public class ViewHolder {
        public TextView head;
        public WebView contentWeb;
    }


}
