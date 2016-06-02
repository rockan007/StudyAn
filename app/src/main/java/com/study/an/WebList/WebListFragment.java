//package com.study.an.WebList;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ListFragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//
//import com.study.an.all.R;
//
//import java.util.ArrayList;
//
//
///**
// * Created by admin on 2016/1/27.
// */
//public class WebListFragment extends ListFragment {
//    View mView;
//    View mheaderView;
//    ArrayList<Web> mWebs;
//    ArrayList<String> websites;
//    boolean isRefresh;
//    WebLab mWebLab;
//    int[] website = {R.string.baidu, R.string.sina, R.string.feng_huang, R.string.wang_yi};
//    private static final String EXTRA_WEBSITE = "web_sites";
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        EventBusUtil.register(this);
//        mWebs = WebLab.get(getActivity()).getWebs();
//        mWebs.clear();
////        if(getArguments()!=null){
////            isRefresh=(boolean)getArguments().getSerializable(EXTRA_WEBSITE);
////            if(isRefresh)
////                website=new int[]{R.string.baidu,R.string.sina};
////        }else {
////
////        websites = new ArrayList<>();
////            String[] mWebSits=new String[website.length];
//        for (int i = 0; i < 10; i++) {
////            mWebSits[i]=getResources().getString(website[i]);
//            Web mWeb = new Web();
////            websites.add(mWebSits[i]);
////            mWeb.setWebSite(mWebSits[i]);
//            mWebs.add(mWeb);
////        }
//
////            setListAdapter(new WebAdapter(mWebs));
//
////            HeaderViewListAdapter(new WebFragment(),null);
//        }
////    public void onEventMainThread(ArrayList post){
////        switch (msg){
////            case 1:
////
////                break;
////        }
////    }
//
////    @Override
////    public void onDestroy() {
////        super.onDestroy();
////        EventBusUtil.unregister(this);
////    }
//
//        //    @Nullable
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////
////        mView=inflater.inflate(R.layout.fragment_weblist,container,false);
////        mListView=(ListView)mView.findViewById(R.id.list_item);
////        WebAdapter mWebAdapter=new WebAdapter(mWebs);
////        mListView.setAdapter(mWebAdapter);
////        mListView.setOnItemClickListener(this);
////        return mView;
////    }
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mheaderView = LayoutInflater.from(getActivity()).inflate(R.layout.web_fragment, null);
////        WebView mWeb = (WebView) mheaderView.findViewById(R.id.web_fragment_listView);
//        Web web = new Web();
//        mWeb.loadDataWithBaseURL(null, web.getA(), "text/html", "utf-8", null);
//        mWeb.getSettings().setPluginState(WebSettings.PluginState.ON);
////                mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
//        mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        mWeb.getSettings().setJavaScriptEnabled(true);
//        mWeb.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        mWeb.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
////                web.setWebTitle(title);
//            }
//        });
//        mWeb.setFocusable(false);
//        if (mheaderView != null) {
//            getListView().addHeaderView(mheaderView);
//        }
//        setListAdapter(new WebAdapter(mWebs));
//    }
//
//    public class WebAdapter extends ArrayAdapter<Web> {
//        public WebAdapter(ArrayList<Web> webs) {
//            super(getActivity(), 0, webs);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
////            ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,R.layout.item_qiuzhi_index_listview, position);
////            LinearLayout layout_question = viewHolder.getView(R.id.qiuzhi_item_layout_question);
////
//            if (convertView == null) {
//                convertView = getActivity().getLayoutInflater().inflate(R.layout.web_list_item, null);
//            }
//
//            final Web web = getItem(position);
//            ListView mWebView = (ListView) convertView.findViewById(R.id.web_fragment_listView);
////
////            try{
////
//////                mWebTitle.setClickable(false);
////                mWebView.loadDataWithBaseURL(null, web.getA(), "text/html", "utf-8", null);
//////            mWebView.loadUrl(web.getWebSite());
////                mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
//////                mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
////                mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
////                mWebView.getSettings().setJavaScriptEnabled(true);
////                mWebView.setWebViewClient(new WebViewClient() {
////                    @Override
////                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                        view.loadUrl(url);
////                        return true;
////                    }
////                });
////                mWebView.setWebChromeClient(new WebChromeClient() {
////                    @Override
////                    public void onReceivedTitle(WebView view, String title) {
////                        super.onReceivedTitle(view, title);
////                        web.setWebTitle(title);
////                    }
////                });
////                mWebView.setFocusable(false);
////            }catch (Exception e){
////                e.printStackTrace();
////            }
//
//            return convertView;
//        }
//    }
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Web w = ((WebAdapter) getListAdapter()).getItem(position);
//        Intent intent = new Intent(getActivity(), WebActivity.class);
//        intent.putExtra(WebFragment.EXTRA_WEB_ID, w.getId());
//        Log.e("TAG", w.getWebTitle());
//        startActivity(intent);
//    }
////    public static WebListFragment newInstance(boolean webs){
////        Bundle args=new Bundle();
////        args.putSerializable(EXTRA_WEBSITE, webs);
////        WebListFragment webListFragment=new WebListFragment();
////        webListFragment.setArguments(args);
////        return webListFragment;
////    }
//
//
//}
