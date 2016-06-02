package com.study.an.WebList;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.study.an.all.R;

import java.util.List;

/**
 * Created by admin on 2016/1/28.
 */
public class WebFragment extends Fragment  {
    View mView;
  ListView mWebListView;
    String path;
    List<Web> mWebList;
    WebListAdapter mWebListAdapter;
    SwipeRefreshLayout mRefreshLayout;
    public  static final String EXTRA_WEB_ID="web_id";
    private boolean isRefresh=false;
    String[] webs={"No.1","No.2",Web.a,Web.a,Web.a,"No.6"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<6;i++){
            Web web=new Web();
            web.setWebSite(webs[i]);
            WebLab.get(this.getActivity()).addWeb(web);
        }
        mWebList=WebLab.get(this.getActivity()).getWebs();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.web_fragment,container,false);
        initViews();
        return mView;
    }
    private void initViews(){

        mWebListView=(ListView)mView.findViewById(R.id.web_fragment_listView);
        mRefreshLayout=(SwipeRefreshLayout)mView.findViewById(R.id.swipe_refresh);
//        mRefreshLayout.setOnRefreshListener(this);
        mWebListView.addHeaderView(getHeadView(),null,false);
        mWebListAdapter= new WebListAdapter(getActivity(), mWebList);
        mWebListView.setAdapter(mWebListAdapter);
    }
    private View getHeadView(){
       View view=View.inflate(getActivity(),R.layout.web_list_head,null);
        WebView mWeb=(WebView)view.findViewById(R.id.list_head_webView);
        Web web=new Web();
        mWeb.loadDataWithBaseURL(null, web.getA(), "text/html", "utf-8", null);
        mWeb.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                web.setWebTitle(title);
            }
        });
        mWeb.setFocusable(false);
        return view;
    }
//
//    @Override
//    public void onRefresh() {
//        if(!isRefresh){
//            isRefresh=true;
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mRefreshLayout.setRefreshing(false);
//                Web newWeb=new Web();
//                mWebList.add(newWeb);
//                mWebListAdapter.notifyDataSetChanged();
//                isRefresh=false;
//            }
//        },3000);
//        }

//    }
////

}
